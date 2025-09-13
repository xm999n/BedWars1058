package com.andrei1058.bedwars.popuptower;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.team.TeamColor;
import com.andrei1058.bedwars.configuration.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

/**
 * Shared builder for Popup Tower directions.
 * Keeps the exact behavior of original Tower* classes: consumes one item,
 * plays the same sound each tick, places two blocks per tick using NewPlaceBlock,
 * and cancels the scheduled task at the same checkpoints.
 */
public final class PopupTowerBuilder {

    private PopupTowerBuilder() {}

    public static void build(Location loc, Block originBlock, TeamColor color, Player p, List<String> relloc) {
        // Consume exactly one item from player's hand, preserving original behavior
        ItemStack itemInHand = p.getInventory().getItemInHand();
        if (itemInHand != null) {
            if (itemInHand.getAmount() > 1) {
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            } else {
                p.getInventory().setItemInHand(null);
            }
        }

        final int[] i = new int[]{0};
        final BukkitTask[] taskRef = new BukkitTask[1];
        taskRef[0] = Bukkit.getScheduler().runTaskTimer(BedWars.plugin, () -> {
            Sounds.playsoundArea("pop-up-tower-build", loc, 1.0F, 0.5F);
            if (relloc.size() + 1 == i[0] + 1) {
                taskRef[0].cancel();
            } else {
                String c1 = relloc.get(i[0]);
                if (c1.contains("ladder")) {
                    int ldata = Integer.parseInt(c1.split("ladder")[1]);
                    new NewPlaceBlock(originBlock, c1, color, p, true, ldata);
                } else {
                    new NewPlaceBlock(originBlock, c1, color, p, false, 0);
                }

                if (relloc.size() + 1 == i[0] + 2) {
                    taskRef[0].cancel();
                } else {
                    String c2 = relloc.get(i[0] + 1);
                    if (c2.contains("ladder")) {
                        int ldatax = Integer.parseInt(c2.split("ladder")[1]);
                        new NewPlaceBlock(originBlock, c2, color, p, true, ldatax);
                    } else {
                        new NewPlaceBlock(originBlock, c2, color, p, false, 0);
                    }

                    i[0] += 2;
                }
            }
        }, 0L, 1L);
    }
}

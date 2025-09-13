package com.andrei1058.bedwars.popuptower;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.team.TeamColor;
import com.andrei1058.bedwars.api.configuration.ConfigPath;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.configuration.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class PopupTowerBuilder {

    private static final int TOWER_HEIGHT = 8;

    private PopupTowerBuilder() {
    }

    // Provide static relloc arrays per direction to avoid class instantiation
    @Contract(pure = true)
    public static java.util.@NotNull @Unmodifiable List<String> getFacingEast() {
        return java.util.List.of(
                "2, 0, -1", "1, 0, -2", "0, 0, -2", "-1, 0, -1", "-1, 0, 0", "-1, 0, 1", "0, 0, 2", "1, 0, 2", "2, 0, 1", "0, 0, 0, ladder5",
                "2, 1, -1", "1, 1, -2", "0, 1, -2", "-1, 1, -1", "-1, 1, 0", "-1, 1, 1", "0, 1, 2", "1, 1, 2", "2, 1, 1", "0, 1, 0, ladder5",
                "2, 2, -1", "1, 2, -2", "0, 2, -2", "-1, 2, -1", "-1, 2, 0", "-1, 2, 1", "0, 2, 2", "1, 2, 2", "2, 2, 1", "0, 2, 0, ladder5",
                "2, 3, 0", "2, 3, -1", "1, 3, -2", "0, 3, -2", "-1, 3, -1", "-1, 3, 0", "-1, 3, 1", "0, 3, 2", "1, 3, 2", "2, 3, 1", "0, 3, 0, ladder5",
                "2, 4, 0", "2, 4, -1", "1, 4, -2", "0, 4, -2", "-1, 4, -1", "-1, 4, 0", "-1, 4, 1", "0, 4, 2", "1, 4, 2", "2, 4, 1", "0, 4, 0, ladder5",
                "-1, 5, -2", "0, 5, -2", "1, 5, -2", "2, 5, -2", "-1, 5, -1", "0, 5, -1", "1, 5, -1", "2, 5, -1", "-1, 5, 0", "1, 5, 0", "2, 5, 0", "-1, 5, 1", "0, 5, 0, ladder5", "0, 5, 1", "1, 5, 1", "2, 5, 1", "-1, 5, 2", "0, 5, 2", "1, 5, 2", "2, 5, 2",
                "2, 5, -3", "2, 6, -3", "2, 7, -3", "1, 6, -3", "0, 6, -3", "-1, 5, -3", "-1, 6, -3", "-1, 7, -3",
                "-2, 5, -2", "-2, 6, -2", "-2, 7, -2", "-2, 6, -1", "-2, 5, 0", "-2, 6, 0", "-2, 7, 0", "-2, 6, 1", "-2, 5, 2", "-2, 6, 2", "-2, 7, 2",
                "2, 5, 3", "2, 6, 3", "2, 7, 3", "1, 6, 3", "0, 6, 3", "-1, 5, 3", "-1, 6, 3", "-1, 7, 3",
                "3, 5, -2", "3, 6, -2", "3, 7, -2", "3, 6, -1", "3, 5, 0", "3, 6, 0", "3, 7, 0", "3, 6, 1", "3, 5, 2", "3, 6, 2", "3, 7, 2"
        );
    }

    @Contract(pure = true)
    public static java.util.@NotNull @Unmodifiable List<String> getFacingNorth() {
        return java.util.List.of(
                "-1, 0, -2", "-2, 0, -1", "-2, 0, 0", "-1, 0, 1", "0, 0, 1", "1, 0, 1", "2, 0, 0", "2, 0, -1", "1, 0, -2", "0, 0, 0, ladder2",
                "-1, 1, -2", "-2, 1, -1", "-2, 1, 0", "-1, 1, 1", "0, 1, 1", "1, 1, 1", "2, 1, 0", "2, 1, -1", "1, 1, -2", "0, 1, 0, ladder2",
                "-1, 2, -2", "-2, 2, -1", "-2, 2, 0", "-1, 2, 1", "0, 2, 1", "1, 2, 1", "2, 2, 0", "2, 2, -1", "1, 2, -2", "0, 2, 0, ladder2",
                "0, 3, -2", "-1, 3, -2", "-2, 3, -1", "-2, 3, 0", "-1, 3, 1", "0, 3, 1", "1, 3, 1", "2, 3, 0", "2, 3, -1", "1, 3, -2", "0, 3, 0, ladder2",
                "0, 4, -2", "-1, 4, -2", "-2, 4, -1", "-2, 4, 0", "-1, 4, 1", "0, 4, 1", "1, 4, 1", "2, 4, 0", "2, 4, -1", "1, 4, -2", "0, 4, 0, ladder2",
                "-2, 5, 1", "-2, 5, 0", "-2, 5, -1", "-2, 5, -2", "-1, 5, 1", "-1, 5, 0", "-1, 5, -1", "-1, 5, -2", "0, 5, 1", "0, 5, -1", "0, 5, -2", "1, 5, 1", "0, 5, 0, ladder2", "1, 5, 0", "1, 5, -1", "1, 5, -2", "2, 5, 1", "2, 5, 0", "2, 5, -1", "2, 5, -2",
                "-3, 5, -2", "-3, 6, -2", "-3, 7, -2", "-3, 6, -1", "-3, 6, 0", "-3, 5, 1", "-3, 6, 1", "-3, 7, 1",
                "-2, 5, 2", "-2, 6, 2", "-2, 7, 2", "-1, 6, 2", "0, 5, 2", "0, 6, 2", "0, 7, 2", "1, 6, 2", "2, 5, 2", "2, 6, 2", "2, 7, 2",
                "3, 5, -2", "3, 6, -2", "3, 7, -2", "3, 6, -1", "3, 6, 0", "3, 5, 1", "3, 6, 1", "3, 7, 1",
                "-2, 5, -3", "-2, 6, -3", "-2, 7, -3", "-1, 6, -3", "0, 5, -3", "0, 6, -3", "0, 7, -3", "1, 6, -3", "2, 5, -3", "2, 6, -3", "2, 7, -3"
        );
    }

    @Contract(pure = true)
    public static java.util.@NotNull @Unmodifiable List<String> getFacingSouth() {
        return java.util.List.of(
                "1, 0, 2", "2, 0, 1", "2, 0, 0", "1, 0, -1", "0, 0, -1", "-1, 0, -1", "-2, 0, 0", "-2, 0, 1", "-1, 0, 2", "0, 0, 0, ladder3",
                "1, 1, 2", "2, 1, 1", "2, 1, 0", "1, 1, -1", "0, 1, -1", "-1, 1, -1", "-2, 1, 0", "-2, 1, 1", "-1, 1, 2", "0, 1, 0, ladder3",
                "1, 2, 2", "2, 2, 1", "2, 2, 0", "1, 2, -1", "0, 2, -1", "-1, 2, -1", "-2, 2, 0", "-2, 2, 1", "-1, 2, 2", "0, 2, 0, ladder3",
                "0, 3, 2", "1, 3, 2", "2, 3, 1", "2, 3, 0", "1, 3, -1", "0, 3, -1", "-1, 3, -1", "-2, 3, 0", "-2, 3, 1", "-1, 3, 2", "0, 3, 0, ladder3",
                "0, 4, 2", "1, 4, 2", "2, 4, 1", "2, 4, 0", "1, 4, -1", "0, 4, -1", "-1, 4, -1", "-2, 4, 0", "-2, 4, 1", "-1, 4, 2", "0, 4, 0, ladder3",
                "2, 5, -1", "2, 5, 0", "2, 5, 1", "2, 5, 2", "1, 5, -1", "1, 5, 0", "1, 5, 1", "1, 5, 2", "0, 5, -1", "0, 5, 1", "0, 5, 2", "-1, 5, -1", "0, 5, 0, ladder3", "-1, 5, 0", "-1, 5, 1", "-1, 5, 2", "-2, 5, -1", "-2, 5, 0", "-2, 5, 1", "-2, 5, 2",
                "3, 5, 2", "3, 6, 2", "3, 7, 2", "3, 6, 1", "3, 6, 0", "3, 5, -1", "3, 6, -1", "3, 7, -1",
                "2, 5, -2", "2, 6, -2", "2, 7, -2", "1, 6, -2", "0, 5, -2", "0, 6, -2", "0, 7, -2", "-1, 6, -2", "-2, 5, -2", "-2, 6, -2", "-2, 7, -2",
                "-3, 5, 2", "-3, 6, 2", "-3, 7, 2", "-3, 6, 1", "-3, 6, 0", "-3, 5, -1", "-3, 6, -1", "-3, 7, -1",
                "2, 5, 3", "2, 6, 3", "2, 7, 3", "1, 6, 3", "0, 5, 3", "0, 6, 3", "0, 7, 3", "-1, 6, 3", "-2, 5, 3", "-2, 6, 3", "-2, 7, 3"
        );
    }

    @Contract(pure = true)
    public static java.util.@NotNull @Unmodifiable List<String> getFacingWest() {
        return java.util.List.of(
                "-2, 0, 1", "-1, 0, 2", "0, 0, 2", "1, 0, 1", "1, 0, 0", "1, 0, -1", "0, 0, -2", "-1, 0, -2", "-2, 0, -1", "0, 0, 0, ladder4",
                "-2, 1, 1", "-1, 1, 2", "0, 1, 2", "1, 1, 1", "1, 1, 0", "1, 1, -1", "0, 1, -2", "-1, 1, -2", "-2, 1, -1", "0, 1, 0, ladder4",
                "-2, 2, 1", "-1, 2, 2", "0, 2, 2", "1, 2, 1", "1, 2, 0", "1, 2, -1", "0, 2, -2", "-1, 2, -2", "-2, 2, -1", "0, 2, 0, ladder4",
                "-2, 3, 0", "-2, 3, 1", "-1, 3, 2", "0, 3, 2", "1, 3, 1", "1, 3, 0", "1, 3, -1", "0, 3, -2", "-1, 3, -2", "-2, 3, -1", "0, 3, 0, ladder4",
                "-2, 4, 0", "-2, 4, 1", "-1, 4, 2", "0, 4, 2", "1, 4, 1", "1, 4, 0", "1, 4, -1", "0, 4, -2", "-1, 4, -2", "-2, 4, -1", "0, 4, 0, ladder4",
                "1, 5, 2", "0, 5, 2", "-1, 5, 2", "-2, 5, 2", "1, 5, 1", "0, 5, 1", "-1, 5, 1", "-2, 5, 1", "1, 5, 0", "-1, 5, 0", "-2, 5, 0", "1, 5, -1", "0, 5, -1", "-1, 5, -1", "-2, 5, -1", "0, 5, 0, ladder4",
                "1, 5, -2", "0, 5, -2", "-1, 5, -2", "-2, 5, -2",
                "-2, 5, 3", "-2, 6, 3", "-2, 7, 3", "-1, 6, 3", "0, 6, 3", "1, 5, 3", "1, 6, 3", "1, 7, 3",
                "2, 5, 2", "2, 6, 2", "2, 7, 2", "2, 6, 1", "2, 5, 0", "2, 6, 0", "2, 7, 0", "2, 6, -1", "2, 5, -2", "2, 6, -2", "2, 7, -2",
                "-2, 5, -3", "-2, 6, -3", "-2, 7, -3", "-1, 6, -3", "0, 6, -3", "1, 5, -3", "1, 6, -3", "1, 7, -3",
                "-3, 5, 2", "-3, 6, 2", "-3, 7, 2", "-3, 6, 1", "-3, 5, 0", "-3, 6, 0", "-3, 7, 0", "-3, 6, -1", "-3, 5, -2", "-3, 6, -2", "-3, 7, -2"
        );
    }

    public static void build(Location loc, Block originBlock, TeamColor color, @NotNull Player p, List<String> relloc) {
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
                    place(originBlock, c1, color, p, true, ldata);
                } else {
                    place(originBlock, c1, color, p, false, 0);
                }

                if (relloc.size() + 1 == i[0] + 2) {
                    taskRef[0].cancel();
                } else {
                    String c2 = relloc.get(i[0] + 1);
                    if (c2.contains("ladder")) {
                        int ldatax = Integer.parseInt(c2.split("ladder")[1]);
                        place(originBlock, c2, color, p, true, ldatax);
                    } else {
                        place(originBlock, c2, color, p, false, 0);
                    }

                    i[0] += 2;
                }
            }
        }, 0L, 1L);
    }

    public static void handleTowerPlace(@NotNull BlockPlaceEvent event) {
        // Centralized placement entrypoint for popup towers
        event.setCancelled(true);
        Player p = event.getPlayer();
        org.bukkit.Location loc = event.getBlock().getLocation();
        IArena a1 = com.andrei1058.bedwars.arena.Arena.getArenaByPlayer(p);
        if (a1 == null) return;
        // Pre-build height check: base Y + TOWER_HEIGHT must not exceed arena max build Y
        try {
            int maxY = a1.getConfig().getInt(ConfigPath.ARENA_CONFIGURATION_MAX_BUILD_Y);
            if (loc.getBlockY() + TOWER_HEIGHT > maxY) {
                return; // stop silently as requested; event already cancelled
            }
        } catch (Exception ignored) {
        }

        TeamColor col = a1.getTeam(p).getColor();
        List<String> locations = getStrings(p);
        build(loc, event.getBlockPlaced(), col, p, locations);
    }

    private static @NotNull List<String> getStrings(@NotNull Player p) {
        double rotation = (p.getLocation().getYaw() - 90.0F) % 360.0F;
        if (rotation < 0.0D) rotation += 360.0D;

        List<String> relloc;
        if (45.0D <= rotation && rotation < 135.0D) {
            relloc = getFacingSouth();
        } else if (225.0D <= rotation && rotation < 315.0D) {
            relloc = getFacingNorth();
        } else if (135.0D <= rotation && rotation < 225.0D) {
            relloc = getFacingWest();
        } else { // 0-45 or 315-360 -> EAST
            relloc = getFacingEast();
        }
        return relloc;
    }

    private static void place(
            @NotNull Block block,
            @NotNull String xyz,
            TeamColor color,
            Player player,
            boolean ladder,
            int ladderData
    ) {
        int x = Integer.parseInt(xyz.split(", ")[0]);
        int y = Integer.parseInt(xyz.split(", ")[1]);
        int z = Integer.parseInt(xyz.split(", ")[2]);
        // Target location for the next placement
        Location targetLoc = block.getRelative(x, y, z).getLocation();

        // Reuse BreakPlace protection around spawns/shops/upgrades/generators
        if (com.andrei1058.bedwars.listeners.BreakPlace.isProtectedLocation(Arena.getArenaByPlayer(player), targetLoc)) {
            return;
        }

        if (block.getRelative(x, y, z).getType().equals(Material.AIR)) {
            if (!ladder)
                BedWars.nms.placeTowerBlocks(block, Arena.getArenaByPlayer(player), color, x, y, z);
            else
                BedWars.nms.placeLadder(block, x, y, z, Arena.getArenaByPlayer(player), ladderData);
        }
    }
}

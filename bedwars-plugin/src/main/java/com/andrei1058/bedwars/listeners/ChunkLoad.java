/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2021 Andrei Dascălu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact e-mail: andrew.dascalu@gmail.com
 */

package com.andrei1058.bedwars.listeners;

import com.andrei1058.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class ChunkLoad implements Listener {

    @EventHandler
    public void onChunkLoadEvent(ChunkLoadEvent e) {
        if (e == null) return;
        if (e.getChunk() == null) return;

        Chunk chunk = e.getChunk();
        if (!Bukkit.isPrimaryThread()) {
            Bukkit.getScheduler().runTask(BedWars.plugin, () -> processChunk(chunk));
            return;
        }

        processChunk(chunk);
    }

    private void processChunk(Chunk chunk) {
        for (Entity entity : chunk.getEntities()) {
            if (entity instanceof ArmorStand) {
                ArmorStand armorStand = (ArmorStand) entity;
                if (entity.hasMetadata("bw1058-setup")) {
                    entity.remove();
                    continue;
                }
                if (!armorStand.isVisible() && armorStand.isMarker() && entity.isCustomNameVisible()) {
                    String name = ChatColor.stripColor(entity.getCustomName());
                    if (name != null && (name.contains(" SET") || name.contains(" set"))) {
                        entity.remove();
                    }
                }
            }
        }
    }
}

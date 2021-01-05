package com.i0dev.util;

import com.i0dev.MysteryMobSpawners;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.HashSet;

public class ChunkLoading implements Listener {

    private HashSet<Chunk> chunkHashSet = new HashSet<Chunk>();

    private MysteryMobSpawners instance;

    public ChunkLoading(MysteryMobSpawners instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent e) {
        if (chunkHashSet.contains(e.getChunk())) {
            e.getChunk().load(true);
            e.setCancelled(true);
        }

    }

    public void addChunk(Chunk chunk) {
        if (!chunkHashSet.contains(chunk)) {
            chunkHashSet.add(chunk);
        }
    }

    public void removeChunk(Chunk chunk) {
        if (chunkHashSet.contains(chunk)) {
            chunkHashSet.remove(chunk);
        }
    }

    public boolean containsChunk(Chunk chunk) {
        return chunkHashSet.contains(chunk);
    }
}

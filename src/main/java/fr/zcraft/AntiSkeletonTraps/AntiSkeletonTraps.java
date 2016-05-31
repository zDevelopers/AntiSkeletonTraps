package fr.zcraft.AntiSkeletonTraps;

import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;


public final class AntiSkeletonTraps extends JavaPlugin implements Listener
{
    private final Random random = new Random();

    private Double cancelProbability;

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);

        cancelProbability = getConfig().getDouble("trap-cancel-probability", 0.5d);
    }

    @EventHandler (priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTrapSpawn(CreatureSpawnEvent ev)
    {
        if (ev.getEntity() instanceof Horse
                && ((Horse) ev.getEntity()).getVariant() == Horse.Variant.SKELETON_HORSE
                && ev.getSpawnReason() == CreatureSpawnEvent.SpawnReason.LIGHTNING
                && random.nextDouble() <= cancelProbability)
        {
            ev.setCancelled(true);
        }
    }
}

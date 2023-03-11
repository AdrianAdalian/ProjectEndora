package com.perceus.endora.utilities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceus.endora.Endora;

public class EndoraUtilities
{
	
	/**
	 * A collection of utilities to change an itemstack's metadata.
	 * @author Yuki_emeralis
	 */
	public static void saveToNamespacedKey(ItemStack target, String key, String value)
    {
        NamespacedKey nskey = new NamespacedKey((Plugin) JavaPlugin.getPlugin(Endora.class), key);

        ItemMeta meta = target.getItemMeta();
        meta.getPersistentDataContainer().set(nskey, PersistentDataType.STRING, value);

        target.setItemMeta(meta);
    }

    /**
     * Reads a string from an itemstack's persistent data container.
     * @param target
     * @param key
     * @return
     */
	public static String readFromNamespacedKey(ItemStack target, String key)
    {
        NamespacedKey nskey = new NamespacedKey((Plugin) JavaPlugin.getPlugin(Endora.class), key);

        ItemMeta meta = target.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();

        return container.has(nskey, PersistentDataType.STRING) ? container.get(nskey, PersistentDataType.STRING) : null;
    }

    public static PersistentDataContainer getDataContainer(ItemStack target)
    {
        return target.getItemMeta().getPersistentDataContainer();
    }

    /**
     * Obtains whether or not an itemstack has a specific key in a persistent data container.
     * @param target
     * @param key
     * @return
     */
    public static boolean hasNamespacedKey(ItemStack target, String key)
    {
        NamespacedKey nskey = new NamespacedKey((Plugin) JavaPlugin.getPlugin(Endora.class), key);

        ItemMeta meta = target.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();

        return container.has(nskey, PersistentDataType.STRING);
    }

    public static List<String> getNamespacedKeysOfType(ItemStack target, PersistentDataType<?, ?> type)
    {
        return getNamespacedKeysOfType(target, type, "");
    }

    public static List<String> getNamespacedKeysOfType(ItemStack target, PersistentDataType<?, ?> type, String prefix)
    {
        List<String> buffer = new ArrayList<>();

        PersistentDataContainer container = getDataContainer(target);

        for (NamespacedKey key : container.getKeys())
        {
            if (!key.getKey().startsWith(prefix))
                continue;

            if (!container.has(key, type))
                continue;

            buffer.add(key.getKey());
        }

        return buffer;
    }
}

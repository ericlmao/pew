package games.negative.pew;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

/**
 * Represents the management class of custom enchantments.
 */
public abstract class CustomEnchantManager {

    /**
     * The instance of the custom enchantment manager.
     */
    protected static CustomEnchantManager instance;

    /**
     * Adds a {@link CustomEnchant} to the registry.
     * @param enchant The enchantment to add.
     * @apiNote Enchantments should be registered and added during the plugin's bootstrap process. See {@link EnchantsBootstapper}
     */
    public abstract void put(@NotNull CustomEnchant enchant);

    /**
     * Initialize all registered {@link CustomEnchant}'s listeners.
     * @param plugin The plugin to register the listeners to.
     */
    public abstract void init(@NotNull JavaPlugin plugin);

    /**
     * Returns all registered {@link CustomEnchant}s.
     * @return All registered enchantments.
     */
    @NotNull
    public abstract Collection<CustomEnchant> enchantments();

    /**
     * Returns the instance of the custom enchantment manager.
     * @return The instance of the custom enchantment manager.
     */
    @NotNull
    public static CustomEnchantManager instance() {
        return instance;
    }
}

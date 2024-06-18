package games.negative.pew.impl;

import com.google.common.collect.Sets;
import games.negative.pew.CustomEnchant;
import games.negative.pew.CustomEnchantManager;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link CustomEnchantManager}.
 */
public class CustomEnchantManagerImpl extends CustomEnchantManager {

    private final Set<CustomEnchant> enchants = Sets.newHashSet();

    public CustomEnchantManagerImpl() {
        instance = this;
    }

    @Override
    public void put(@NotNull CustomEnchant enchant) {
        enchants.add(enchant);
    }

    @Override
    public void init(@NotNull JavaPlugin plugin) {
        Registry<Enchantment> registry = RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT);

        for (CustomEnchant enchant : enchants) {
            Enchantment ench = registry.get(enchant.key());
            if (ench == null) {
                plugin.getLogger().warning("Enchantment not found: " + enchant.key());
                continue;
            }

            enchant.enchantment(ench);

            plugin.getServer().getPluginManager().registerEvents(enchant, plugin);
        }
    }

    @Override
    public @NotNull Collection<CustomEnchant> enchantments() {
        return enchants;
    }
}

package games.negative.pew;

import games.negative.pew.impl.CustomEnchantManagerImpl;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.event.RegistryEvents;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the bootstrapper for custom enchantments.
 */
public abstract class EnchantsBootstapper implements PluginBootstrap {

    /**
     * The custom enchantment manager.
     */
    private CustomEnchantManager enchants;

    /**
     * Invoked when the plugin is bootstrapping.
     * @param context the server provided context
     */
    @Override
    public void bootstrap(@NotNull BootstrapContext context) {
        this.enchants = new CustomEnchantManagerImpl();

        onBootstrap(context);

        context.getLifecycleManager().registerEventHandler(RegistryEvents.ENCHANTMENT.freeze().newHandler(event -> {
            for (CustomEnchant enchantment : enchants.enchantments()) {
                event.registry().register(
                        // The key of the registry
                        // Plugins should use their own namespace instead of minecraft or papermc
                        TypedKey.create(RegistryKey.ENCHANTMENT, enchantment.key()),
                        b -> enchantment.builder(event, b)
                );
            }
        }));
    }

    /**
     * Invoked after the enchantment manager has been initialized.
     * @param context the server provided context
     */
    public abstract void onBootstrap(@NotNull BootstrapContext context);

    /**
     * Returns the custom enchantment manager.
     * @return The custom enchantment manager.
     */
    @NotNull
    public CustomEnchantManager enchants() {
        return enchants;
    }
}

package games.negative.pew;

import io.papermc.paper.registry.data.EnchantmentRegistryEntry;
import io.papermc.paper.registry.event.RegistryFreezeEvent;
import net.kyori.adventure.key.Key;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.CheckReturnValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class CustomEnchant implements Listener {

    /**
     * The key of the custom enchantment.
     */
    private final Key key;

    /**
     * The enchantment instance of the custom enchantment.
     */
    protected Enchantment enchantment;

    /**
     * Constructs a new custom enchantment with the specified key.
     * @param key The key of the custom enchantment.
     */
    public CustomEnchant(@NotNull Key key) {
        this.key = key;
    }

    /**
     * Returns the key of the custom enchantment.
     * @return The key of the custom enchantment.
     */
    @NotNull
    public Key key() {
        return key;
    }

    /**
     * Returns the enchantment instance of the custom enchantment.
     * @return The enchantment instance of the custom enchantment.
     * @apiNote This method should only be used after {@link CustomEnchantManager#init(JavaPlugin)} is invoked.
     */
    @NotNull
    @CheckReturnValue
    public Enchantment enchantment() {
        return enchantment;
    }

    /**
     * Sets the enchantment instance of the custom enchantment.
     * @param enchantment The enchantment instance of the custom enchantment.
     */
    @ApiStatus.Internal
    public void enchantment(@NotNull Enchantment enchantment) {
        this.enchantment = enchantment;
    }

    public void onEnable() {
    }

    /**
     * Builds the custom enchantment registry entry.
     * @param event The registry freeze event.
     * @param builder The builder of the custom enchantment registry entry.
     * @return The builder of the custom enchantment registry entry.
     */
    @NotNull
    public abstract EnchantmentRegistryEntry.Builder builder(@NotNull RegistryFreezeEvent<Enchantment, EnchantmentRegistryEntry.Builder> event, @NotNull EnchantmentRegistryEntry.Builder builder);

    @NotNull
    public abstract List<EnchantmentTarget> targets();
}

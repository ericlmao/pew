package games.negative.pew.util;

import com.google.common.collect.Lists;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.set.RegistryKeySet;
import io.papermc.paper.registry.set.RegistrySet;
import io.papermc.paper.registry.tag.Tag;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class EnchantUtil {

    /**
     * Creates a {@link RegistryKeySet} from multiple {@link Tag}s
     *
     * @param values the tags to create the set from
     * @return the created set
     */
    @NotNull
    public static RegistryKeySet<ItemType> multipleItems(Tag<ItemType>... values) {
        Collection<TypedKey<ItemType>> keys = Lists.newArrayList();
        for (Tag<ItemType> value : values) {
            keys.addAll(value.values());
        }

        return RegistrySet.keySet(RegistryKey.ITEM, keys);
    }

}

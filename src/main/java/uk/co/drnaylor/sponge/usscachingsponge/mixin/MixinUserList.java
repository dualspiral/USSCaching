package uk.co.drnaylor.sponge.usscachingsponge.mixin;

import net.minecraft.server.management.UserList;
import net.minecraft.server.management.UserListEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.co.drnaylor.sponge.usscachingsponge.IMixinUserList;

import java.util.Map;

@Mixin(UserList.class)
public abstract class MixinUserList<K, V extends UserListEntry<K>> implements IMixinUserList<K, V> {

    @Shadow protected abstract Map<String, V> getValues();

    @Override
    public final Map<String, V> getValuesPublic() {
        return this.getValues();
    }
}

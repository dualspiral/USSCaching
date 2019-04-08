package uk.co.drnaylor.sponge.usscachingsponge.mixin;

import net.minecraft.server.management.UserListEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.co.drnaylor.sponge.usscachingsponge.IMixinUserListEntry;

@Mixin(UserListEntry.class)
public abstract class MixinUserListEntry<T> implements IMixinUserListEntry<T> {

    @Shadow @Final private T value;

    @Override
    public T getValuePublic() {
        return this.value;
    }
}

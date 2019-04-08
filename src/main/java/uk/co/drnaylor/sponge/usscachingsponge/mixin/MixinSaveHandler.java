package uk.co.drnaylor.sponge.usscachingsponge.mixin;

import net.minecraft.world.storage.SaveHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.co.drnaylor.sponge.usscachingsponge.IMixinUSSSaveHandler;

import java.io.File;
import java.nio.file.Path;

@Mixin(SaveHandler.class)
public abstract class MixinSaveHandler implements IMixinUSSSaveHandler {

    @Shadow @Final private File playersDirectory;

    public Path getPlayerSaveDirectory() {
        return this.playersDirectory.toPath();
    }

    public File getPlayerSaveDirectoryAsFile() {
        return this.playersDirectory;
    }
}

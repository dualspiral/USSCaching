package uk.co.drnaylor.sponge.usscachingsponge;

import java.io.File;
import java.nio.file.Path;

public interface IMixinUSSSaveHandler {

    Path getPlayerSaveDirectory();

    File getPlayerSaveDirectoryAsFile();
}

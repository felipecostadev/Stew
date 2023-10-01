package com.minecraft.stew.core.util.updater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarFile;
import java.util.logging.Logger;

public class PluginUpdater {

    protected final File updateDirectory = new File("");

    private final File serverPluginFile;
    private final File updaterPluginFile;

    private boolean updated;

    public PluginUpdater(File serverPluginFile) {
        this.serverPluginFile = serverPluginFile;
        this.updaterPluginFile = new File(updateDirectory, serverPluginFile.getName());
    }

    public boolean isUpdated() {
        return updated;
    }

    private boolean hasUpdate() {
        return updaterPluginFile.isFile() && updaterPluginFile.lastModified() > serverPluginFile.lastModified() && validate(updaterPluginFile);
    }

    public boolean verify(Runnable runnable) {
        Logger.getGlobal().info("Searching for updates...");

        if (hasUpdate()) {
            Logger.getGlobal().info("Update found!");
            update(runnable);
            return true;
        } else {
            Logger.getGlobal().info("No updates found, skipping!");
        }
        return false;
    }

    private void update(Runnable afterUpdate) {
        try {
            Files.copy(updaterPluginFile.toPath(), serverPluginFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            updated = true;

            afterUpdate.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean validate(File file) {
        try (JarFile jar = new JarFile(file)) {
            return jar.getJarEntry("plugin.yml") != null;
        } catch (IOException e) {
            return false;
        }
    }
}
package com.minecraft.stew.core.bukkit;

import com.minecraft.stew.core.bukkit.command.BukkitCommandFramework;
import com.minecraft.stew.core.bukkit.command.CommandInterface;
import com.minecraft.stew.core.bukkit.command.executor.BukkitSchedulerExecutor;
import com.minecraft.stew.core.util.loader.ClassLoader;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class BukkitGame extends JavaPlugin {

    private BukkitCommandFramework commandFramework;

    public static BukkitGame getPlugin() {
        return BukkitGame.getPlugin(BukkitGame.class);
    }

    @Override
    public void onEnable() {

        //registrar adapter sempre que tiver um novo argumento (ex: tag)

        this.commandFramework = new BukkitCommandFramework(this);
        this.commandFramework.setExecutor(new BukkitSchedulerExecutor(this));

        for (Class<?> commandClass : ClassLoader.withPackageByFile(getFile(), "com.minecraft.stew.core.bukkit.command.handle")) {
            if (!CommandInterface.class.isAssignableFrom(commandClass))
                continue;

            try {
                CommandInterface commandInterface = (CommandInterface) commandClass.newInstance();

                commandFramework.registerCommands(commandInterface);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
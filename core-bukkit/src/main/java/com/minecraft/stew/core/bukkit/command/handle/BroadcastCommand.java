package com.minecraft.stew.core.bukkit.command.handle;

import com.minecraft.stew.core.bukkit.command.CommandInterface;
import com.minecraft.stew.core.command.annotation.Command;
import com.minecraft.stew.core.command.function.Context;
import com.minecraft.stew.core.command.platform.Platform;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandInterface {

    @Command(name = "broadcast", aliases = {"bc", "warn", "aviso"}, usage = "broadcast <message>", platform = Platform.PLAYER)
    public void handleCommand(Context<Player> context, String[] message) {
        context.sendMessage("§b§lSTEW §7» §e%s", ChatColor.translateAlternateColorCodes('&', String.join(" ", message)));
    }
}
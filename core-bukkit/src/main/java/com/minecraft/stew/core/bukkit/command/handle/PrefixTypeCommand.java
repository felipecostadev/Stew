package com.minecraft.stew.core.bukkit.command.handle;

import com.minecraft.stew.core.account.prefixtype.PrefixType;
import com.minecraft.stew.core.account.tag.Tag;
import com.minecraft.stew.core.bukkit.command.CommandInterface;
import com.minecraft.stew.core.command.annotation.Command;
import com.minecraft.stew.core.command.annotation.Completer;
import com.minecraft.stew.core.command.function.Context;
import com.minecraft.stew.core.command.platform.Platform;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class PrefixTypeCommand implements CommandInterface {

    @Command(name = "prefixtype", usage = "prefixtype <type>", platform = Platform.PLAYER)
    public void handleCommand(Context<Player> context, PrefixType prefixType) {
        if (prefixType == null) {
            context.sendMessage("§c\"prefixType\": Este prefix type não foi encontrado.");
            return;
        }
        context.sendMessage("§eExemplo das tags após a mudança:");
        context.sendMessage("");
        context.sendMessage(Tag.ADMINISTRATOR.getPrefix(prefixType) + context.getSender().getName());
        context.sendMessage(Tag.MODERATOR.getPrefix(prefixType) + context.getSender().getName());
        context.sendMessage(Tag.PARTNER.getPrefix(prefixType) + context.getSender().getName());
        context.sendMessage(Tag.ELITE.getPrefix(prefixType) + context.getSender().getName());
        context.sendMessage(Tag.PRO.getPrefix(prefixType) + context.getSender().getName());
        context.sendMessage(Tag.MVP.getPrefix(prefixType) + context.getSender().getName());
        context.sendMessage(Tag.VIP.getPrefix(prefixType) + context.getSender().getName());
        context.sendMessage("");
        context.sendMessage("§aVocê alterou seu prefix type para %s.", prefixType.name().toLowerCase());
    }

    @Completer(name = "prefixtype")
    public List<String> handleCompleter(Context<Player> context) {
        return Collections.emptyList();
    }
}
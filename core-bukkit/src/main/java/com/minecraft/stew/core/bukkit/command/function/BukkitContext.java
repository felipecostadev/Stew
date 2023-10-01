/*
 * Copyright 2020 Luiz Carlos Mourão Paes de Carvalho
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.minecraft.stew.core.bukkit.command.function;

import com.minecraft.stew.core.account.Account;
import com.minecraft.stew.core.bukkit.command.platform.BukkitPlatformValidator;
import com.minecraft.stew.core.command.CommandFramework;
import com.minecraft.stew.core.command.exception.CommandException;
import com.minecraft.stew.core.command.function.CommandHolder;
import com.minecraft.stew.core.command.function.Context;
import com.minecraft.stew.core.command.message.MessageType;
import com.minecraft.stew.core.command.platform.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;

/**
 * @author Luiz Carlos Mourão
 */
@Getter
@AllArgsConstructor
public class BukkitContext implements Context<CommandSender> {

    private final String label;
    private final CommandSender sender;
    private final Platform platform;
    private final Account account;
    private final String[] args;

    private final CommandFramework<?, ?, ?> commandFramework;
    private final CommandHolder<?, ?> commandHolder;

    /**
     * Sends a message to the CommandSender
     *
     * @param message the message to be sent
     */
    @Override
    public void sendMessage(String message) {
        sender.sendMessage(message);
    }

    /**
     * Sends a array of messages to the CommandSender
     *
     * @param messages the messages to be sent
     */
    @Override
    public void sendMessage(String[] messages) {
        sender.sendMessage(messages);
    }

    /**
     * Tests a permission into the CommandSender.
     * <p>Returns true if it was successful</p>
     *
     * @param permission the permission to be tested
     * @param silent     whether a exception should be thrown
     * @return boolenn
     * @throws CommandException Throws if the player doesn't have permission and the test isn't silent.
     */
    @Override
    public boolean testPermission(String permission, boolean silent) throws CommandException {
        if (sender.hasPermission(permission)) {
            return true;
        }

        if (!silent) {
            throw new CommandException(MessageType.NO_PERMISSION, permission);
        }

        return false;
    }

    /**
     * Validates if the target is a {@link Platform}
     * <p>Returns true if the test was successful</p>
     *
     * @param platform the target to be tested
     * @param silent whether a exception should be thrown
     * @return boolean
     * @throws CommandException Throws if the validation was wrong and the test isn't silent.
     */
    @Override
    public boolean testPlatform(Platform platform, boolean silent) throws CommandException {
        if (BukkitPlatformValidator.INSTANCE.validate(platform, sender))
            return true;

        if (!silent)
            throw new CommandException(MessageType.INCORRECT_USAGE, platform.name());

        return false;
    }
}
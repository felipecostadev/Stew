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

package com.minecraft.stew.core.bukkit.command.platform;

import com.minecraft.stew.core.command.platform.Platform;
import com.minecraft.stew.core.command.platform.PlatformValidator;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * The BukkitTargetValidator validates if the Target
 * is a correct and usable {@link Platform}
 *
 * @author Luiz Carlos Mourão
 */
public final class BukkitPlatformValidator implements PlatformValidator {

    public static final BukkitPlatformValidator INSTANCE = new BukkitPlatformValidator();

    /**
     * Tries to validate the Command target and Sender object.
     * <p> Returns false if it wasn't validated</p>
     * @param platform Platform
     * @param object Object
     *
     * @return Boolean
     */
    @Override
    public boolean validate(Platform platform, Object object) {
        if (platform.equals(Platform.BOTH)) {
            return true;
        }

        if (platform.equals(Platform.PLAYER) && object instanceof Player) {
            return true;
        }

        return platform.equals(Platform.TERMINAL) && object instanceof ConsoleCommandSender;
    }

    /**
     * Returns the CommandTarget by the Sender object
     * <p>Example: The Player object returns a {@link Platform} of PLAYER</p>
     * @param object Object
     *
     * @return Platform
     */
    @Override
    public Platform fromSender(Object object) {
        if (object instanceof Player) {
            return Platform.PLAYER;
        }

        if (object instanceof ConsoleCommandSender) {
            return Platform.TERMINAL;
        }
        return Platform.BOTH;
    }
}
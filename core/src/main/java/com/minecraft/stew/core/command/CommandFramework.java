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

package com.minecraft.stew.core.command;

import com.minecraft.stew.core.command.argument.AdapterMap;
import com.minecraft.stew.core.command.argument.TypeAdapter;
import com.minecraft.stew.core.command.argument.eval.MethodEvaluator;
import com.minecraft.stew.core.command.function.CommandHolder;
import com.minecraft.stew.core.command.function.CommandInfo;
import com.minecraft.stew.core.command.executor.CommandExecutor;
import com.minecraft.stew.core.command.executor.CompleterExecutor;
import com.minecraft.stew.core.command.message.MessageHolder;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * The CommandFrame is the core of the framework,
 * it registers the commands, adapters {@link AdapterMap}
 * and message holders {@link MessageHolder}
 *
 * @author Luiz Carlos Mourão
 */
public interface CommandFramework<P, S, C extends CommandHolder<S, ? extends C>> {

    P getPlugin();
    AdapterMap getAdapterMap();
    MessageHolder getMessageHolder();

    Map<String, C> getCommandMap();
    MethodEvaluator getMethodEvaluator();

    @Nullable
    Executor getExecutor();

    C getCommand(String name);

    /**
     * Registers a new Adapter from that type.
     * @param type Class
     * @param adapter TypeAdapter
     * @param <T> Generic value for the type
     */
    default <T> void registerAdapter(Class<T> type, TypeAdapter<T> adapter) {
        getAdapterMap().put(type, adapter);
    }

    /**
     * Registers multiple command object once
     * @param objects Object...
     */
    void registerCommands(Object... objects);

    /**
     * Registers a single command with the CommandInfo and Executor
     * @param commandInfo CommandInfo
     * @param commandExecutor CommandExecutor
     */
    void registerCommand(CommandInfo commandInfo, CommandExecutor<S> commandExecutor);
    void registerCompleter(String name, CompleterExecutor<S> completerExecutor);

    /**
     * Unregisters the command with the provided name
     * @param name String
     *
     * @return Boolean
     */
    boolean unregisterCommand(String name);
}
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.minecraft.stew.core.command.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtil {

    public static boolean startsWithIgnoreCase(String str, String prefix) {
        return startsWith(str, prefix, true);
    }

    private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
        if (str == null || prefix == null) return (str == null && prefix == null);
        if (prefix.length() > str.length()) return false;

        return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) return 0;

        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }

        return count;
    }

    public static String uncapitalize(String str) {
        if (str == null || str.length() == 0) return str;
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}

package com.minecraft.stew.core.account.storage;

import com.minecraft.stew.core.account.Account;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class AccountStorage {

    private final Map<UUID, Account> accountMap = new ConcurrentHashMap<>();

    public Account get(UUID uniqueId) {
        return accountMap.get(uniqueId);
    }

    public void store(Account account) {
        accountMap.put(account.getUniqueId(), account);
    }

    public void remove(UUID uniqueId) {
        accountMap.remove(uniqueId);
    }
}
package com.minecraft.stew.core.account;

import com.minecraft.stew.core.account.rank.Rank;
import lombok.Getter;

import java.util.UUID;

@Getter
public final class Account {

    private UUID uniqueId;

    private Rank rank = Rank.DEFAULT;
}
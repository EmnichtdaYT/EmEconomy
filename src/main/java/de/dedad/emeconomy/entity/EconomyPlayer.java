package de.dedad.emeconomy.entity;

import de.dedad.emeconomy.EmEconomyMain;
import de.dedad.emeconomy.account.PocketCash;
import de.dedad.emeconomy.account.PrivateBankAccount;
import lombok.Getter;

import java.util.UUID;

public class EconomyPlayer {

    @Getter
    private final PocketCash cash;
    @Getter
    private final PrivateBankAccount bankAccount;
    @Getter
    private final UUID player;

    public EconomyPlayer(UUID player) {
        this.player = player;
        cash = new PocketCash(player, EmEconomyMain.getPlugin());
        bankAccount = new PrivateBankAccount(player, EmEconomyMain.getPlugin());
    }

}

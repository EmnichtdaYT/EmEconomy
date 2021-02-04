package de.dedad.emeconomy.entity;

import de.dedad.emeconomy.EmEconomyMain;
import de.dedad.emeconomy.account.PocketCash;
import de.dedad.emeconomy.account.PrivateBankAccount;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.UUID;

public class EconomyPlayer {

    @Getter
    private PocketCash cash;
    @Getter
    private PrivateBankAccount bankAccount;
    @Getter
    private UUID player;

    public EconomyPlayer(UUID player) {
        this.player = player;
        cash = new PocketCash(player, EmEconomyMain.getPlugin());
        bankAccount = new PrivateBankAccount(player, EmEconomyMain.getPlugin());
    }

}

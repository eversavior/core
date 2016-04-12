package game.start;

import game.data.PlayerData;

/**
 * Created on 11.04.2016.
 */
public class FightService
{
    public Boolean doFight(PlayerData player1, PlayerData player2)
    {
        return Math.random() > 0.5;
    }
}

package dev.velasquez.javatest.player;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void loosesWhenDiceNumberIsLow() {
//        Dice dice = new Dice(6);
        Dice dice = Mockito.mock(Dice.class);

        // Similar el dato, si alguien llama a dice.role(), retorna 2
        Mockito.when(dice.roll()).thenReturn(2);

        Player player = new Player(dice, 3);
        assertFalse(player.play());
    }

    @Test
    public void winsWhenDiceNumberIsBig() {
        Dice dice = Mockito.mock(Dice.class);

        // Simila el dado, si alguien llama a dice.role(), retorna 4
        Mockito.when(dice.roll()).thenReturn(4);

        Player player = new Player(dice, 3);
        assertTrue(player.play());
    }
}
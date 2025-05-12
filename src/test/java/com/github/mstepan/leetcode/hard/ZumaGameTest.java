package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.ZumaGame.findMinStep;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ZumaGameTest {

    @Test
    void findMinStepCase1() {
        assertEquals(-1, findMinStep("WRRBBW", "RB"));
    }

    @Test
    void findMinStepCase2() {
        assertEquals(2, findMinStep("WWRRBBWW", "WRBRW"));
    }

    @Test
    void findMinStepCase3() {
        assertEquals(2, findMinStep("G", "GGGGG"));
    }

    @Test
    void findMinStepCase4() {
        assertEquals(2, findMinStep("RRWWRRBBRR", "WB"));
    }

    // Time elapsed: 811.3 ms
    // Time elapsed: 685.1 ms
    // Time elapsed: 570.0 ms
    // Time elapsed: 522.0 ms
    @Test
    void findMinStepCase5() {
        long startTime = System.nanoTime();
        assertEquals(-1, findMinStep("RRGGBBYYWWRRGGBB", "RGBYW"));
        long endTime = System.nanoTime();
        System.out.printf("Time elapsed: %.1f ms%n", (endTime - startTime) / 1_000_000.0);
    }

    // Time elapsed: 108.4 ms
    @Test
    void findMinStepCase6() {
        long startTime = System.nanoTime();
        assertEquals(-1, findMinStep("WWRBBWWGGBBRRGWB", "WGGBB"));
        long endTime = System.nanoTime();
        System.out.printf("Time elapsed: %.1f ms%n", (endTime - startTime) / 1_000_000.0);
    }

    @Test
    void findInsertPlaces() {
        assertThat(ZumaGame.findInsertPlaces("WWRBBRWBBWWB", 'W'))
                .hasSize(3)
                .containsExactly(0, 6, 9);

        assertThat(ZumaGame.findInsertPlaces("WWRBBRWBBWWB", 'B'))
                .hasSize(3)
                .containsExactly(3, 7, 11);

        assertThat(ZumaGame.findInsertPlaces("WWRBBRWBBWWB", 'R')).hasSize(2).containsExactly(2, 5);

        assertThat(ZumaGame.findInsertPlaces("WWRBBRWBBWWB", 'Y')).isEmpty();
    }

    @Test
    void removeCharAt() {
        assertEquals("RBRW", ZumaGame.removeCharAt("WRBRW", 0));
        assertEquals("WBRW", ZumaGame.removeCharAt("WRBRW", 1));
        assertEquals("WRRW", ZumaGame.removeCharAt("WRBRW", 2));
        assertEquals("WRBW", ZumaGame.removeCharAt("WRBRW", 3));
        assertEquals("WRBR", ZumaGame.removeCharAt("WRBRW", 4));
    }

    @Test
    void calculateNextBoard() {
        assertEquals("RBBW", ZumaGame.calculateNextBoard("WWRBBW", 0, 'W'));
        assertEquals("WWRBBWW", ZumaGame.calculateNextBoard("WWRBBW", 5, 'W'));
        assertEquals("WWRW", ZumaGame.calculateNextBoard("WWRBBW", 3, 'B'));
        assertEquals("", ZumaGame.calculateNextBoard("WWRRW", 2, 'R'));
        assertEquals("BBW", ZumaGame.calculateNextBoard("WWRRWBBW", 2, 'R'));
        assertEquals("WBB", ZumaGame.calculateNextBoard("WBBWWRRW", 5, 'R'));
        assertEquals("WBBWW", ZumaGame.calculateNextBoard("WBBWRRW", 4, 'R'));
        assertEquals("WBBWWRRW", ZumaGame.calculateNextBoard("WBBWWRW", 5, 'R'));
        assertEquals("", ZumaGame.calculateNextBoard("WBBWWRRWBWW", 5, 'R'));
    }
}

package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.WordsWithinTwoEditsOfDictionary.twoEditWords;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class WordsWithinTwoEditsOfDictionaryTest {

    @Test
    void twoEditWordsCase1() {
        assertEquals(
                List.of("tsl", "yyy", "rbc", "dda", "qus", "hyb", "ilu"),
                twoEditWords(
                        new String[] {
                            "tsl", "sri", "yyy", "rbc", "dda", "qus", "hyb", "ilu", "ahd"
                        },
                        new String[] {"uyj", "bug", "dba", "xbe", "blu", "wuo", "tsf", "tga"}));
    }
}

package com.github.mstepan.leetcode.other;

import java.text.BreakIterator;
import java.text.Normalizer;
import java.util.Objects;

public final class StringUtils {

    private StringUtils() {}

    /**
     * Check if the second string is the reverse of the first by grapheme clusters, allowing
     * canonically equivalent Unicode encodings.
     *
     * <p>time: O(N)
     *
     * <p>space: O(N)
     */
    public static boolean isReversed(String first, String second) {
        Objects.requireNonNull(first, "'first' is null");
        Objects.requireNonNull(second, "'second' is null");

        first = Normalizer.normalize(first, Normalizer.Form.NFC);
        second = Normalizer.normalize(second, Normalizer.Form.NFC);

        StringBuilder firstReversed = new StringBuilder();

        BreakIterator firstIt = BreakIterator.getCharacterInstance();
        firstIt.setText(first);

        int end = firstIt.last();

        for (int start = firstIt.previous();
                start != BreakIterator.DONE;
                end = start, start = firstIt.previous()) {

            firstReversed.append(first, start, end);
        }

        return Normalizer.normalize(firstReversed, Normalizer.Form.NFC).equals(second);
    }
}

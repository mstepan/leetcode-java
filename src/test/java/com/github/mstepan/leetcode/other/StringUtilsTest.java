package com.github.mstepan.leetcode.other;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void acceptsReversedText() {
        assertTrue(StringUtils.isReversed("hello", "olleh"));
        assertTrue(StringUtils.isReversed("привет :)", "): тевирп"));
    }

    @Test
    void rejectsTextThatIsNotReversed() {
        assertFalse(StringUtils.isReversed("hello", "hello"));
        assertFalse(StringUtils.isReversed("ab", "a"));
    }

    @Test
    void handlesEmptyStrings() {
        assertTrue(StringUtils.isReversed("", ""));
        assertFalse(StringUtils.isReversed("", "a"));
        assertFalse(StringUtils.isReversed("a", ""));
    }

    @Test
    void acceptsCanonicallyEquivalentAccents() {
        assertTrue(StringUtils.isReversed("\u00E9", "e\u0301"));
        assertTrue(StringUtils.isReversed("e\u0301x", "x\u00E9"));
    }

    @Test
    void keepsCombiningMarksWithTheirBaseCharacter() {
        assertTrue(StringUtils.isReversed("q\u0301x", "xq\u0301"));
        assertFalse(StringUtils.isReversed("q\u0301x", "x\u0301q"));
    }

    @Test
    void keepsSupplementaryCharactersIntact() {
        assertTrue(StringUtils.isReversed("привет 😄", "😄 тевирп"));
    }

    @Test
    void keepsFlagSequencesIntact() {
        assertTrue(StringUtils.isReversed("🇬🇺a", "a🇬🇺"));
        assertFalse(StringUtils.isReversed("🇬🇺", "🇺🇬"));
    }

    @Test
    void keepsJoinedEmojiIntact() {
        assertTrue(StringUtils.isReversed("a👩‍💻", "👩‍💻a"));
    }

    @Test
    void rejectsCompatibilityEquivalentCharacters() {
        assertFalse(StringUtils.isReversed("①", "1"));
    }

    @Test
    void preservesLigaturesWhenReversing() {
        assertTrue(StringUtils.isReversed("\uFB01a", "a\uFB01"));
    }

    @Test
    void normalizesCombiningMarksAfterReversal() {
        assertTrue(StringUtils.isReversed("\u0301a", "a\u0301"));
        assertTrue(StringUtils.isReversed("\u0301a", "\u00E1"));
    }

    @Test
    void rejectsNullFirstString() {
        assertThrows(NullPointerException.class, () -> StringUtils.isReversed(null, ""));
    }

    @Test
    void rejectsNullSecondString() {
        assertThrows(NullPointerException.class, () -> StringUtils.isReversed("", null));
    }
}

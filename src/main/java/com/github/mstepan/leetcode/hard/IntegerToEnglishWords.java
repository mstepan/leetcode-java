package com.github.mstepan.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 273. Integer to English Words
 *
 * <p>https://leetcode.com/problems/integer-to-english-words/description/
 */
public class IntegerToEnglishWords {

    // num = 2_123_507_101
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String numStr = String.valueOf(num); // "2_123_507_101"

        int to = numStr.length() - 1;
        int from = Math.max(0, to - 2);

        Suffix suffix = Suffix.HUNDRED;

        Deque<String> partialResult = new ArrayDeque<>();

        while (true) {
            String chunkWord = convertChunkOfDigits(numStr, from, to);

            if (!chunkWord.isEmpty()) {
                chunkWord += " " + suffix.title;
            }

            if (!chunkWord.isEmpty()) {
                partialResult.push(chunkWord);
            }

            to = from - 1;
            if (to < 0) {
                break;
            }

            from = Math.max(0, to - 2);
            suffix = suffix.next();
        }

        StringBuilder result = new StringBuilder();
        while (!partialResult.isEmpty()) {
            result.append(" ").append(partialResult.pop());
        }

        return result.toString().trim();
    }

    enum Suffix {
        HUNDRED(""),
        THOUSAND("Thousand"),
        MILLION("Million"),
        BILLION("Billion");

        private final String title;

        Suffix(String title) {
            this.title = title;
        }

        public Suffix next() {
            return switch (this) {
                case HUNDRED -> THOUSAND;
                case THOUSAND -> MILLION;
                case MILLION -> BILLION;
                default -> throw new IllegalStateException(
                        "Can't find next suffix for %s".formatted(this));
            };
        }
    }

    private static String convertChunkOfDigits(String numStr, int from, int to) {
        if (allZeros(numStr, from, to)) {
            return "";
        }

        // skip all leading zeros
        while (numStr.charAt(from) == '0') {
            ++from;
        }

        int digitsCnt = to - from + 1;

        if (digitsCnt == 3) {
            return (convert1Digit(numStr.charAt(from))
                            + " Hundred "
                            + convert2Digits(numStr, from + 1, to).trim())
                    .trim();
        } else if (digitsCnt == 2) {
            return convert2Digits(numStr, from, to).trim();
        }
        return convert1Digit(numStr.charAt(from)).trim();
    }

    private static boolean allZeros(String numStr, int from, int to) {
        for (int i = from; i <= to; ++i) {
            if (numStr.charAt(i) != '0') {
                return false;
            }
        }

        return true;
    }

    private static String convert1Digit(char ch) {
        return switch (ch) {
            case '0' -> "";
            case '1' -> "One";
            case '2' -> "Two";
            case '3' -> "Three";
            case '4' -> "Four";
            case '5' -> "Five";
            case '6' -> "Six";
            case '7' -> "Seven";
            case '8' -> "Eight";
            case '9' -> "Nine";
            default -> throw new IllegalStateException(
                    "Can't convert SINGLE character '%c' to digit".formatted(ch));
        };
    }

    private static String convert2Digits(String numStr, int from, int to) {

        if (allZeros(numStr, from, to)) {
            return "";
        }

        while (numStr.charAt(from) == '0') {
            ++from;
        }

        int digitsCnt = to - from + 1;

        if (digitsCnt == 1) {
            return convert1Digit(numStr.charAt(from));
        }

        int mostSignificantDigit = numStr.charAt(from) - '0';

        if (mostSignificantDigit == 1) {

            String sub = numStr.substring(from, to + 1);

            // 10, 11, ..., 19
            return switch (sub) {
                case "10" -> "Ten";
                case "11" -> "Eleven";
                case "12" -> "Twelve";
                case "13" -> "Thirteen";
                case "14" -> "Fourteen";
                case "15" -> "Fifteen";
                case "16" -> "Sixteen";
                case "17" -> "Seventeen";
                case "18" -> "Eighteen";
                case "19" -> "Nineteen";
                default -> throw new IllegalStateException(
                        "Can't convert 2 DIGITS string: %s".formatted(sub));
            };
        } else {
            // 20, 30, ...., 90
            String firstPart =
                    switch (mostSignificantDigit) {
                        case 2 -> "Twenty";
                        case 3 -> "Thirty";
                        case 4 -> "Forty";
                        case 5 -> "Fifty";
                        case 6 -> "Sixty";
                        case 7 -> "Seventy";
                        case 8 -> "Eighty";
                        case 9 -> "Ninety";
                        default -> throw new IllegalStateException(
                                "Can't convert 3 DIGITS string: %s");
                    };

            return (firstPart + " " + convert1Digit(numStr.charAt(to)).trim()).trim();
        }
    }
}

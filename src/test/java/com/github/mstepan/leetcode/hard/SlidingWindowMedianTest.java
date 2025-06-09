package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.SlidingWindowMedian.MovingMedian;
import static com.github.mstepan.leetcode.hard.SlidingWindowMedian.MovingMedian.parentIdx;
import static com.github.mstepan.leetcode.hard.SlidingWindowMedian.medianSlidingWindow;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

public class SlidingWindowMedianTest {

    private static final Percentage PERCENTAGE = Percentage.withPercentage(0.00001);

    @Test
    void movingMedianAddOperation() {
        MovingMedian movingMedian = new MovingMedian(5);
        // 20
        movingMedian.add(20);
        assertThat(movingMedian.median()).isCloseTo(20.0, PERCENTAGE);

        // 13, 20
        movingMedian.add(13);
        assertThat(movingMedian.median()).isCloseTo((13.0 + 20.0) / 2.0, PERCENTAGE);

        // 7, 13, 20
        movingMedian.add(7);
        assertThat(movingMedian.median()).isCloseTo(13.0, PERCENTAGE);

        // 7, 9, 13, 20
        movingMedian.add(9);
        assertThat(movingMedian.median()).isCloseTo((9.0 + 13.0) / 2.0, PERCENTAGE);

        // 7, 9, 13, 16, 20
        movingMedian.add(16);
        assertThat(movingMedian.median()).isCloseTo(13.0, PERCENTAGE);
    }

    @Test
    void movingMedianRemoveOperation() {
        MovingMedian movingMedian = new MovingMedian(8);

        // 1, 4, 6, 10, 13, 16, 17, 20
        movingMedian.add(16);
        movingMedian.add(4);
        movingMedian.add(10);
        movingMedian.add(6);
        movingMedian.add(17);
        movingMedian.add(20);
        movingMedian.add(1);
        movingMedian.add(13);

        assertThat(movingMedian.median()).isCloseTo((10.0 + 13.0) / 2.0, PERCENTAGE);

        // 1, 4, 6, 10, 13, 16, 20
        movingMedian.remove(17);
        assertThat(movingMedian.median()).isCloseTo(10.0, PERCENTAGE);

        // 1, 4, 6, 13, 16, 20
        movingMedian.remove(10);
        assertThat(movingMedian.median()).isCloseTo((6.0 + 13.0) / 2.0, PERCENTAGE);

        // 1, 4, 6, 13, 16
        movingMedian.remove(20);
        assertThat(movingMedian.median()).isCloseTo(6.0, PERCENTAGE);

        // 4, 6, 13, 16
        movingMedian.remove(1);
        assertThat(movingMedian.median()).isCloseTo((6.0 + 13.0) / 2.0, PERCENTAGE);

        // 4, 13, 16
        movingMedian.remove(6);
        assertThat(movingMedian.median()).isCloseTo(13.0, PERCENTAGE);

        // 4, 16
        movingMedian.remove(13);
        assertThat(movingMedian.median()).isCloseTo((4.0 + 16.0) / 2.0, PERCENTAGE);

        // 4
        movingMedian.remove(16);
        assertThat(movingMedian.median()).isCloseTo(4.0, PERCENTAGE);

        movingMedian.remove(4);
    }

    @Test
    void movingMedianMedianOnEmptyShouldFail() {
        MovingMedian movingMedian = new MovingMedian(8);

        // 4
        movingMedian.add(4);
        assertThat(movingMedian.median()).isCloseTo(4.0, PERCENTAGE);

        movingMedian.remove(4);

        assertThatThrownBy(movingMedian::median)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("No elements available to calculate median");
    }

    @Test
    void medianSlidingWindowCase1() {
        assertThat(medianSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3))
                .containsExactly(1.00000, -1.00000, -1.00000, 3.00000, 5.00000, 6.00000);
    }

    @Test
    void medianSlidingWindowCase2() {
        assertThat(medianSlidingWindow(new int[] {1, 2, 3, 4, 2, 3, 1, 4, 2}, 3))
                .containsExactly(2.00000, 3.00000, 3.00000, 3.00000, 2.00000, 3.00000, 2.00000);
    }

    @Test
    void medianSlidingWindowCase3() {
        assertThat(medianSlidingWindow(new int[] {2147483647, 1, 2, 3, 4, 5, 6, 7, 2147483647}, 2))
                .containsExactly(1.073741824E9, 1.5, 2.5, 3.5, 4.5, 5.5, 6.5, 1.073741827E9);
    }

    @Test
    void medianSlidingWindowCase4() {
        assertThat(medianSlidingWindow(new int[] {5, 2, 2, 7, 3, 7, 9, 0, 2, 3}, 9))
                .containsExactly(3.00000, 3.00000);
    }

    @Test
    void parentIdxCase1() {
        assertThat(parentIdx(4)).isEqualTo(1);
    }
}

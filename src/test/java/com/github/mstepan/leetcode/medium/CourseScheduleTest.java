package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.CourseSchedule.canFinish;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CourseScheduleTest {

    @Test
    void allCanBeVisited() {
        assertTrue(
                canFinish(
                        5,
                        new int[][] {
                            {1, 0},
                            {2, 1},
                            {3, 1},
                            {4, 2},
                            {4, 3},
                        }));
    }

    @Test
    void notAllCanBeVisited() {
        assertFalse(
                canFinish(
                        5,
                        new int[][] {
                            {1, 0},
                            {2, 1},
                            {3, 1},
                            {4, 2},
                            {4, 3},
                            {1, 4}
                        }));
    }

    @Test
    void singleCourseNoPrerequisites() {
        assertTrue(canFinish(1, new int[][] {}));
    }

    @Test
    void noPrerequisites() {
        assertTrue(canFinish(2, new int[][] {}));
    }

    @Test
    void twoCoursesWithSimpleCycle() {
        assertFalse(canFinish(2, new int[][] {{1, 0}, {0, 1}}));
    }

    @Test
    void selfDependencyCreatesCycle() {
        assertFalse(canFinish(3, new int[][] {{0, 0}}));
    }

    @Test
    void disconnectedGraphWithCycleComponent() {
        assertFalse(canFinish(6, new int[][] {{1, 0}, {2, 1}, {4, 3}, {3, 4}}));
    }

    @Test
    void disconnectedAcyclicGraph() {
        assertTrue(canFinish(6, new int[][] {{1, 0}, {2, 1}, {4, 3}}));
    }

    @Test
    void duplicatePrerequisitesStillCompletable() {
        assertTrue(canFinish(2, new int[][] {{1, 0}, {1, 0}}));
    }

    @Test
    void invalidNumCoursesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> canFinish(0, new int[][] {}));
    }

    @Test
    void nullPrerequisitesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> canFinish(2, null));
    }
}

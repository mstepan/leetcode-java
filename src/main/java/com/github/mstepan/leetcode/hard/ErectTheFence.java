package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 587. Erect the Fence
 *
 * <p>https://leetcode.com/problems/erect-the-fence
 */
public class ErectTheFence {

    /**
     * Uses Graham's scan to construct convex hull.
     *
     * <p>time: O(N*lgN) space: O(N)
     *
     * @param treesArr - initial unsorted array of trees
     * @return - convex hull value
     */
    public static int[][] outerTrees(int[][] treesArr) {
        Objects.requireNonNull(treesArr, "'treesArr' is NULL'");
        if (treesArr.length < 4) return treesArr;

        Tree[] trees = toTrees(treesArr);
        Tree pivot = findPivot(trees);

        Comparator<Tree> angleCmp = Tree.angleToPivotComparator(pivot);
        Arrays.sort(trees, angleCmp);

        Deque<Tree> convexHull = new ArrayDeque<>();
        convexHull.push(trees[0]);
        convexHull.push(trees[1]);
        convexHull.push(trees[2]);

        for (int i = 3; i < trees.length; i++) {

            Tree c = trees[i];

            while (convexHull.size() > 2) {
                Tree b = convexHull.pop();
                Tree a = convexHull.peek();

                if (!isLeftTurn(a, b, c)) {
                    convexHull.push(b);
                    break;
                }
            }

            convexHull.push(c);
        }

        return to2DArray(convexHull);
    }

    private static boolean isLeftTurn(Tree a, Tree b, Tree c) {
        return Tree.crossProduct(a, b, c) > 0;
    }

    private static Tree[] toTrees(int[][] treesArr) {
        assert treesArr != null : "'treesArr' is NULL";

        Tree[] trees = new Tree[treesArr.length];

        for (int i = 0; i < treesArr.length; i++) {
            trees[i] = new Tree(treesArr[i][0], treesArr[i][1]);
        }

        return trees;
    }

    private static int[][] to2DArray(Deque<Tree> convexHull) {
        int[][] arr = new int[convexHull.size()][2];

        int i = 0;
        for (Tree tree : convexHull) {
            arr[i][0] = tree.x;
            arr[i][1] = tree.y;
            ++i;
        }

        return arr;
    }

    /**
     * Find elements with smallest Y and if there is a tight smallest X value.
     *
     * @param trees - all trees unsorted
     * @return pivot tree with smallest Y and X values.
     */
    static Tree findPivot(Tree[] trees) {
        assert trees != null && trees.length > 0;
        Tree pivot = trees[0];

        for (int i = 1; i < trees.length; i++) {
            Tree other = trees[i];

            if (Tree.Y_THEN_X_LOCATION_ASC.compare(other, pivot) < 0) {
                pivot = other;
            }
        }

        return pivot;
    }

    record Tree(int x, int y) {

        static final Comparator<Tree> Y_THEN_X_LOCATION_ASC =
                Comparator.comparing(Tree::y).thenComparing(Tree::x);

        static Comparator<Tree> angleToPivotComparator(Tree pivot) {
            return (first, second) -> {
                int crossProduct = crossProduct(pivot, first, second);

                if (crossProduct != 0) {
                    return crossProduct;
                }

                // crossProduct == 0, collinear points, compare using Euclidean distance squared
                int firstDistance = distance(pivot, first);
                int secondDistance = distance(pivot, second);

                // choose the closest point
                return Integer.compare(firstDistance, secondDistance);
            };
        }

        /*
         * > 0 → Left turn
         * < 0 → Right turn
         * = 0 → Collinear
         */
        static int crossProduct(Tree pivot, Tree first, Tree second) {
            return (first.x - pivot.x) * (second.y - pivot.y)
                    - (first.y - pivot.y) * (second.x - pivot.x);
        }

        /*
         * Euclidean distance squared between trees.
         *
         *  (x1-x2)^2 + (y1 - y2)^2
         */
        static int distance(Tree first, Tree second) {
            int dx = first.x - second.x;
            int dy = first.y - second.y;

            return dx * dx + dy * dy;
        }

        @Override
        public String toString() {
            return "(%d; %d)".formatted(x, y);
        }
    }
}

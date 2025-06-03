package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 587. Erect the Fence
 *
 * <p>https://leetcode.com/problems/erect-the-fence
 */
public class ErectTheFence {

    /**
     * Uses Jarvis' march algorithm to construct convex hull with additional logic to fetch
     * collinear points.
     *
     * <p>time: O(H*N)
     *
     * <p>space: O(N)
     *
     * @param treesArr - initial unsorted array of trees
     * @return - all points/trees located at convex hull boundary
     */
    public static int[][] outerTrees(int[][] treesArr) {
        Objects.requireNonNull(treesArr, "'treesArr' is NULL'");
        if (treesArr.length < 4) return treesArr;

        Tree[] trees = toTrees(treesArr);

        Tree initialPivot = findPivot(trees);

        Set<Tree> convexHull = new HashSet<>();
        convexHull.add(initialPivot);

        Tree cur = initialPivot;

        while (true) {
            Tree candidate = findNext(cur, trees);

            List<Tree> collinear = findAllCollinear(cur, candidate, trees);
            convexHull.addAll(collinear);

            // if candidate == initialPivot element we have FULLY constructed convex hull
            if (candidate.equals(initialPivot)) {
                break;
            }

            convexHull.add(candidate);
            cur = candidate;
        }

        return to2DArray(convexHull);
    }

    private static Tree findNext(Tree base, Tree[] trees) {

        Tree candidate = null;

        for (Tree value : trees) {
            if (value.equals(base)) {
                continue;
            }

            candidate = value;
            break;
        }

        assert candidate != null;

        for (Tree value : trees) {
            if (value.equals(base) || value.equals(candidate)) {
                continue;
            }

            int crossProductRes = Tree.orientation(base, candidate, value);

            // crossProductRes < 0 => value is located left to candidate
            // crossProductRes == 0 => collinear elements, choose the farthest one
            if (crossProductRes < 0
                    || (crossProductRes == 0
                            && Tree.distance(base, value) > Tree.distance(base, candidate))) {
                candidate = value;
            }
        }

        return candidate;
    }

    private static List<Tree> findAllCollinear(Tree base, Tree candidate, Tree[] trees) {

        List<Tree> res = new ArrayList<>();
        for (Tree value : trees) {
            if (value.equals(base) || value.equals(candidate)) {
                continue;
            }

            if (Tree.crossProduct(base, candidate, value) == 0) {
                res.add(value);
            }
        }

        return res;
    }

    private static Tree[] toTrees(int[][] treesArr) {
        assert treesArr != null : "'treesArr' is NULL";

        Tree[] trees = new Tree[treesArr.length];

        for (int i = 0; i < treesArr.length; i++) {
            trees[i] = new Tree(treesArr[i][0], treesArr[i][1]);
        }

        return trees;
    }

    private static int[][] to2DArray(Collection<Tree> convexHull) {
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
     * Find elements with smallest X and if there is a tight smallest Y coordinate.
     *
     * @param trees - all trees unsorted
     * @return pivot tree with smallest Y and X values.
     */
    static Tree findPivot(Tree[] trees) {
        assert trees != null && trees.length > 0;
        Tree pivot = trees[0];

        for (int i = 1; i < trees.length; i++) {
            Tree other = trees[i];

            if (Tree.X_THEN_Y_LOCATION_ASC.compare(other, pivot) < 0) {
                pivot = other;
            }
        }

        return pivot;
    }

    record Tree(int x, int y) {

        static final Comparator<Tree> X_THEN_Y_LOCATION_ASC =
                Comparator.comparing(Tree::x).thenComparing(Tree::y);

        /*
         * This is equivalent to the 2D cross product of vectors base->candidate × candidate->value.
         *
         * val = 0 → Collinear
         * val < 0 → Counter-clockwise (left turn)
         * val > 0 → Clockwise
         */
        static int orientation(Tree base, Tree candidate, Tree value) {
            return (candidate.y - base.y) * (value.x - candidate.x)
                    - (candidate.x - base.x) * (value.y - candidate.y);
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

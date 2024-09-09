package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.DeleteNodesAndReturnForest.delNodes;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.Test;

public class DeleteNodesAndReturnForestTest {

    @Test
    void delNodesCase1() {
        DeleteNodesAndReturnForest.TreeNode one = new DeleteNodesAndReturnForest.TreeNode(1);
        DeleteNodesAndReturnForest.TreeNode two = new DeleteNodesAndReturnForest.TreeNode(2);
        DeleteNodesAndReturnForest.TreeNode three = new DeleteNodesAndReturnForest.TreeNode(3);

        DeleteNodesAndReturnForest.TreeNode four = new DeleteNodesAndReturnForest.TreeNode(4);
        DeleteNodesAndReturnForest.TreeNode five = new DeleteNodesAndReturnForest.TreeNode(5);

        DeleteNodesAndReturnForest.TreeNode six = new DeleteNodesAndReturnForest.TreeNode(6);
        DeleteNodesAndReturnForest.TreeNode seven = new DeleteNodesAndReturnForest.TreeNode(7);

        one.left = two;
        one.right = three;

        two.left = four;
        two.right = five;

        three.left = six;
        three.right = seven;

        List<DeleteNodesAndReturnForest.TreeNode> forestAfterDeletion =
                delNodes(one, new int[] {3, 5});

        assertThat(forestAfterDeletion).isNotNull().hasSize(3);

        DeleteNodesAndReturnForest.TreeNode subtree1 = forestAfterDeletion.get(0);
        assertNotNull(subtree1);
        assertEquals(1, subtree1.val);
        assertNotNull(subtree1.left);
        assertNull(subtree1.right);

        DeleteNodesAndReturnForest.TreeNode leftTwo = subtree1.left;
        assertEquals(2, leftTwo.val);
        assertNotNull(leftTwo.left);
        assertNull(leftTwo.right);

        DeleteNodesAndReturnForest.TreeNode leftFour = leftTwo.left;
        assertEquals(4, leftFour.val);
        assertNull(leftFour.left);
        assertNull(leftFour.right);

        DeleteNodesAndReturnForest.TreeNode subtree2 = forestAfterDeletion.get(1);
        assertNotNull(subtree2);
        assertEquals(6, subtree2.val);
        assertNull(subtree2.left);
        assertNull(subtree2.right);

        DeleteNodesAndReturnForest.TreeNode subtree3 = forestAfterDeletion.get(2);
        assertNotNull(subtree3);
        assertEquals(7, subtree3.val);
        assertNull(subtree3.left);
        assertNull(subtree3.right);
    }
}

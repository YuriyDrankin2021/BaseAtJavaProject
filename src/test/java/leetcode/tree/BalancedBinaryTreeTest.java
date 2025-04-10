package leetcode.tree;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BalancedBinaryTreeTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/same-tree/")
    @Disabled("incorrect solution")
    public void balancedBinaryTreeTest(TreeNode root, boolean expectedResult) {
        Assertions.assertThat(isBalanced(root)).isEqualTo(expectedResult);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        else return isIt(root.left, root.right);
    }

    private boolean isIt(TreeNode p, TreeNode q) {
        boolean a = true;
        boolean b = true;
        if (p == null && q == null) return true;
        else {
            if (p != null) {
                if (q != null && p.val == q.val) return false;
                if (p.left != null || p.right != null) a = isIt(p.left, p.right);
            }
            if ((q != null)) {
                if (q.left != null || q.right != null) b = isIt(q.left, q.right);
            }
        }
        return a && b;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new TreeNode(3,
                                new TreeNode(9),
                                new TreeNode(20, new TreeNode(15), new TreeNode(7))),
                        true),
                arguments(new TreeNode(1,
                                new TreeNode(2, new TreeNode(3, new TreeNode(4), new TreeNode(4)), new TreeNode(3)),
                                new TreeNode(2)),
                        false),
                arguments(new TreeNode(), true)
        );
    }
}

package leetcode.tree;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SameTreeTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/same-tree/")
    public void sameTreeTest(TreeNode p, TreeNode q, boolean expectedResult) {
        Assertions.assertThat(isSameTree(p, q)).isEqualTo(expectedResult);
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null) {
            return q == null;
        } else if (q == null) return false;
        else if (p.val != q.val) return false;
        else {
            boolean a = true;
            boolean b = true;
            if (p.right != null || q.right != null) a = isSameTree(p.right, q.right);
            if (p.left != null || q.left != null) b = isSameTree(p.left, q.left);
            return a&&b;
        }
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new TreeNode(1, new TreeNode(2), new TreeNode(3)), new TreeNode(1, new TreeNode(2), new TreeNode(3)), true),
                arguments(new TreeNode(1, new TreeNode(2), new TreeNode(1)), new TreeNode(1, new TreeNode(1), new TreeNode(2)), false),
                arguments(new TreeNode(1, new TreeNode(2, new TreeNode(), new TreeNode()), new TreeNode(3, new TreeNode(4), new TreeNode(5))),
                        new TreeNode(1, new TreeNode(2), new TreeNode(3)),
                        false),
                arguments(new TreeNode(1, new TreeNode(2)), new TreeNode(1, new TreeNode(), new TreeNode(2)), false),
                arguments(new TreeNode(2, new TreeNode(), new TreeNode(4)),
                        new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                        false)
        );
    }
}

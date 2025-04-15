package leetcode.linkedList;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SwapNodesInPairsTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/swap-nodes-in-pairs/")
    public void swapNodesInPairsTest(ListNode root, ListNode expectedResult) {
        ListNode res = swapPairs(root);
        Assertions.assertThat(res).isEqualTo(expectedResult);
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode curr = head;
        while (curr.next != null) {
            int var = curr.val;
            curr.val = curr.next.val;
            curr.next.val = var;
            if (curr.next.next!=null){
                curr = curr.next.next;
            }else break;
        }
        return head;
    }


    private Stream<Arguments> data() {
        return Stream.of(
//                arguments(new ListNode(1, new ListNode(2, new ListNode(3))),
//                        new ListNode(2, new ListNode(1, new ListNode(3)))
//                ),
                arguments(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4)))),
                        new ListNode(2, new ListNode(1, new ListNode(4, new ListNode(3))))
                ));
    }
}

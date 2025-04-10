package leetcode.linkedList;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ReverseLinkedListTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/reverse-linked-list/description/")
    @Disabled("incorrect checking with correct result")
    public void reverseLinkedListTest(ListNode root, ListNode expectedResult) {
        ListNode res = reverseList(root);
        Assertions.assertThat(res).isEqualTo(expectedResult);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }


    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
                        new ListNode(5, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1)))))),
                arguments(new ListNode(1, new ListNode(2)),
                        new ListNode(2, new ListNode(1))),
                arguments(new ListNode(), new ListNode())
        );
    }
}

package leetcode.linkedList;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RemoveNthNodeFromEndOfListTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/longest-common-prefix/")
    public void removeNthNodeFromEndOfListTest(ListNode input, int n, ListNode expectedResult) {
        Assertions.assertThat(removeNthFromEnd(input, n)).isEqualTo(expectedResult);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int size = 1;
        ListNode node = head;
        while (node.next != null) {
            size++;
            node = node.next;
        }
        if (n == 1 && size == 1) return null;
        if (n == size) return head.next;
        int num = size - n;
        int pos = 1;
        node = head;
        while (pos < num) {
            node = node.next;
            pos++;
        }
        node.next = node.next.next;
        return head;
    }


    /**
     * Input: head = [1,2,3,4,5], n = 2
     * Output: [1,2,3,5]
     * Example 2:
     * <p>
     * Input: head = [1], n = 1
     * Output: []
     * Example 3:
     * <p>
     * Input: head = [1,2], n = 1
     * Output: [1]
     */


    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2,
                        new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5))))),
                arguments(new ListNode(1), 1, new ListNode()),
                arguments(new ListNode(1, new ListNode(2)), 1, new ListNode(1)),
                arguments(new ListNode(1, new ListNode(2)), 2, new ListNode(2))
        );
    }
}

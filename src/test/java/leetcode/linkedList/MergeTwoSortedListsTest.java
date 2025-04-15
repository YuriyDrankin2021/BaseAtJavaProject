package leetcode.linkedList;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MergeTwoSortedListsTest {


    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/longest-common-prefix/")
    public void removeNthNodeFromEndOfListTest(ListNode list1, ListNode list2, ListNode expectedResult) {
        Assertions.assertThat(mergeTwoLists2(list1, list2)).isEqualTo(expectedResult);
    }

    //doesn't work
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        while (list1.next != null && list2.next != null) {
            ListNode draft = new ListNode();
            if (list1.val < list2.val) {
                draft.val = list1.val;
                list1 = list1.next;
            } else {
                draft.val = list2.val;
                list2 = list2.next;
            }
            if (result.val == 0) {
                result = draft;
            } else {
                result.next = draft;
            }
        }
        return result;

    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                list1.next = mergeTwoLists2(list1.next, list2);
                return list1;
            } else {
                list2.next = mergeTwoLists2(list2.next, list1);
                return list2;
            }
        }
        if (list1 == null)
            return list2;
        return list1;
    }

    /**
     * Input: list1 = [1,2,4], list2 = [1,3,4]
     * Output: [1,1,2,3,4,4]
     * Example 2:
     * <p>
     * Input: list1 = [], list2 = []
     * Output: []
     * Example 3:
     * <p>
     * Input: list1 = [], list2 = [0]
     * Output: [0]
     *
     * @return
     */

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new ListNode(1, new ListNode(2, new ListNode(4))), new ListNode(1, new ListNode(3, new ListNode(4))),
                        new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(4))))))),
                arguments(null, new ListNode(0), new ListNode(0)),
                arguments(null, null, null)
        );
    }
}

package leetcode.array;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FindFirstAndLastPositionOfElementInSortedArrayTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/")
    public void findFirstAndLastPositionOfElementInSortedArrayTest(int[] input, int element, int[] positions) {
        int[] current = searchRange(input, element);
        Assertions.assertThat(current).isEqualTo(positions);
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int size = nums.length;
        if (size == 0) return new int[]{-1, -1};
        for (int i = 0; i < size; i++) {
            if (nums[i] == target) {
                res[0] = i;
                i++;
                while (i < size) {
                    if (nums[i] == target) i++;
                    else break;
                }
                if (i == res[0]) {
                    res[1] = i;
                    return res;
                } else {
                    res[1] = i - 1;
                    return res;
                }
            }
        }
        return new int[]{-1, -1};
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{5, 7, 7, 8, 8, 10}, 8, new int[]{3, 4}),
                arguments(new int[]{5, 7, 7, 8, 8, 8, 8, 8, 10}, 8, new int[]{3, 7}),
                arguments(new int[]{5, 7, 7, 8, 8, 8, 8, 8, 8}, 8, new int[]{3, 8}),
                arguments(new int[]{5, 7, 7, 8, 8, 10}, 6, new int[]{-1, -1}),
                arguments(new int[]{5, 5, 7, 8, 8, 10}, 5, new int[]{0, 1}),
                arguments(new int[]{5, 6, 7, 8, 8, 10}, 5, new int[]{0, 0}),
                arguments(new int[]{}, 0, new int[]{-1, -1})
        );
    }
}

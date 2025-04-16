package leetcode.binarySearch;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SearchInsertPositionTest {
    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/search-insert-position/")
    public void searchInsertPositionTest(int[] input, int element, int position) {
        int current = searchInsert(input, element);
        Assertions.assertThat(current).isEqualTo(position);
    }

    public int searchInsert(int[] nums, int target) {
        if (nums[0] >= target) return 0;
        int length = nums.length;
        if (nums[length - 1] < target) return length;
        int left = 0;
        int right = length - 1;
        int mid = (right - left) / 2;
        if (nums[mid] > target) {
            right = mid;
        } else left = mid;
        while (left < right) {
            if (nums[left] < target) {
                left++;
            } else right--;
        }
        return left;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{5, 7, 7, 8, 8, 10}, 8, 3),
                arguments(new int[]{1, 3, 5, 6}, 5, 2),
                arguments(new int[]{1, 3, 5, 6}, 2, 1),
                arguments(new int[]{1, 3, 5, 6}, -2, 0),
                arguments(new int[]{1, 3, 5, 6}, 7, 4)
        );
    }
}

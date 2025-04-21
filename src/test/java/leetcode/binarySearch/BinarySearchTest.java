package leetcode.binarySearch;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BinarySearchTest {
    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/binary-search/")
    public void binarySearchTest(int[] input, int element, int position) {
        int current = search(input, element);
        Assertions.assertThat(current).isEqualTo(position);
    }

    public int search(int[] nums, int target) {
        int size = nums.length;
        if (nums[0] == target) return 0;
        if (nums[size - 1] == target) return size - 1;
        int left = 0;
        int right = size - 1;
        int mid = (right - left) / 2;
        if (nums[mid] >= target) right = mid;
        else left = mid;
        while (left <= right) {
            if (nums[left] == target) return left;
            else left++;
        }
        return -1;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{-1, 0, 3, 5, 9, 12}, 9, 4),
                arguments(new int[]{-1, 0, 3, 5, 9, 12}, -1, 0),
                arguments(new int[]{-1, 0, 3, 5, 9, 12}, 2, -1)
        );
    }
}

package leetcode.array;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RemoveDuplicatesFromSortedArrayTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/remove-duplicates-from-sorted-array/")
    public void removeDuplicatesFromSortedArrayTest(int[] input, int expectedResult) {
        int currVal = removeDuplicates(input);
        Assertions.assertThat(currVal).isEqualTo(expectedResult);
    }

    public int removeDuplicates2(int[] nums) {
        int k = 1;
        int pos = 0;
        int cnt = 0;
        while (k < nums.length) {
            if (nums[k] == nums[k - 1]) {
                pos = k;
                k++;
            } else if (pos != 0) {
                int val = nums[pos];
                nums[pos] = nums[k];
                nums[k] = val;
                k = pos;
                pos = 0;
            } else {
                k++;
                cnt++;
            }
        }
        return cnt;
    }

    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        int j = 0;
        for (int i = 1; i < length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (res[j] != nums[i - 1]) {
                    res[j] = nums[i - 1];
                    j++;
                } else if (res[j] == 0) {
                    j++;
                }
            }
        }
        if (nums[length - 1] != res[j]) res[j] = nums[length - 1];
        j++;
        System.arraycopy(res, 0, nums, 0, j);
        return j;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{1, 1, 2}, 2),
                arguments(new int[]{-1, 1, 2}, 3),
                arguments(new int[]{-1, 0, 0, 1, 2}, 4),
                arguments(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 5)
        );
    }
}

package leetcode.hash;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TwoSumTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/two-sum/description/")
    public void singleNumberTest(int[] nums, int target, int[] expectedResult) {
        Assertions.assertThat(twoSum3(nums, target)).isEqualTo(expectedResult);
    }

    public int[] twoSum(int[] nums, int target) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int val1 = nums[i];
            int val2 = target - val1;
            for (int j = i + 1; j < size; j++) {
                if (nums[j] == val2) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    public int[] twoSum2(int[] nums, int target) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int val1 = nums[i];
            int val2 = target - val1;
            int cnt = i + 1;
            List<Integer> list = Arrays.stream(nums).boxed().toList().subList(cnt, size);
            if (list.contains(val2)) {
                int j = list.indexOf(val2) + cnt;
                return new int[]{i, j};
            }
        }
        return new int[2];
    }

    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < size; i++) {
            int val = target - nums[i];
            if (map.containsKey(val) && map.get(val)!=i) {
                return new int[]{i, map.get(val)};
            }
        }
        return new int[]{};
    }


    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                arguments(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
                arguments(new int[]{1,3,4,2}, 6, new int[]{2, 3}),
                arguments(new int[]{2,5,5,11}, 10, new int[]{1, 2}),
                arguments(new int[]{3, 3}, 6, new int[]{0, 1})
        );
    }
}

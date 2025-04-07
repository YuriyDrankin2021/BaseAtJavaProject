package leetcode.hash;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SingleNumberTest {

    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.merge(i, 1, Integer::sum);
        }
        return getKey(map);
    }

    private int getKey(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return 0;
    }

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/single-number/")
    public void singleNumberTest(int[] nums, int expectedResult) {
        Assertions.assertThat(singleNumberXOR(nums)).isEqualTo(expectedResult);
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{2, 2, 1}, 1),
                arguments(new int[]{2, 2, 0}, 0),
                arguments(new int[]{1, 0, 1, 2, 0}, 2),
                arguments(new int[]{4, 1, 2, 1, 2}, 4),
                arguments(new int[]{1}, 1)
        );
    }

    public int singleNumber(int[] nums) {
        List<Integer> map = new ArrayList<>();
        for (Integer i : nums) {
            if (!map.contains(i)) {
                map.add(i);
            } else
                map.remove(i);
        }
        return map.getFirst();
    }

    public int singleNumberXOR(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

}

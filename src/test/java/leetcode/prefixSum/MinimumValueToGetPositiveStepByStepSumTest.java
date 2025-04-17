package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MinimumValueToGetPositiveStepByStepSumTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/minimum-value-to-get-positive-step-by-step-sum")
    public void minimumValueToGetPositiveStepByStepSumTest(int[] input, int expectedResult) {
        int result = minStartValue(input);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    public int minStartValue(int[] nums) {
        int min = 0;
        int summ = 0;
        for (int num : nums) {
            summ += num;
            if (summ < min) min = summ;
        }
       return 1 - min;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{-3, 2, -3, 4, 2}, 5),
                arguments(new int[]{1, 2}, 1),
                arguments(new int[]{1, -2, -3}, 5)
        );
    }
}

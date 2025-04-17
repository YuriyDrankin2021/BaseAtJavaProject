package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RunningSumOf1dArrayTest {
    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/running-sum-of-1d-array/description")
    public void runningSumOf1dArrayTest(int[] input, int[] expectedResult) {
        int[] result = runningSum(input);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < input.length; i++) {
            softAssertions.assertThat(result[i]).as("i = %d", i).isEqualTo(expectedResult[i]);
        }
        softAssertions.assertAll();
    }

    public int[] runningSum(int[] nums) {
        int size = nums.length;
        int[] output = new int[size];
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += nums[i];
            output[i] = sum;
        }
        return output;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{1, 2, 3, 4}, new int[]{1, 3, 6, 10}),
                arguments(new int[]{1, 1, 1, 1, 1}, new int[]{1, 2, 3, 4, 5}),
                arguments(new int[]{3, 1, 2, 10, 1}, new int[]{3, 4, 6, 16, 17})
        );
    }
}

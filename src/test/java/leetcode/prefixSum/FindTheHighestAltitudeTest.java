package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FindTheHighestAltitudeTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/find-the-highest-altitude")
    public void minimumValueToGetPositiveStepByStepSumTest(int[] input, int expectedResult) {
        int result = largestAltitude(input);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    public int largestAltitude(int[] gain) {
        int result = 0;
        int val = 0;
        for (int current:gain){
            val+=current;
            result = Math.max(result, val);
        }
        return result;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{-5,1,5,0,-7}, 1),
                arguments(new int[]{-4,-3,-2,-1,4,3,2}, 0)
        );
    }
}

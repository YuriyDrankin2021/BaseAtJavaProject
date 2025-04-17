package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MaximumScoreAfterSplittingAStringTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/maximum-score-after-splitting-a-string")
    public void maximumScoreAfterSplittingAStringTest(String input, int expectedResult) {
        int result = maxScore(input);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    public int maxScore(String s) {
        int max = 0;
        int sumLeft = 0;
        int sumRight = 0;
        int size = s.length();
        int[] right = new int[size - 1];
        for (int i = 1; i < size; i++) {
            if (s.charAt(size - i) == '1') {
                sumRight++;
            }
            right[size - i - 1] = sumRight;

        }
        for (int j = 0; j < size - 1; j++) {
            sumLeft = s.charAt(j) == '0' ? sumLeft + 1 : sumLeft;
            max = Math.max(sumLeft + right[j], max);
        }
        return max;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments("011101", 5),
                arguments("00111", 5),
                arguments("1111", 3)
        );
    }
}

package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CheckIfAllTheIntegersInARangeAreCoveredTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/")
    public void checkIfAllTheIntegersInARangeAreCoveredTest(int[][] input, int left, int right, boolean expectedResult) {
        boolean result = isCovered(input, left, right);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        for (int i = left; i<=right; i++){
            boolean isCovered=false;
            for (int[] range:ranges){
                if(i>=range[0] && i<=range[1]){
                    isCovered = true;
                    break;
                }
            }
            if (!isCovered) return isCovered;
        }
        return true;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[][]{{1, 2}, {3, 4}, {5, 6}}, 2, 5, true),
                arguments(new int[][]{{1, 10}, {11, 20}}, 21, 21, false)
        );
    }
}

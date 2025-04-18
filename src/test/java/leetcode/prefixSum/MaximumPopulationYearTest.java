package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MaximumPopulationYearTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/running-sum-of-1d-array/description")
    public void runningSumOf1dArrayTest(int[][] input, int expectedResult) {
        int result = maximumPopulation(input);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    public int maximumPopulation(int[][] logs) {
        int result = 0;
        int maxCnt = 0;
        int minYear = 2050;
        int maxYear = 1950;
        for (int[] logFor : logs) {
            if (logFor[0] < minYear) minYear = logFor[0];
            if (logFor[1] > maxYear) maxYear = logFor[1];
        }

        for (int i = minYear; i < maxYear; i++) {
            int currCnt = 0;
            for (int[] log : logs) {
                if (i >= log[0] && i < log[1]) {
                    currCnt++;
                }
            }
            if (maxCnt < currCnt) {
                result = i;
                maxCnt = currCnt;
            }
        }
        return result;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[][]{{1993, 1999}, {2000, 2010}}, 1993),
                arguments(new int[][]{{1950, 1961}, {1960, 1971}, {1970, 1981}}, 1960),
                arguments(new int[][]{
                        {2033, 2034}, {2039, 2047}, {1998, 2042}, {2047, 2048}, {2025, 2029},
                        {2005, 2044}, {1990, 1992}, {1952, 1956}, {1984, 2014}
                }, 2005)
        );
    }
}

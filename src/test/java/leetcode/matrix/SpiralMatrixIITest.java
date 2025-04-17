package leetcode.matrix;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SpiralMatrixIITest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/length-of-last-word/")
    @Disabled("not implemented")
    public void spiralMatrixIITest(int n, int[][] result) {
        SoftAssertions softAssertions = new SoftAssertions();
        int[][] current = generateMatrix(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                softAssertions.assertThat(current[i][j]).as("%d - %d", i, j).isEqualTo(result[i][j]);
            }
        }
        softAssertions.assertAll();
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int size = n*n;
        //todo
        return result;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(
                        1, new int[][]{{1}}
                ),
                arguments(
                        3, new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}
                )
        );
    }
}

package leetcode.math;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ReverseIntegerTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/palindrome-number/")
    public void reverseIntegerTest(int input, int expectedResult) {
        Assertions.assertThat(reverse(input)).isEqualTo(expectedResult);
    }

    public int reverse(int x) {
        if (x >= 0 && x < 10) return x;
        int koeff = 1;
        if (x < 0) {
            koeff = -1;
            x *= koeff;
        }
        long result = 0L;
        while (x > 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (result>Integer.MAX_VALUE){
            return 0;
        }else return (int) result * koeff;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(123, 321),
                arguments(-123, -321),
                arguments(120, 21),
                arguments(1534236469, 0)
        );
    }
}

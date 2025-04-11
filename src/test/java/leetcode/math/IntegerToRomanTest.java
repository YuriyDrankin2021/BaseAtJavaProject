package leetcode.math;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class IntegerToRomanTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/integer-to-roman/description/")
    public void integerToRomanTest(int input, String expectedResult) {
        Assertions.assertThat(intToRoman3(input)).isEqualTo(expectedResult);
    }

    /*
    *
Symbol	Value
I	1
V	5
X	10
L	50
C	100
D	500
M	1000

    * */

    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        if (num / 1000 > 0) {
            builder.append("M".repeat(Math.max(0, num / 1000)));
            num = num % 1000;
        }
        if (num / 100 > 0) {
            int hCnt = num / 100;
            if (hCnt == 9) {
                builder.append("CM");
            } else {
                if (hCnt >= 5) {
                    builder.append("D");
                    hCnt -= 5;
                } else if (hCnt == 4) {
                    builder.append("CD");
                    hCnt -= 4;
                }
                builder.append("C".repeat(hCnt));
            }
            num %= 100;
        }
        if (num / 10 > 0) {
            int hCnt = num / 10;
            if (hCnt == 9) {
                builder.append("XC");
            } else {
                if (hCnt >= 5) {
                    builder.append("L");
                    hCnt -= 5;
                } else if (hCnt == 4) {
                    builder.append("XL");
                    hCnt -= 4;
                }
                builder.append("X".repeat(hCnt));
            }
            num %= 10;
        }
        if (num > 0) {
            if (num == 9) {
                builder.append("IX");
            } else {
                if (num >= 5) {
                    builder.append("V");
                    num -= 5;
                } else if (num == 4) {
                    builder.append("IV");
                    num -= 4;
                }
                builder.append("I".repeat(num));
            }
        }

        return builder.toString();
    }

    public String intToRoman2(int num) {
        StringBuilder builder = new StringBuilder();
        Map<Integer, String> roman = new TreeMap<>(Comparator.reverseOrder());
        roman.put(1000, "M");
        roman.put(900, "CM");
        roman.put(500, "D");
        roman.put(400, "CD");
        roman.put(100, "C");
        roman.put(90, "XC");
        roman.put(50, "L");
        roman.put(40, "XL");
        roman.put(10, "X");
        roman.put(9, "IX");
        roman.put(5, "V");
        roman.put(4, "IV");
        roman.put(1, "I");
        for (int i : roman.keySet()) {
            int curr = num / i;
            if (curr > 0) {
                builder.append(roman.get(i).repeat(curr));
                num -= curr * i;
            }
        }
        return builder.toString();
    }

    public String intToRoman3(int num) {
        StringBuilder builder = new StringBuilder();
        final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i<values.length; i++){
            int curr = num / values[i];
            if (curr > 0) {
                builder.append(symbols[i].repeat(curr));
                num -= curr * values[i];
            }
        }
        return builder.toString();
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(3749, "MMMDCCXLIX"),
                arguments(1994, "MCMXCIV"),
                arguments(58, "LVIII"),
                arguments(58, "LVIII"),
                arguments(10, "X")
        );
    }
}

package leetcode.math;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RomanToIntegerTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/integer-to-roman/description/")
    public void integerToRomanTest(String input, int expectedResult) {
        Assertions.assertThat(romanToInt(input)).isEqualTo(expectedResult);
    }

    public int romanToInt(String s) {
        Map<Character, Integer> roman = new HashMap<>();
        roman.put('M', 1000);
        roman.put('D', 500);
        roman.put('C', 100);
        roman.put('L', 50);
        roman.put('X', 10);
        roman.put('V', 5);
        roman.put('I', 1);
        int result = 0;
        int iterator = 0;
        char[] array = s.toCharArray();
        int size = array.length;
        if (size == 1) return roman.get(s.charAt(0));
        while (iterator < size - 1) {
            if (array[iterator] == array[iterator + 1]) {
                result += roman.get(array[iterator]);
                iterator++;
            } else {
                if (array[iterator] != 'C' && array[iterator] != 'X' && array[iterator] != 'I') {
                    result += roman.get(array[iterator]);
                    iterator++;
                } else {
                    int val = (roman.get(array[iterator + 1]) - roman.get(array[iterator]));
                    if (val > 0) {
                        result += (roman.get(array[iterator + 1]) - roman.get(array[iterator]));
                        iterator += 2;
                    } else {
                        result += roman.get(array[iterator]);
                        iterator++;
                    }
                }
            }
            if (iterator == size - 1) result += roman.get(array[iterator]);
        }
        return result;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments("MMMDCCXLIX", 3749),
                arguments("MCMXCIV", 1994),
                arguments("LVIII", 58),
                arguments("III", 3),
                arguments("X", 10)
        );
    }
}

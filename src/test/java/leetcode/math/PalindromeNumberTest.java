package leetcode.math;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PalindromeNumberTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/palindrome-number/")
    public void palindromeNumberTest(int input, boolean expectedResult) {
        Assertions.assertThat(isPalindrome2(input)).isEqualTo(expectedResult);
    }

    public boolean isPalindrome(int x) {
        String stringInt = String.valueOf(x);
        int n = stringInt.length();
        for (int i = 0; i < n / 2; i++) {
            if (stringInt.charAt(i) != stringInt.charAt(n - 1 - i)) return false;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        int n = chars.length;
        for (int i = 0; i < n / 2; i++) {
            if (chars[i] != chars[n - 1 - i]) return false;
        }
        return true;
    }

    public boolean isPalindrome3(int x) {
        if (x < 0) return false;
        if (x > 0 && x <= 9) return true;
        List<Integer> list = new ArrayList<>();
        while (x>0){
            list.add(x%10);
            x/=10;
        }
        int n = list.size();
        for (int i = 0; i < n / 2; i++) {
            if (!Objects.equals(list.get(i), list.get(n - 1 - i))) return false;
        }
        return true;
    }


    private Stream<Arguments> data() {
        return Stream.of(
                arguments(121, true),
                arguments(-121, false),
                arguments(10, false)
        );
    }
}

package leetcode;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LengthOfLastWordTest {


    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/length-of-last-word/")
    public void lengthOfLastWordTest(String string, int result) {
        Assertions.assertThat(lengthOfLastWord2(string)).isEqualTo(result);
    }

    public int lengthOfLastWord(String s) {
        String[] strings = s.trim().split(" ");
        return strings[strings.length - 1].length();
    }

    public int lengthOfLastWord2(String s) {
        int k = 0;
        int i = s.length() - 1;
        while (s.charAt(i) == ' ') {
            i--;
        }
        while (s.charAt(i) != ' ') {
            k++;
            i--;
        }
        return k;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments("Hello World", 5),
                arguments("   fly me   to   the moon  ", 4),
                arguments("luffy is still joyboy", 6)
        );
    }
}

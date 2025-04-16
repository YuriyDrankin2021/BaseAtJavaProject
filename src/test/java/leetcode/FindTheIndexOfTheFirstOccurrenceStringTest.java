package leetcode;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FindTheIndexOfTheFirstOccurrenceStringTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/")
    public void test(String haystack, String needle, int n) {
        int res = strStr(haystack, needle);
        Assertions.assertThat(res).isEqualTo(n);
    }

    public int strStr(String haystack, String needle) {
        int size1 = haystack.length();
        int size2 = needle.length();
        if (size1 < size2) return -1;
        int i = 0;
        while (i < size1) {
            boolean isIt = true;
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (size1 - i < size2) return -1;
                for (int k = 1; k < size2; k++) {
                    if (haystack.charAt(i + k) != needle.charAt(k)) {
                        isIt = false;
                        break;
                    }
                }
                if (isIt)
                    return i;
                else
                    i++;
            } else
                i++;
        }
        return -1;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments("sadbutsad", "sad", 0),
                arguments("leetcode", "leeto", -1),
                arguments("mississippi", "issip", 4)
        );
    }

}

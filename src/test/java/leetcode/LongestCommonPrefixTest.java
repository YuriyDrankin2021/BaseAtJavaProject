package leetcode;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class LongestCommonPrefixTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/longest-common-prefix/")
    public void longestCommonPrefixTest(String[] input, String expectedResult) {
        Assertions.assertThat(longestCommonPrefix(input)).isEqualTo(expectedResult);
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder builder = new StringBuilder();
        int arraySize = strs.length;
        int size = strs[0].length();
        for (int i = 0; i < size; i++) {
            char curr = strs[0].charAt(i);
            for (int j = 1; j < arraySize; j++) {
                if (strs[j].length() <= i) return builder.toString();
                char newCurr = strs[j].charAt(i);
                if (curr != newCurr) {
                    return builder.toString();
                }
            }
            builder.append(curr);
        }
        return builder.toString();
    }

    public String longestCommonPrefix2(String[] strs) {
        String firstString = strs[0];
        if(strs.length == 1){
            return firstString;
        }

        for(int end = 0; end < firstString.length(); end++){
            for(int i = 1; i < strs.length; i++){
                if(end < strs[i].length() && firstString.charAt(end) == strs[i].charAt(end)){
                    continue;
                }
                else{
                    return firstString.substring(0, end);
                }
            }
        }
        return firstString;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new String[]{"flower", "flow", "flight"}, "fl"),
                arguments(new String[]{"dog", "racecar", "car"}, ""),
                arguments(new String[]{"ab", "a"}, "a"),
                arguments(new String[]{"dog", "racecar", ""}, "")
        );
    }
}

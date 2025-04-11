package leetcode;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class UniqueSubStringLengthTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/longest-substring-without-repeating-characters/")
    public void uniqueSubStringLengthTest(String string, int expectedSize) {
        Assertions.assertThat(lengthOfLongestSubstring(string)).isEqualTo(expectedSize);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int result = 0;
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            if (!list.contains(c)) {
                list.add(c);
            } else {
                if (list.size() > result) {
                    result = list.size();
                }
                list = list.subList(list.indexOf(c) + 1, list.size());
                list.add(c);
            }
        }
        return Math.max(list.size(), result);
    }

    public int lengthOfLongestSubstring2(String s) {
        int length = s.length();
        int result = 0;
        Map<Character,Integer>  map = new HashMap<>();
        int left = 0;

        for (int right = 0; right<length; right++){
            if (!map.containsKey(s.charAt(right))|| map.get(s.charAt(right))< left){
                map.put(s.charAt(right), right);
                result = Math.max(result, right-left+1);
            }else {
                left=map.get(s.charAt(right)) + 1;
                map.put(s.charAt(right), right);
            }
        }
        return result;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments("abcabcbb", 3),
                arguments("bbbbb", 1),
                arguments("dvdf", 3),
                arguments("jbpnbwwd", 4),
                arguments("pwwkew", 3)
        );
    }
}

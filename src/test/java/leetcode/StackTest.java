package leetcode;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StackTest {

    @ParameterizedTest
    @Link("https://leetcode.com/problems/valid-parentheses/description/")
    @MethodSource("data")
    public void stackTest(String input, boolean expectedResult) {
        Assertions.assertThat(isValid1(input)).isEqualTo(expectedResult);
    }

    public boolean isValid2(String s) {
        List<Character> listOpen = List.of('(', '{', '[');
        List<Character> listClosed = List.of(')', '}', ']');
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (stack.empty()) {
                if (listClosed.contains(c))
                    return false;
                else
                    stack.push(c);
            } else if (listOpen.indexOf(stack.peek()) == listClosed.indexOf(c)) {
                stack.pop();
            } else if (listClosed.contains(c)) {
                return false;
            } else
                stack.push(c);
        }
        return stack.empty();
    }

    public boolean isValid0(String s) {
        if (s.length() == 1)
            return false;
        else {
            Stack<Character> stack = new Stack<>();
            char open1 = '(';
            char open2 = '{';
            char open3 = '[';
            char close1 = ')';
            char close2 = '}';
            char close3 = ']';

            if (s.charAt(0) == close1 || s.charAt(0) == close2 || s.charAt(0) == close3)
                return false;
            else {
                for (char c : s.toCharArray()) {
                    if (c == open1 || c == open2 || c == open3) {
                        stack.push(c);
                    } else if (c == close1 && !stack.empty()) {
                        if (stack.peek() == open1)
                            stack.pop();
                        else
                            return false;
                    } else if (c == close2 && !stack.empty()) {
                        if (stack.peek() == open2)
                            stack.pop();
                        else
                            return false;
                    } else if (c == close3 && !stack.empty()) {
                        if (stack.peek() == open3)
                            stack.pop();
                        else
                            return false;
                    } else
                        return false;
                }
                return stack.empty();
            }
        }
    }

    public boolean isValid1(String s) {

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (char c : s.toCharArray()) {
            if (stack.empty()) {
                if (map.containsValue(c))
                    return false;
                else
                    stack.push(c);
            } else if (map.get(stack.peek()) == c) {
                stack.pop();
            } else if (map.containsValue(c)) {
                return false;
            } else
                stack.push(c);
        }
        return stack.empty();
    }


    private Stream<Arguments> data() {
        return Stream.of(
                arguments("()", true),
                arguments("()[]{}", true),
                arguments("(]", false),
                arguments("([])", true),
                arguments("([{}({})])", true),
                arguments("([{}({})]{)}", false),
                arguments("(", false),
                arguments("]", false),
                arguments("[])", false)
        );
    }

}

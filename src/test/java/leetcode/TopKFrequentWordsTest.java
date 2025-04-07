package leetcode;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TopKFrequentWordsTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/top-k-frequent-words/description/")
    public void topKFrequentWordsTest(String[] words, int k, List<String> expectedResult) {
        Assertions.assertThat(topKFrequent(words, k)).isEqualTo(expectedResult);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<String> resultList = new ArrayList<>();
        for (String word : words) {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else map.put(word, 1);
        }
        List<Integer> countList = new HashSet<>(map.values()).stream().sorted(Comparator.reverseOrder()).toList();
        for (int count : countList) {
            List<String> subList = new ArrayList<>();
            map.forEach((s, integer) -> {
                if (integer == count && !resultList.contains(s)) subList.add(s);
            });
            resultList.addAll(subList.stream().sorted().toList());
            if (resultList.size() >= k) break;
        }
        return resultList.subList(0, k);
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2, List.of("i", "love")),
                arguments(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 3, List.of("i", "love", "coding")),
                arguments(
                        new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4,
                        List.of("the", "is", "sunny", "day")
                )
        );
    }
}

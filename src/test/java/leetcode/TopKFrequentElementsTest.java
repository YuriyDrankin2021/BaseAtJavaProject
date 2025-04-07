package leetcode;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class TopKFrequentElementsTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/top-k-frequent-elements/description/")
    public void singleNumberTest(int[] nums, int k, int[] expectedResult) {
        Assertions.assertThat(topKFrequent(nums, k)).isEqualTo(expectedResult);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> countList = new HashSet<>(map.values()).stream().sorted(Comparator.reverseOrder()).toList();
        List<Integer> result = new ArrayList<>();
        for (int i : countList){
            List<Integer> tempList = new ArrayList<>();
            map.forEach((value, counter ) -> {
                if (counter == i) tempList.add(value);
            } );
            result.addAll(tempList.stream().sorted().toList());
        }
        return result.subList(0,k).stream().mapToInt(i->i).toArray();
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2}),
                arguments(new int[]{1}, 1, new int[]{1})
        );
    }


    public int[] topKFrequentWithQueue(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[k];
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>(((a,b)->b.getValue()-a.getValue()));
        queue.addAll(map.entrySet());
        for (int i=0;i<k;i++){
            result[i]= Objects.requireNonNull(queue.poll()).getKey();
        }
        return result;
    }
}

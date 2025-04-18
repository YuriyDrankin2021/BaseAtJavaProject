package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class SumOfAllOddLengthSubArraysTest {
    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/sum-of-all-odd-length-subarrays/description")
    public void sumOfAllOddLengthSubArraysTest(int[] input, int expectedResult) {
        int result = sumOddLengthSubarrays(input);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int sum=0;
        int length = arr.length;
        for (int i = 0; i<length; i++){
            for (int j = i; j<length; j++){
                if((j-i+1)%2==1){
                    for (int k=i; k<=j; k++){
                        sum+=arr[k];
                    }
                }
            }
        }
        return sum;
    }

    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{1,4,2,5,3}, 58),
                arguments(new int[]{1, 2}, 3),
                arguments(new int[]{10,11,12}, 66)
        );
    }
}

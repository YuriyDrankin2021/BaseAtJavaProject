package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class FindPivotIndexTests {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/find-pivot-index/description/")
    public void findPivotIndexTests(int[] nums, int expectedResult){
        int currentResult = pivotIndex2(nums);
        Assertions.assertThat(currentResult).isEqualTo(expectedResult);
    }

    public int pivotIndex(int[] nums) {
        int size = nums.length;
        int[] pref = new int[size];
        int[] prefRev = new int[size];
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 1; i<size;i++){
            sum1+=nums[i-1];
            sum2+=nums[size-i];
            pref[i]=sum1;
            prefRev[size-i-1]=sum2;
        }
        for (int k = 0;k<size;k++){
            if (pref[k]==prefRev[k])return k;
        }
        return -1;
    }

    public int pivotIndex2(int[] nums) {
        int sum1 = 0;
        int sumRev = 0;
        for (int i:nums){
            sumRev+=i;
        }
        for (int j=0;j<nums.length;j++){
            int k = nums[j];
            if (sum1==sumRev-k)return j;
            else {
                sum1+=k;
                sumRev-=k;
            }
        }return -1;
    }

    private Stream<Arguments> data(){
        return Stream.of(
                arguments(new int[]{1,7,3,6,5,6}, 3),
                arguments(new int[]{1,2,3}, -1),
                arguments(new int[]{2,1,-1}, 0)
        );
    }
}

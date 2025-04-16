package leetcode.array;

import io.qameta.allure.Link;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RemoveElementTest {

    @ParameterizedTest
    @MethodSource("data")
    @Link("https://leetcode.com/problems/remove-element/description/")
    public void removeElementTest(int[] input, int deletedNumber, int expectedCount) {
        int current = removeElement4(input, deletedNumber);
        Assertions.assertThat(current).isEqualTo(expectedCount);
    }

    //doesn't work
    public int removeElement2(int[] nums, int val) {
        int size = nums.length - 1;
        List<Integer> list = new ArrayList<>();
//        int j = size;
        int result = 0;
        for (int i = size; i >= 0; i--) {
            if (nums[i] != val) {
                list.add(i);
                result++;
            } else if (!list.isEmpty()) {
                nums[i] = nums[list.getFirst()];
                nums[list.getFirst()] = val;
                list.removeFirst();
            }
        }
        return result;
    }

    public int removeElement3(int[] nums, int val) {
        int size = nums.length - 1;
        int counter = 0;
        int res = 0;
        int lastVar = size;
        for (int i = size; i >= 0; i--) {
            if (nums[i] == val) {
                if (lastVar != i) {
                    nums[i] = nums[lastVar];
                    nums[lastVar] = val;
                    if (counter > 0) {
                        lastVar--;
                        counter--;
                    } else {
                        lastVar = i;
                    }
                }
            } else {
                if (lastVar != size) {
                    counter++;
                }
                res++;
            }
        }
        return res;
    }

    //Marina
    public int removeElement4(int[] nums, int val) {
        int f =0;
        int size = nums.length - 1;
        for (int i = size; i >= 0; i--) {
            if(nums[i]==val){
                nums[i]= nums[size-f];
                nums[size-f]=val;
                f++;
            }
        }
        return size-f+1;
    }


    public int removeElement(int[] nums, int val) {
        int size = nums.length;
        int[] res = new int[size];
        int uniqueSize = 0;
        for (int num : nums) {
            if (num != val) {
                res[uniqueSize] = num;
                uniqueSize++;
            }
        }
        System.arraycopy(res, 0, nums, 0, uniqueSize);
        return uniqueSize;
    }


    private Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{3, 2, 2, 3}, 3, 2),
                arguments(new int[]{2, 2, 3}, 2, 1),
                arguments(new int[]{2, 3, 3}, 2, 2),
                arguments(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, 5)
        );
    }
}

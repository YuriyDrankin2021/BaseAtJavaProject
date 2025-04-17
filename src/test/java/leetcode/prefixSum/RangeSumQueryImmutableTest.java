package leetcode.prefixSum;

import io.qameta.allure.Link;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class RangeSumQueryImmutableTest {

    @Test
    @Link("https://leetcode.com/problems/range-sum-query-immutable/description/?envType=problem-list-v2&envId=prefix-sum")
    public void rangeSumQueryImmutableTest() {
        NumArray array = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(array.sumRange(0,2)).as("0,2").isEqualTo(1);
        softAssertions.assertThat(array.sumRange(2,5)).as("2,5").isEqualTo(-1);
        softAssertions.assertThat(array.sumRange(0,5)).as("0,5").isEqualTo(-3);
        softAssertions.assertAll();
    }

}

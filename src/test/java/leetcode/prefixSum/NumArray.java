package leetcode.prefixSum;

public class NumArray {
    private int[] prefixSum;

    public NumArray(int[] nums) {
        this.prefixSum = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }
    }

    public int sumRange(int left, int right) {
        int lres = left == 0 ? 0 : prefixSum[left - 1];
        return prefixSum[right] - lres;
    }
}

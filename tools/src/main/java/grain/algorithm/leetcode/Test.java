package grain.algorithm.leetcode;

/**
 * @author wulifu
 * @date 2020/8/25 16:54
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        for (int i : new int[]{4, 5, 6, 7, 0, 1, 2}) {
            System.out.println(new Test().search(new int[]{4, 5, 6, 7, 0, 1, 2}, i));
        }
        System.out.println(System.currentTimeMillis());
    }

    public int search(int[] nums, int target) {
        return find(nums, 0, nums.length - 1, target);
    }

    public int find(int[] nums, int left, int right, int target) {
        if (nums[left] == target) {
            return left;
        }
        if (nums[right] == target) {
            return right;
        }
        if (right - left == 2 && nums[left + 1] == target) {
            return left + 1;
        }
        int half = right / 2 + left / 2;
        if (nums[half] == target) {
            return half;
        }
        if (nums[left] < target && nums[half] > target) {
            return find(nums, left, half, target);
        } else if (nums[left] > target && nums[half] > target && nums[half] > nums[left]) {
            return find(nums, half, right, target);
        } else if (nums[left] > target && nums[half] > target && nums[half] < nums[left]) {
            return find(nums, left, half, target);
        } else if (nums[left] > target && nums[half] < target && nums[right] > target) {
            return find(nums, half, right, target);
        } else if (nums[left] < target && nums[half] < target) {
            return find(nums, half, right, target);
        }
        return -1;
    }
}
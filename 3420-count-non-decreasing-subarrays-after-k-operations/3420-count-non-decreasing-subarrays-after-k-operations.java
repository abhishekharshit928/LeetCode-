class Solution {

    class Group {
        int value;
        int count;

        Group(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        Deque<Group> dq = new ArrayDeque<>();

        long cost = 0;
        long ans = 0;

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            while (!dq.isEmpty() && dq.peekLast().value < num) {

                Group g = dq.pollLast();

                count += g.count;

                cost += (long) (num - g.value) * g.count;
            }

            dq.offerLast(new Group(num, count));

            while (cost > k) {

                Group g = dq.pollFirst();

                cost -= (long) (g.value - nums[j]);

                j--;

                if (g.count > 1) {
                    dq.offerFirst(new Group(g.value, g.count - 1));
                }
            }

            ans += j - i + 1;
        }

        return ans;
    }
}

class NodeValue {
    int maxNode;
    int minNode;
    int sum;
    int maxSum;
    boolean isBST;

    NodeValue(int minNode, int maxNode, int sum, int maxSum, boolean isBST) {
        this.minNode = minNode;
        this.maxNode = maxNode;
        this.sum = sum;
        this.maxSum = maxSum;
        this.isBST = isBST;
    }
}

class Solution {

    private NodeValue maxSumBSTHelper(TreeNode root) {

        if (root == null) {
            return new NodeValue(
                Integer.MAX_VALUE,
                Integer.MIN_VALUE,
                0,
                0,
                true
            );
        }

        NodeValue left = maxSumBSTHelper(root.left);
        NodeValue right = maxSumBSTHelper(root.right);

        if (left.isBST &&
            right.isBST &&
            left.maxNode < root.val &&
            root.val < right.minNode) {

            int sum = left.sum + right.sum + root.val;

            return new NodeValue(
                Math.min(root.val, left.minNode),
                Math.max(root.val, right.maxNode),
                sum,
                Math.max(sum, Math.max(left.maxSum, right.maxSum)),
                true
            );
        }

        return new NodeValue(
            Integer.MIN_VALUE,
            Integer.MAX_VALUE,
            0,
            Math.max(left.maxSum, right.maxSum),
            false
        );
    }

    public int maxSumBST(TreeNode root) {
        return maxSumBSTHelper(root).maxSum;
    }
}
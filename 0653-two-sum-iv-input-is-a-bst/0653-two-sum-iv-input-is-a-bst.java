/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        inor(root, inorder);

        int l = 0;
        int r = inorder.size() - 1;

        while(l < r) {
            if(inorder.get(l) + inorder.get(r) == k)
                return true;
            else if(inorder.get(l) + inorder.get(r) < k)
                l++;
            else
                r--;
        }

        return false;
    }

    private void inor(TreeNode root, List<Integer> inorder) {
        if(root == null) return;

        inor(root.left, inorder);
        inorder.add(root.val);
        inor(root.right, inorder);
    }
}
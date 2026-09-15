class Solution {
    private TreeNode insert(TreeNode root, int val){
        if(root == null)
            return new TreeNode(val);

        if(val < root.val)
            root.left = insert(root.left, val);
        else if(val > root.val)
            root.right = insert(root.right, val);

        return root;
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        return insert(root, val);
    }
}
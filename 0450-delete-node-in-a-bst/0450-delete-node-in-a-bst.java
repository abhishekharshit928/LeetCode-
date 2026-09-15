class Solution {
    private TreeNode build(TreeNode curr, TreeNode prev, TreeNode root, int key) {
        if (curr == null) return root;

        if (key < curr.val) {
            return build(curr.left, curr, root, key);
        }

        if (key > curr.val) {
            return build(curr.right, curr, root, key);
        }

        // curr is the node to delete

        // curr is root
        if (prev == null) {

            // no left child
            if (curr.left == null)
                return curr.right;

            // no right child
            if (curr.right == null)
                return curr.left;

            // two children
            TreeNode left = curr.left;
            TreeNode right = curr.right;

            TreeNode temp = left;

            while (temp.right != null) {
                temp = temp.right;
            }

            temp.right = right;

            return left;
        }

        // leaf
        if (curr.left == null && curr.right == null) {
            if (prev.left == curr)
                prev.left = null;
            else
                prev.right = null;

            return root;
        }

        // only right child
        if (curr.left == null) {
            if (prev.left == curr)
                prev.left = curr.right;
            else
                prev.right = curr.right;

            return root;
        }

        // only left child
        if (curr.right == null) {
            if (prev.left == curr)
                prev.left = curr.left;
            else
                prev.right = curr.left;

            return root;
        }

        // two children
        TreeNode left = curr.left;
        TreeNode right = curr.right;

        TreeNode temp = left;

        while (temp.right != null) {
            temp = temp.right;
        }

        temp.right = right;

        if (prev.left == curr)
            prev.left = left;
        else
            prev.right = left;

        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        return build(root, null, root, key);
    }
}
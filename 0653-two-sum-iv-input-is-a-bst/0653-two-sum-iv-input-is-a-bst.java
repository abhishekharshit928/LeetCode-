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
// class Solution {
//     public boolean findTarget(TreeNode root, int k) {
//         List<Integer> inorder = new ArrayList<>();
//         inor(root, inorder);

//         int l = 0;
//         int r = inorder.size() - 1;

//         while(l < r) {
//             if(inorder.get(l) + inorder.get(r) == k)
//                 return true;
//             else if(inorder.get(l) + inorder.get(r) < k)
//                 l++;
//             else
//                 r--;
//         }

//         return false;
//     }

//     private void inor(TreeNode root, List<Integer> inorder) {
//         if(root == null) return;

//         inor(root.left, inorder);
//         inorder.add(root.val);
//         inor(root.right, inorder);
//     }

class BSTIterator {
    private java.util.Stack<TreeNode> stack;
    private boolean reverse;

    BSTIterator(TreeNode root, boolean isReverse) {
        stack = new java.util.Stack<>();
        reverse = isReverse;
        pushAll(root);
    }

    boolean hasNext() {
        return !stack.isEmpty();
    }

    int next() {
        TreeNode tmpNode = stack.pop();

        if (!reverse) {
            pushAll(tmpNode.right);
        } else {
            pushAll(tmpNode.left);
        }

        return tmpNode.val;
    }

    private void pushAll(TreeNode node) {
        while (node != null) {
            stack.push(node);

            if (reverse) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
    }
}

class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;

        BSTIterator l = new BSTIterator(root, false);
        BSTIterator r = new BSTIterator(root, true);

        int i = l.next();
        int j = r.next();

        while (i < j) {
            if (i + j == k) return true;
            else if (i + j < k) i = l.next();
            else j = r.next();
        }

        return false;
    }
}
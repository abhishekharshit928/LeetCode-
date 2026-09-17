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
    public TreeNode bstFromPreorder(int[] preorder) {
        
        HashMap<Integer , Integer> map = new HashMap<>();
        for(int i = 0 ; i < preorder.length ; i++){
            map.put(preorder[i] , i);
        }
        return build(preorder,0, preorder.length-1 , map);
        
    }
    private TreeNode build(int[] preorder, int strt, int end,
                       HashMap<Integer, Integer> map) {

    if(strt > end) return null;

    TreeNode root = new TreeNode(preorder[strt]);

    int idx = -1;

    for(int i = strt + 1; i <= end; i++){
        if(preorder[i] > root.val){
            idx = i;
            break;
        }
    }

    if(idx == -1){
        root.left = build(preorder, strt + 1, end, map);
        root.right = null;
    }
    else{
        root.left = build(preorder, strt + 1, idx - 1, map);
        root.right = build(preorder, idx, end, map);
    }

    return root;
}
}
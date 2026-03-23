class tNode {
    int key;
    tNode left, right;

    public tNode(int key) {
        this.key = key;
        left = right= null;
    }
}
public class tryBST {
    tNode root;
    public tNode insert(tNode node, int key) {
        if(node==null) {
            return new tNode(key);
        }

        if(key< node.key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }
        return node;

    }
    public void balancedBST(int start, int end){
        if(start > end) {
            return;
        }
        
        int mid = (start + end)/ 2;
        root = insert(root, mid);
        
        balancedBST(start, mid - 1);
        balancedBST(mid + 1, end);
    }
    public boolean isBST(tNode node, int min, int max){
        if(node == null){
            return true;
        }
        if(node.key <= min || node.key >= max){
            return false;
        }
        return isBST(node.left, min, node.key ) && isBST(node.right, node.key, max);
        
    }
    

}

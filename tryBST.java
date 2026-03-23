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
    
    public  tNode findMin(tNode node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
    //This is to delete the node
    public tNode delete(tNode node, int key){
        if (node == null){
            return null;
        }
        
        if(key < node.key){
            node.left = delete(node.left,key);
        } else if (key > node.key){
            node.right = delete(node.right, key);
        } else {
            if(node.left == null && node.right == null){
                return null;
            }
            if(node.left == null){
                return node.right;
            }
            if(node.right == null){
                return node.left;
            }
            
            tNode temp = findMin(node.right);
            node.key = temp.key;
            node.right = delete(node.right, temp.key);
        }
        return node;
    }
    //Removing all even numbers
    public void removeEven(int max){
        for (int i = 2; i < max; i = i + 2){
            root = delete(root, i);
        }
    }
    


}

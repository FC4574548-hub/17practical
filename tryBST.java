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
        if(node==null)
            return new tNode(key);
        
        if(key< node.key)
            node.left = insert(node.left, key);
        else 
            node.right = insert(node.right, key);
        return node;
        


    }
    
}

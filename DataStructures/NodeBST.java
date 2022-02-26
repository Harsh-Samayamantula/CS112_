public class NodeBST 
{
    int key;
    String value;
    NodeBST left;
    NodeBST right;

    public NodeBST(int key, String value, NodeBST left, NodeBST right)
    {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public NodeBST(int key, String value)
    {
        this.key = key;
        this.value = value;
    }
    
    public int size(NodeBST node) { 
        if (node == null) return(0); 
        else { 
          return(size(node.left) + 1 + size(node.right)); 
        } 
      }
}

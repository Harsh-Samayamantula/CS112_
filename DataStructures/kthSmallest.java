
public class kthSmallest
{
    public NodeBST ksmall(NodeBST root, int k)
    {
        if(size(root.left) == k-1) return root;
        if(k <= size(root.left))
        {
            return ksmall(root.left, k);
        }
        return ksmall(root.right, k-size(root.left)-1);
    }

    public static int size(NodeBST node) { 
        if (node == null) return(0); 
        else { 
          return(size(node.left) + 1 + size(node.right)); 
        } 
      }

    
}
public int max()
{
    return max(root);
}
private static int max(Node n)
{
    if(n.right != null)
    {
        max(n.right);
    }
    else
    {
        return n.data;
    }
}

private static boolean search(Node n, int k)
{
    if(n.key < k)
    {
        return search(n.right, k);
    }
    else if(n.key > k)
    {
        return search(n.left, k);
    }
    else if(n.key == k)
    {
        return true;
    }
    return false;
}

private static Integer ceiling(Node n, int target)
{
    if(n.key == target)
    {
        return Integer.valueOf(n.key);
    }
    if(n.left.key == null)
    {
        return Integer.valueOf(n.key);
    }
    if(n.left.key >= target)
    {
        return ceiling(n.left, target);
    }
    else if(n.right.key >=target)
    {
        
    }

}
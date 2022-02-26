public class NodeCL<T>
{
    T data;
    NodeCL<T> next;

    public NodeCL (T data, NodeCL<T> next)
    {
        this.data = data;
        this.next = next;
    }
    public NodeCL(){}
    public NodeCL(T data)
    {
        this.data = data;
    }
}

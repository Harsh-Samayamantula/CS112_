public class NodeDLL<T> 
{
    T data;
    NodeDLL<T> next;
    NodeDLL <T> prev;
    public NodeDLL(T item, NodeDLL<T> next, NodeDLL<T> prev)
    {
        data = item;
        this.next = next;
        this.prev = prev;
    }
    public NodeDLL(){}
    public NodeDLL(T item)
    {
        data = item;
    }
}

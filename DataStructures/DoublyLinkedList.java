public class DoublyLinkedList <T>
{
    NodeDLL<T> first;
    int size;

    public void print()
    {
        NodeDLL<T> ptr = first;
        if(ptr==null) return;
        while(ptr!=null)
        {
            System.out.println(ptr.data);
            ptr = ptr.next;
        }
    }

    public void insertAfter(T target)
    {
        NodeDLL<T> ptr = first;

        while(ptr !=null)
        {
            if(ptr.data == target)
            {
                if(ptr.next!=null)
                {
                    NodeDLL<T> targetNode = new NodeDLL<>(target);
                    targetNode.next = ptr.next;
                    ptr.next = targetNode;
                    targetNode.prev = ptr;
                    size++;
                    return;
                }
                ptr.next = new NodeDLL<>(target);
                ptr.next.prev = ptr;
                size++;
                return;
            }
            ptr = ptr.next;
        }
    }

    public void addToFront(T data)
    {
        NodeDLL<T> newNode = new NodeDLL<>(data);
        if(first != null)
        {
            first.prev = newNode;
            newNode.next = first;
        }
        first = newNode;
        size++;
    }

    public static void main(String[]args)
    {
        
    }
}

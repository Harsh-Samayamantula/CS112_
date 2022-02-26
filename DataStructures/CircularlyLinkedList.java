public class CircularlyLinkedList <T>
{
    NodeCL<T> last;
    int size;

    public void addToFront(T data)
    {
        NodeCL ptr = last;
        if(ptr== null)
        {
            ptr = new NodeCL<T>(data, ptr);
            return;
        }
        if(ptr.next == ptr)
        {
            ptr.next = new NodeCL<T>(data, ptr);
            return;
        }
        NodeCL temp = ptr.next;
        ptr.next = new NodeCL<T>(data, temp);
    }

    public void removeFront()
    {
        if(last.next == last)
        {
            last = null;
        }
        if(last == null) return;
        last.next = last.next.next;
    }

    public void print()
    {
        NodeCL ptr = last;
        if(last == null) return;
        
        while(ptr.next != last)
        {
            
            System.out.println(ptr.next.data);
            ptr = ptr.next;
        }
        if(ptr.next == last)
            {
                System.out.println(ptr.next.data);
            }
    }

    public void deleteTarget(T target)
    {
        if(last == null) return;
        NodeCL ptr = last;
        if(ptr.data == target) {ptr = null; return;}
        while(ptr.next != ptr)
        {
            if(ptr.next.data == target)
            {
                ptr.next = ptr.next.next;
                return;
            }
            ptr = ptr.next;
        }
    }


}

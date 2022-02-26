public class LinkedList 
{
    Node head;
    public void append(Node n, int target) //Appends Node after the Node with the target value and if no target value is matched it adds at the end of the linked list
    {
        for(Node pointer = head; pointer != null; pointer = pointer.next)
        {
            if(pointer.next == null)
            {
                pointer.next = n;
                break;
            }
            if(pointer.data == target)
            {
                n.next = pointer.next;
                pointer.next = n;
            }
        }
    }

    public void prepend(Node n)
    {
        n.next = head;
        head = n;
    }

    public void printList()
    {
        Node pointer = head;
        while(pointer != null)
        {
            System.out.println(pointer.data);
            pointer = pointer.next;
        }
    }
    public static void main (String[]args)
    {
        LinkedList linkedlist = new LinkedList();
        Node h = new Node(4);
        //linkedlist.printList();
        linkedlist.head = h;
        linkedlist.printList();
        linkedlist.append(new Node(8), 9);
        linkedlist.printList();
        Node x = new Node(6);
        linkedlist.prepend(x);
        linkedlist.printList();
        linkedlist.prepend(new Node(5));
        linkedlist.printList();
    }
}

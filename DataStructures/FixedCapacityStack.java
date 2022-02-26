import java.util.Scanner;
public class FixedCapacityStack 
{
    private String[] arr;
    private int counter;

    public FixedCapacityStack(int size)
    {
        arr = new String[size];
        counter = 0;
    }

    public boolean isEmpty()
    {
        if(counter == 0)
        {
            return true;
        }
        return false;
    }

    public void getArr()
    {
        for(String a: arr)
        {
            System.out.println(a);
        }
    }
    public void push(String item)
    {
        arr[counter] = item;
        counter++;
    }

    public String pop()
    {
        String valuePopped = arr[--counter];
        arr[counter] = null;
        return valuePopped;
    }

    public static void main(String[]args)
    {
        Scanner scan = new Scanner(System.in);
        FixedCapacityStack stack = new FixedCapacityStack(5);
        String a;
        int count = 0;
        while(true) 
        {
            a = scan.nextLine();
            if(a.isEmpty())
            {
                break;
            }
            if(stack.arr.length-1 == count)
            {
              System.out.println("This is the last element.");
            }
                try
                {
                    stack.push(a);
                    count++;
                }
                catch(ArrayIndexOutOfBoundsException e)
                {
                    System.out.println("Cannot add this element. Stack is full.");
                }
        }
        stack.getArr();
        String x = stack.pop();
        stack.getArr();
        stack.push(x);
        stack.getArr();
        scan.close();
    }
}

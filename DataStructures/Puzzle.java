import java.util.Scanner;
public class Puzzle 
{
    public static void main(String[]args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a positive integer");
        int n = input.nextInt();
        if(n<=0)
        {
            System.out.println("You can't read instructions you dipshit");
        }
        int a = 1;
        int b = n;
        while(true)
        {
            if(a*b == n)
            {
                if(isEven(a, b))
                {
                    break;
                }
            }      
            a++;
            b = n/a;
        }
        System.out.println("A is " + a + ", and B is " + b + ".");
        input.close();
    }
    public static boolean isEven(int a, int b)
    {
        if(a+b %2 == 0)
        {
            return true;
        }
        return false;
    }
}

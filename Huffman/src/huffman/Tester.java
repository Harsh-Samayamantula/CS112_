package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Tester {
    public static void main(String[]args)
    {
        
        try{
        FileInputStream in = new FileInputStream("input1");
        File file = new File("input1");

        byte arr[] = new byte[(int) file.length()];
        in.read(arr);
        in.close();
        for(byte a: arr)
        {
            System.out.println(a);
        }
        }
        catch (Exception e)
        {
            System.out.println("dumbass");
        }
        

 
        
    }
}

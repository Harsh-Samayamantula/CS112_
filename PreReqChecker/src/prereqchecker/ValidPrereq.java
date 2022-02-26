package prereqchecker;
import java.util.ArrayList;
/**
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq 
{
    public static void main(String[] args) 
    {
        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
            return;
        }
        Graph prereq = new Graph(args[0]);
        StdIn.setFile(args[1]);
        String[] input = StdIn.readAllLines();
        StdOut.setFile(args[2]);
        //ArrayList<String> adjacencies = prereq.adjacentTo(input[0]);
        DFS search = new DFS(prereq, input[1]);
        //DFS search2 = new DFS(prereq, input[0]);
        String output = "";
        String[][] cycleSearch = search.getVisited();
        /*
        String[][] prereqSearch = search2.getVisited();
        for(int i = 0; i < prereq.getSize(); i++)
        {
            if(prereqSearch[i][0].equals(input[1]) && prereqSearch[i][1].equals("true"))
            {
                output = "YES";
                break;
            }
        }
        */
        prereq.addPreReq(input[0], input[1]);
        for(int i = 0; i < prereq.getSize(); i++)
        {
            if(cycleSearch[i][0].equals(input[0]) && cycleSearch[i][1].equals("true"))
            {
                output = "NO";
                break;
            }
        }
        if(output.equals("")){output = "YES";}
        StdOut.print(output);
    }
}

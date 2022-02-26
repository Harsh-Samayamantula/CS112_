package prereqchecker;

import java.util.*;

/**
 * 
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
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }
        Graph prereq = new Graph(args[0]);
        StdIn.setFile(args[1]);
        String[] input = StdIn.readAllLines();
        StdOut.setFile(args[2]);
        int c = Integer.valueOf(input[0]);
        String[] keys = prereq.returnKeys();
        ArrayList<String> targets = new ArrayList<String>();
        for(int k = 0; k < c; k++)
        {
            targets.add(input[k+1]);
        }
        DFS coursesTaken = new DFS(prereq, targets);
        for(int i = 0; i < keys.length; i++)
        {
            if(!coursesTaken.visited(keys[i]))
            {
                boolean eligibility = true;
                ArrayList<String> adjacencies = prereq.adjacentTo(keys[i]);
                for(String adj: adjacencies)
                {
                    if(coursesTaken.visited(adj) == false)
                    {
                        eligibility = false;
                        break;
                    }
                }
                if(eligibility == true)
                {
                    StdOut.println(keys[i]);
                }
            }

        }
    }
}

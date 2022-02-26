package prereqchecker;

import java.util.*;

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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
            return;
        }
        Graph prereq = new Graph(args[0]);
        StdIn.setFile(args[1]);
        StdOut.setFile(args[2]);
        String[] input = StdIn.readAllLines();

        String advTarget = input[0];
        int numCoursesTaken = Integer.valueOf(input[1]);
        ArrayList<String> targets = new ArrayList<String>();
        for(int k = 0; k < numCoursesTaken; k++)
        {
            targets.add(input[k+2]);
        }

        DFS taken = new DFS(prereq, targets);
        DFS advancedPrereqs = new DFS(prereq, advTarget);

        advancedPrereqs.markUnvisited(advTarget);

        ArrayList<String> needToTake = new ArrayList<String>();

        String[][] requiredCourses = advancedPrereqs.getVisited();

        for(int i = 0; i < requiredCourses.length; i++)
        {
            String course = requiredCourses[i][0];
            if(advancedPrereqs.visited(course) && taken.visited(course) == false)
            {
                needToTake.add(course);
            }
        }

        for(String course: needToTake)
        {
            StdOut.println(course);
        }
    }
}

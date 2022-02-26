import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
	/**
	 * Makes a layered linked list representing the given arrays of train stations, bus
	 * stops, and walking locations. Each layer begins with a location of 0, even though
	 * the arrays don't contain the value 0.
	 * 
	 * @param trainStations Int array listing all the train stations
	 * @param busStops Int array listing all the bus stops
	 * @param locations Int array listing all the walking locations (always increments by 1)
	 * @return The zero node in the train layer of the final layered linked list
	 */
	public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
		// WRITE YOUR CODE HERE
		//Making layer of nodes for train stops
		TNode nodeZero = new TNode(0);
		makeLayer(trainStations, nodeZero);
		
		//Making layer of nodes for bus stops
		TNode busNodeZero = new TNode(0);
		makeLayer(busStops, busNodeZero);

		//Making layer of nodes for walking
		TNode walkNodeZero = new TNode(0);
		makeLayer(locations, walkNodeZero);

		//Connecting nodeZeros for train, bus, and walk
		nodeZero.down = busNodeZero;
		busNodeZero.down = walkNodeZero;

		//Connecting all layers
		connectLayer(nodeZero, busNodeZero, trainStations, busStops);
		connectLayer(busNodeZero, walkNodeZero, busStops, locations);
		return nodeZero;
	}

	//Iterates through the list of ints to make a layer of nodes that is attached to nodezero
	private static void makeLayer(int[] arr, TNode nodeZero)
	{
		TNode pointer = nodeZero;
		for(int i = 0; i < arr.length; i++)
		{
			pointer.next = new TNode(arr[i]);
			pointer = pointer.next;
		}
	}

	//Iterates through the existing layer and returns the node with the location a
	private static TNode findNode(int a, TNode nodeZero)
	{
		TNode pointer = nodeZero;
		while(pointer != null)
		{
			if(pointer.location == a)
			{
				return pointer;
			}
			pointer = pointer.next;
		}
		return null;
	}
	
	//Iterates the array of ints, finds matching stops, uses findNode() and connects them through TNode.down
	private static void connectLayer(TNode nodeZeroLayer1, TNode nodeZeroLayer2, int[]layer1, int[]layer2)
	{
		for(int a: layer1)
		{
			for(int b: layer2)
			{
				if(a == b)
				{

					TNode ptr1 = findNode(a, nodeZeroLayer1);
					TNode ptr2 = findNode(b, nodeZeroLayer2);
					if(ptr1 != null && ptr2 != null) {ptr1.down = ptr2;}
					break;
				}
			}
		}
	}

	/**
	 * Modifies the given layered list to remove the given train station but NOT its associated
	 * bus stop or walking location. Do nothing if the train station doesn't exist
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param station The location of the train station to remove
	 */
	public static void removeTrainStation(TNode trainZero, int station) 
	{
		// WRITE YOUR CODE HERE
		TNode pointer = trainZero;
		TNode prev = null;
		if(pointer == null){return;}
		while(pointer.next != null)
		{
			if(pointer.next.location == station)
			{
				prev = pointer;
				break;
			}
			pointer = pointer.next;
		}
		if(prev == null){return;}
		prev.next = prev.next.next;
	}

	/**
	 * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
	 * if there is no corresponding walking location.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param busStop The location of the bus stop to add
	 */
	public static void addBusStop(TNode trainZero, int busStop) 
	{
		// WRITE YOUR CODE HERE
		TNode busZero = trainZero.down;
		TNode pointer = busZero;
		if(pointer == null) {return;}
		while(pointer.next != null)
		{
			if(pointer.next.location == busStop) { return;}
			if(pointer.next.location > busStop)
			{
				break;
			}
			pointer = pointer.next;
		}
		if(pointer.next == null)
		{
			TNode walkPointer = pointer.down;
			pointer.next = new TNode(busStop);
			while(walkPointer!=null)
			{
				if(walkPointer.location == busStop)
				{
					break;
				}
				walkPointer = walkPointer.next;
			}
			pointer.next.down = walkPointer;
		}
		else{
			TNode walkPointer = pointer.down;
			TNode holder = pointer.next;
			pointer.next = new TNode(busStop);
			pointer.next.next = holder;
			while(walkPointer!=null)
			{
				if(walkPointer.location == busStop)
				{
					break;
				}
				walkPointer = walkPointer.next;
			}
			pointer.next.down = walkPointer;
		}
	}
	
	/**
	 * Determines the optimal path to get to a given destination in the walking layer, and 
	 * collects all the nodes which are visited in this path into an arraylist. 
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param destination An int representing the destination
	 * @return
	 */
	public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {
		// WRITE YOUR CODE HERE
		ArrayList<TNode> bestPath = new ArrayList<>();
		TNode pointer;
		for(pointer = trainZero; pointer != null; pointer = pointer.next)
		{
			if(pointer.location == destination)
			{
				bestPath.add(pointer);
				bestPath.add(pointer.down);
				bestPath.add(pointer.down.down);
				return bestPath;
			}
			if(pointer.next == null && pointer.location < destination )
			{
				bestPath.add(pointer);
				break;
			}
			if(pointer.next.location <= destination)
			{
				bestPath.add(pointer);
			}
			else if(pointer.next.location > destination)
			{
				bestPath.add(pointer);
				break;
			}
		}
		for(pointer = pointer.down; pointer != null; pointer = pointer.next)
		{
			if(pointer.location == destination)
			{
				bestPath.add(pointer);
				bestPath.add(pointer.down);
				return bestPath;
			}
			if(pointer.next == null && pointer.location < destination )
			{
				bestPath.add(pointer);
				break;
			}
			if(pointer.next.location <= destination)
			{
				bestPath.add(pointer);
			}
			else if(pointer.next.location > destination)
			{
				bestPath.add(pointer);
				break;
			}
		}
		for(pointer = pointer.down; pointer != null; pointer = pointer.next)
		{
			if(pointer.location == destination)
			{
				bestPath.add(pointer);
				return bestPath;
			}
			if(pointer.next == null && pointer.location < destination )
			{
				bestPath.add(pointer);
				break;
			}
			if(pointer.next.location <= destination)
			{
				bestPath.add(pointer);
			}
			else if(pointer.next.location > destination)
			{
				bestPath.add(pointer);
				break;
			}
		}

		/*
		ArrayList<TNode> bestPath = new ArrayList<>();
		bestPath.add(trainZero);
		TNode pointer = trainZero;
		while(pointer.next != null)
		{
			if(pointer.next.location == destination)
			{
				pointer = pointer.next;
				bestPath.add(pointer);
				while(pointer.down != null)
				{
					bestPath.add(pointer.down);
					pointer = pointer.down;
				}
				return bestPath;
			}
			if(pointer.next.location < destination && pointer != null)
			{
				pointer = pointer.next;
				bestPath.add(pointer);
			}
			else if(pointer.next.location > destination && pointer != null)
			{
				pointer = pointer.down;
				bestPath.add(pointer);
			}
			if(pointer.next == null && pointer.down != null)
			{
				pointer = pointer.down;
				bestPath.add(pointer);
			}
		}
		*/

		return bestPath;
	}

	/**
	 * Returns a deep copy of the given layered list, which contains exactly the same
	 * locations and connections, but every node is a NEW node.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @return
	 */
	public static TNode duplicate(TNode trainZero) {
		// WRITE YOUR CODE HERE
		//Making 3 new root nodes
		TNode nodeZero = new TNode(0);
		TNode busZero = new TNode(0);
		TNode walkZero = new TNode(0);
		//Connecting 3 root nodes
		nodeZero.down = busZero;
		busZero.down = walkZero;
		//Walk Node Loop
		TNode pointer = trainZero.down.down;
		TNode newPointer = walkZero;
		while(pointer.next != null)
		{
			newPointer.next= new TNode(pointer.next.location);
			pointer = pointer.next;
			newPointer = newPointer.next;
		}
		//Bus Node Loop
		pointer = trainZero.down;
		newPointer = busZero;
		while(pointer.next != null)
		{
			newPointer.next = new TNode(pointer.next.location);
			newPointer.next.down = findNode(pointer.next.location, walkZero);
			pointer = pointer.next;
			newPointer = newPointer.next;
		}
		//Train Node Loop
		pointer = trainZero;
		newPointer = nodeZero;
		while(pointer.next != null)
		{
			newPointer.next = new TNode(pointer.next.location);
			newPointer.next.down = findNode(pointer.next.location, busZero);
			pointer = pointer.next;
			newPointer = newPointer.next;
		}
		return nodeZero;
	}
	
	/**
	 * Modifies the given layered list to add a scooter layer in between the bus and
	 * walking layer.
	 * 
	 * @param trainZero The zero node in the train layer of the given layered list
	 * @param scooterStops An int array representing where the scooter stops are located
	 */
	public static void addScooter(TNode trainZero, int[] scooterStops) {
		// WRITE YOUR CODE HERE
		//Making and connecting the nodeZero for scooter
		TNode scooterZero = new TNode();
		TNode temp = trainZero.down.down;
		trainZero.down.down = scooterZero;
		scooterZero.down = temp;
		//useful nodeZeros
		TNode busZero = trainZero.down;
		TNode walkZero = scooterZero.down;
		//For loop to iterate through scooter stops and connecting them to walk stops and bus stops if existing
		TNode pointer = scooterZero;
		for(int i = 0; i < scooterStops.length; i++)
		{
			pointer.next = new TNode(scooterStops[i]);
			if(findNode(scooterStops[i], busZero) != null)
			{
				findNode(scooterStops[i], busZero).down = pointer.next;
			}
			pointer.next.down = findNode(scooterStops[i], walkZero);
			pointer = pointer.next;
		}
	}
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class UndirectGraph {
	private HashMap<String, LinkedList<Edge>> graph = new HashMap<String, LinkedList<Edge>>();
	
	// LOADMAP: Takes file input and processes it into a hashmap with movies as the key and actor values
	public HashMap<String, LinkedList<Edge>> loadMap(String name) {
		
		HashMap<String, LinkedList<String>> map = new HashMap<String, LinkedList<String>>();
		
		// Read file and add to an movie -> actor hashmap
		try {
			// Open the file
			File file = new File(name);
            Scanner scan = new Scanner(file);
            
            // Iterate through the file
            while(scan.hasNextLine()) {
            	// read line and separate into two strings
            	String line = scan.nextLine().trim();
            	String[] parts = line.split("\\|");
            	// If movie isn't added it adds it. otherwise add new actor to linked list
            	map.computeIfAbsent(parts[1], actors -> new LinkedList<>()).add(parts[0]);
            }
			scan.close();
		}catch (FileNotFoundException e){
			System.out.println("File not found.");
		}
		
		// Restructure map to an undirected graph format
		toGraph(map);
		return graph;
	}
	
	// TOGRAPH: Build an adjacency list by linking all actors to each other in each movie and adding to map
	public void toGraph(HashMap<String, LinkedList<String>> map) {
			// Iterate through hash map values
			for (Map.Entry<String, LinkedList<String>> values : map.entrySet()) {
				// Pull list of actors out
		        LinkedList<String> actors = values.getValue();

		        // For each actor in a movie, it links the successive actors to it with edges.
		        for (int i = 0; i < actors.size(); i++) {
		            for (int j = i + 1; j < actors.size(); j++) {
		                String actor1 = actors.get(i);
		                String actor2 = actors.get(j);

		                // Edge from actor1 to actor2
		                Edge edge1 = new Edge(values.getKey(), actor1, actor2);
		                // If actor1 isn't in graph, add them. otherwise adds edge to actor
		                graph.computeIfAbsent(actor1, edges -> new LinkedList<>()).add(edge1);

		                // Edge from actor2 to actor1
		                Edge edge2 = new Edge(values.getKey(), actor2, actor1);
		                // If actor2 isn't in graph, add them. otherwise adds edge to actor
		                graph.computeIfAbsent(actor2, edges -> new LinkedList<>()).add(edge2);
		            }
		        }
		    }
	}
	
	// SHORTESTPATH: Gets a start and find and uses djristkas to find the shortest path
	public void shortestPath() {
		 // Get the starting and ending actor.
		 Scanner scanner = new Scanner(System.in);
	     System.out.println("Enter starting actor:");
	     String start = scanner.nextLine().trim();
	     System.out.println("Enter ending actor:");
	     String end = scanner.nextLine().trim();
	     
	     // End method if the actors aren't in the list.
		 if (!graph.containsKey(start) || !graph.containsKey(end)) {
		 	System.out.println("Path does not exist to " + end);
		 	return;
		 }
		 
		// Records distance 
		Map<String, Double> distance = new HashMap<>();
		Map<String, Edge> previous = new HashMap<>();
		PriorityQueue<PriorityVertex> visitQueue = new PriorityQueue<>();

		// Set the possible distances from start to nodes to infinity
		for (String actor : graph.keySet()) {
			distance.put(actor, Double.POSITIVE_INFINITY);
		}

		// Update the distance from start to start to zero
		distance.put(start, 0.0);
		// Picking start as where we will start running djristkas 
		visitQueue.add(new PriorityVertex(start, 0));

		// Djristkas algorithm
		while (!visitQueue.isEmpty()) {
			// Vertext at top has shortest distance
			PriorityVertex current = visitQueue.poll();
			String currentActor = current.getVertex();

			// Loop through each neighbor
			for (Edge edge : graph.get(currentActor)) {
				String neighbor = edge.getV2();
				// Edge weight of 1
		        double newDist = distance.get(currentActor) + 1;
		        
		        // If the path to the neighbor is shorter
		        if (newDist < distance.get(neighbor)) {
		        	// Adds neighbor to the queue
		        	distance.put(neighbor, newDist);
		            previous.put(neighbor, edge);
		            visitQueue.add(new PriorityVertex(neighbor, newDist));
		        }
		    }
		 }

		// End method if the path doesn't work
		 if (!previous.containsKey(end)) {
			 System.out.println("Path does not exist to " + end);
		     return;
		 }

		 // Go backwards and build the path up in order
		 LinkedList<Edge> path = new LinkedList<>();
		 String current = end;
		 while (!current.equals(start)) {
		     Edge edge = previous.get(current);
		     path.addFirst(edge);
		     current = edge.getV1();
		 }

		 // Print out the path
		 int i =0;
		 for (Edge edge : path) {
			 i++;
		     System.out.println(edge.toString());
		 }
		 System.out.println("\n\rNumber of hops: " + i + "\n\r");
	}
}
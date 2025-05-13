import java.util.*;

public class MST {
	private HashSet<Edge> mst = new HashSet<Edge>();
	private HashMap<String, LinkedList<String>> adjList = new HashMap<String, LinkedList<String>>();
	
	// MSTFINO : builds an output information from the mst listing movies and edges
	public void mstInfo() {
		// Build the mst info output
		String output = "\r\nEdges in MST:\r\n"
				+ "---------------------\r\n";
		
		// Iterate and print the mst
		int i = 0;
		for (Edge e : mst) {
			i++;
			output += i + ". " + e.toString() + "\r\n";
		}
		output += "\r\n"
				+ "List of movies to watch that covers all 30 Actors:\r\n"
				+ "---------------------\r\n";
		
		// iterate through the mst add save all the movies at each edge
		HashSet<String> movies = new HashSet<>();
	    for (Edge e : mst) {
	        movies.add(e.getMovie());
	    }
	    
	    i = 0;
	    for (String movie : movies) {
	        i++;
	        output += i + ". " + movie + "\r\n";
	    }
		
		System.out.println(output);
	}
	
	// GETMST: takes unweighted undirected graph and turns it into MST
	public HashSet<Edge> getMST(HashMap<String, LinkedList<Edge>> graph) {
		
	    // Connected Component Marker
	    HashMap<String, Integer> ccm = new HashMap<>();
	    int id = 0;
	    for (String actor : graph.keySet()) {
	    	// Builds ccm with each actor name
	        ccm.put(actor, id++);
	    }

	    // Holds seen and unique edges
	    HashSet<String> seen = new HashSet<>();
	    ArrayList<Edge> allEdges = new ArrayList<>();
	    
	    // Iterate through graph and add collects unique edges
	    for (String actor : graph.keySet()) {
	        for (Edge e : graph.get(actor)) {
	            String key1 = e.getV1() + "|" + e.getV2();
	            String key2 = e.getV2() + "|" + e.getV1();
	            // If the edge doesn't exist yet, adds it to the list of edges
	            if (!seen.contains(key1) && !seen.contains(key2)) {
	                allEdges.add(e);
	                seen.add(key1);
	            }
	        }
	    }

	    // Main Kruskal's loop, goes through each edge and decides to add it to MST or not
	    for (Edge e : allEdges) {
	        String from = e.getV1();
	        String to = e.getV2();

	        // Check for a cycle 
	        if (!ccm.get(from).equals(ccm.get(to))) {
	            this.mst.add(e); // Add edge to MST

	            // Merge components: update all actors with 'to' marker to 'from' marker
	            int oldMarker = ccm.get(to);
	            int newMarker = ccm.get(from);
	            for (String actor : ccm.keySet()) {
	                if (ccm.get(actor) == oldMarker) {
	                    ccm.put(actor, newMarker);
	                }
	            }
	        }
	    }


		// Build adjacency list
		adjacencyList();
	    
		return mst;
		
	}

	// LONGESTPATH: Finds the longest possible path in the MST
	public void longestPath() {
	    // Start at first actor in MST
	    String start = adjList.keySet().iterator().next();

	    // Find the farthest node from the start mode
	    String furthestNode = bfsFurthest(adjList, start);

	    // Go from the farthest node back to the start, using bfs to add up the actor names in order
	    LinkedList<String> longestPath = bfsPath(adjList, furthestNode);

	    // Print the path
	    System.out.println("\r\nLongest path in the MST:\r\n---------------------\r\n");
	    for (String actor : longestPath) {
	        System.out.print(actor);
	        if (!actor.equals(longestPath.get(longestPath.size() - 1))) {
	            System.out.print(" -> ");
	        }
	    }
	    System.out.println("\r\nNumber of hops: " + (longestPath.size() - 1));
	}
	
	// ADJACENCYLIST: Build an adjacency list from the MST
	public void adjacencyList() {
        for (Edge edge : mst) {
            String V1 = edge.getV1();
            String V2 = edge.getV2();

            this.adjList.putIfAbsent(V1, new LinkedList<>());
            this.adjList.putIfAbsent(V2, new LinkedList<>());

            this.adjList.get(V1).add(V2);
            this.adjList.get(V2).add(V1);
        }
	}
	
	// BFSFURTHEST: Finds farthest vertex from start using breadth first search
	private String bfsFurthest(HashMap<String, LinkedList<String>> adjList, String start) {
	   // Keep track of the vertexes that have been visited and that need to be visited
		Queue<String> queue = new LinkedList<>();
	    Set<String> visited = new HashSet<>();
	    queue.add(start);
	    visited.add(start);

	    // Starts at the first vertex in mst
	    String current = start;
	    // Iterates through the queue and looks at the top value of it until its empty
	    while (!queue.isEmpty()) {
	        current = queue.poll();
	        // Collects all the connected vertexes of the current vertex
	        for (String neighbor : adjList.get(current)) {
	        	// If these vertexes haven't been checked yet, there added to the queue
	            if (!visited.contains(neighbor)) {
	                visited.add(neighbor);
	                queue.add(neighbor);
	            }
	        }
	    }

	    // Reaches the last vertex that hasnt been visited, which is farthest from start
	    return current;
	}

	// BFSPATH: Uses breadth first to create a string from , then reverses
	private LinkedList<String> bfsPath(HashMap<String, LinkedList<String>> adjList, String furthest) {
	    // Keep track of vertexes and their connection, starting at the furthest node
		Queue<String> queue = new LinkedList<>();
	    Map<String, String> parent = new HashMap<>();
	    Set<String> visited = new HashSet<>();
	    queue.add(furthest);
	    visited.add(furthest);

	    // While there are still vertexes to visit, looks at each first vertex in queue
	    while (!queue.isEmpty()) {
	        String current = queue.poll();
	        furthest = current;

	        // Iterates through each vertex and collects its connected vertexes
	        for (String neighbor : adjList.get(current)) {
	            if (!visited.contains(neighbor)) {
	                visited.add(neighbor);
	                // Saves each edge used as two strings
	                parent.put(neighbor, current);
	                queue.add(neighbor);
	            }
	        }
	    }

	    // Trace back the path from furthest to start
	    LinkedList<String> path = new LinkedList<>();
	    String current = furthest;
	    while (current != null) {
	        path.addFirst(current);
	        current = parent.get(current);
	    }

	    return path;
	}
}
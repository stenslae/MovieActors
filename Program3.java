import java.util.*;

public class Program3 {
	// Graph and MSTs
	private HashMap<String, LinkedList<Edge>> graph = new HashMap<String, LinkedList<Edge>>();
	private HashSet<Edge> mst = new HashSet<Edge>();
	// Other variables
	private Scanner scanner = new Scanner(System.in);
	
	// RUN: Calls other methods
	public void run() {
		// Load the undirected and unweighted graph
		UndirectGraph graphrun = new UndirectGraph();
		MST mstrun = new MST();
		
		this.graph = graphrun.loadMap("actors.txt");
		this.mst = mstrun.getMST(graph);
		
		while(true) {
			
			System.out.println("Enter your choice:\r\n"
					+ "1: Print out MST Information\r\n"
					+ "2: Find Shortest Path from one Actor to another\r\n"
					+ "3: Find Longest path in MST\r\n"
					+ "4: Exit program\r\n");
			String response = this.scanner.nextLine().trim();
			if(response.equals("1")) {
				// Builds a MST
				mstrun.mstInfo();
			}else if(response.equals("2")){
				// Prints out the shortest path
				graphrun.shortestPath();
			}else if(response.equals("3")){
				// Prints out the longest path
				mstrun.longestPath();
			}else if(response.equals("4")){
				// Ends the while loop
				System.out.println("Okay. Ending program.");
				this.scanner.close();
				break;
			}else{
				System.out.println("Invalid response. Please input a single number.\n");
			}
			
		}
	}
}

public class Edge {
	private String V1;
	private String V2;
	private String movie;
	
	public Edge(String movie, String V1, String V2) {
		this.V1 = V1;
		this.V2 = V2;
		this.movie = movie;
	}
	
	public String getMovie() {
		return this.movie;
	}
	
	public String getV1(){
		return this.V1;
	}
	
	public String getV2() {
		return this.V2;
	}
	
	public String toString() {
		String output = V1 + " - " + V2 + " (" + movie + ")";
		return output;
	}
}

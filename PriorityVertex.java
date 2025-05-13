public class PriorityVertex implements Comparable<PriorityVertex> {
    private String vertex;
    private double distanceFromSource;

    public PriorityVertex(String v, double d) {
        this.vertex = v;
        this.distanceFromSource = d;
    }

    public String getVertex() {
        return this.vertex;
    }

    @Override
    public int compareTo(PriorityVertex other) {
        return Double.compare(this.distanceFromSource, other.distanceFromSource);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PriorityVertex)) return false;
        PriorityVertex pv = (PriorityVertex) other;
        return this.vertex.equals(pv.vertex);
    }

    @Override
    public int hashCode() {
        return vertex.hashCode();
    }
}
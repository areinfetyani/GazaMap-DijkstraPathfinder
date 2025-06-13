
public class Edge {
	double cost;
	Vertex v1;
	Vertex v2;

	public Edge(double cost, Vertex v1, Vertex v2) {
		super();
		this.cost = cost;
		this.v1 = v1;
		this.v2 = v2;
	}
	

	public Edge() {
		super();
	}


	public void setCost(Vertex v1, Vertex v2) {
		final int EARTH_RADIUS = 6371; // in km
		double lat1Rad = Math.toRadians(v1.lat);
		double lat2Rad = Math.toRadians(v2.lat);
		double deltaLat = Math.toRadians(v2.lat - v1.lat);
		double deltaLon = Math.toRadians(v2.lng - v1.lng);

		double dis = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
		        + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(dis), Math.sqrt(1 - dis));
		this.cost = EARTH_RADIUS * c;
	}

	@Override
	public String toString() {
		return "Edge [cost=" + cost + ", v1=" + v1 + ", v2=" + v2 + "]";
	}
	

}

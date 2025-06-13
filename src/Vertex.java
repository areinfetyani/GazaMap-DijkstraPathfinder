import java.util.LinkedList;

public class Vertex {
	
	LinkedList<Edge> e;

	String city;
	double lat; // latitude
	double lng; // longitude
	double x;
	double y;
	boolean visited;
	Vertex prev;
	double distance=Double.MAX_VALUE;
	
	
	public Vertex(String city, double lat, double lng) {
		super();
		this.city = city;
		this.lat = lat;
		this.lng = lng;
		e=new LinkedList<>();
	}


	@Override
	public String toString() {
		return "Vertex [ city=" + city + ", lat=" + lat + ", lng=" + lng + ", x=" + x + ", y=" + y
		        + ", distance=" + distance + ", visited= "+visited+"]";
	}

//	@Override
//	public String toString() {
//		return city + "," + lat + "," + lng ;
//	}

	
}

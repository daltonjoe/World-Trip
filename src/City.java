public class City {
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	private String name;
	private String country;
	private double population;
	private double x;
	private double y;
	
	public City(String name, double latitude, double longitude,String country,double population) {
		this.name=name;
		this.country=country;
		this.x=latitude;
		this.y=longitude;
		this.population=population;
		
	}
	public double calculateDistance(City c) {
		return Math.sqrt( (Math.pow(c.x-x, 2))+(Math.pow(c.y-y, 2)));
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Talha Güzel - ID Number: 041802046 Comp110 Object-Oriented
 *         Programming Assignment #2
 * @since 05.04.2020
 * 
 *        In this assignment, calculates the total distance of the trip by
 *        visiting the cities determined.It's printed to console distances
 *        between cities and total distance.
 */
public class WorldTrip {
	
	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "trip03.txt";
		try {
			File file = new File(fileName);
			if (!file.exists())
				throw new FileNotFoundException();
			ArrayList<String> trips = readTrips(file);
			String City = "city.txt";
			File file2 = new File(City);
			ArrayList<City> city = readCity(file2);
			check(city, trips);
			ArrayList<City> trip = StringtoCity(city, trips);
			printTrip(trip);
		} catch (FileNotFoundException e) {
			System.out.printf("Input file (%s) can not be found", fileName);
		}
	}
	public static void check(ArrayList<City> cities, ArrayList<String> tripnames) {
		String[] citynames = new String[cities.size()];
		for (int i = 0; i < cities.size(); i++) {
			citynames[i] = cities.get(i).getName();
		}
		for (int i = 0; i < tripnames.size(); i++) {
			if (searchLoop(citynames, tripnames.get(i))) {
			} else {
				System.out.println(tripnames.get(i) + " not found");
			}
		}
	}
	public static ArrayList<City> StringtoCity(ArrayList<City> cities, ArrayList<String> tripnames) {
		ArrayList<City> tripArray = new ArrayList<>();
		for (int i = 0; i < tripnames.size(); i++) {
			for (int k = 0; k < cities.size(); k++) {
				if (tripnames.get(i).equals(cities.get(k).getName())) {
					City a = cities.get(k);
					tripArray.add(a);
				}
			}
		}
		return tripArray;
	}
	public static boolean searchLoop(String[] strings, String searchString) {
		for (String string : strings) {
			if (string.equals(searchString))
				return true;
		}
		return false;
	}
	public static ArrayList<City> readCity(File file) throws FileNotFoundException {
		ArrayList<City> Cities = new ArrayList<>();
		Scanner sc = new Scanner(file);
		sc.nextLine();
		while (sc.hasNextLine()) {
			String[] parts = sc.nextLine().split(";");
			String City = parts[0];
			double Latitude = Double.parseDouble(parts[1]);
			double Longitude = Double.parseDouble(parts[2]);
			String Country = parts[3];
			double Population = Double.parseDouble(parts[4]);
			City city = new City(City, Latitude, Longitude, Country, Population);
			Cities.add(city);
		}
		return Cities;
	}
	public static ArrayList<String> readTrips(File file) throws FileNotFoundException {
		ArrayList<String> trips = new ArrayList<>();
		Scanner sc = new Scanner(file);
		while (sc.hasNextLine()) {
			trips.add(sc.nextLine());
		}
		return trips;
	}
	public static void printTrip(ArrayList<City> tripArray) {
		double totalDistance = 0;
		System.out.println("Trip details: ");
		for (int i = 0; i < tripArray.size() - 1; i++) {
			double distance = tripArray.get(i).calculateDistance(tripArray.get(i + 1));
			System.out.printf("%-10s -> %-10s [%8.2f km]\n", tripArray.get(i).getName(), tripArray.get(i + 1).getName(),
					distance);
			totalDistance = totalDistance + distance;
		}
		System.out.printf("\nTotal trip distance: %8.3f km", totalDistance);
	}
}
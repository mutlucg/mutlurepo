
import java.util.*;

public class World {
	public int days = 1;
	public Country[][] countries;
	int numberOfCountries;
	int population;
	int healthyPopulation;
	int sickPopulation;
	int deadPopulation;
	int infectedPopulation;
	int currentDay;
	ArrayList<Person> people;

	public World(int P, int N, int X) {
		population = P;
		countries = new Country[N][N];
		numberOfCountries = N * N;
		infectedPopulation = (P * X) / 100;
		healthyPopulation = population - infectedPopulation;

	}

	public void createWorld() {
		createPeople();
		createCountries();
		setCountryPopulations(population, numberOfCountries);
		setNeighbours();

	}

	public void createPeople() {
		people = new ArrayList<Person>();
		for (int i = 0; i < population; i++) {
			people.add(new Person());
		}
	}

	public void createCountries() {
		for (int i = 0; i < countries.length; i++) {
			for (int j = 0; j < countries.length; j++) {
				countries[i][j] = new Country();
			}
		}
	}

	public void setCountryPopulations(int pop, int numOfCountries) {
		Random r = new Random();
		int countryPop = 0;
		while (pop > 0) {
			for (int i = 0; i < countries.length; i++) {
				for (int j = 0; j < countries.length; j++) {
					if (pop / numOfCountries <= 0) {
						countries[i][j].population += pop;
						break;
					}
					countryPop = r.nextInt(pop / numOfCountries) + 1;
					countries[i][j].population += countryPop;
					pop -= countryPop;
					numOfCountries--;
				}
			}
			numOfCountries = numberOfCountries;
		}
	}

	private void setNeighbours() {
		setCornersNeighbors();
		setEdgeNeighbors();
		setMiddlesNeighbors();
	}

	public void setCornersNeighbours() {

		for (int i = 0; i < countries.length; i++) {
			for (int j = 0; j < countries.length; j++) {
				if (i == 0 && j == 0) {
					countries[i][j].southN = countries[i + 1][j];
					countries[i][j].northN = countries[countries.length - 1][j];
					countries[i][j].westN = countries[i][countries.length - 1];
					countries[i][j].eastN = countries[i][j + 1];
				} else if (i == 0 && j == countries.length - 1) {
					countries[i][j].southN = countries[i + 1][j];
					countries[i][j].northN = countries[countries.length - 1][j];
					countries[i][j].westN = countries[i][j - 1];
					countries[i][j].eastN = countries[i][0];
				} else if (i == countries.length - 1 && j == 0) {
					countries[i][j].southN = countries[0][j];
					countries[i][j].northN = countries[i - 1][j];
					countries[i][j].westN = countries[i][countries.length - 1];
					countries[i][j].eastN = countries[i][j + 1];
				} else if (i == countries.length - 1 && j == countries.length - 1) {
					countries[i][j].southN = countries[0][j];
					countries[i][j].northN = countries[i - 1][j];
					countries[i][j].westN = countries[i][j - 1];
					countries[i][j].eastN = countries[i][0];

				}
			}
		}
	}

	public void setEdgeNeighbours() {

		for (int i = 0; i < countries.length; i++) {
			for (int j = 0; j < countries.length; j++) {
				if (i == 0 && j != 0 && j != countries.length - 1) {
					countries[i][j].southN = countries[i + 1][j];
					countries[i][j].northN = countries[countries.length - 1][j];
					countries[i][j].westN = countries[i][j - 1];
					countries[i][j].eastN = countries[i][j + 1];

				} else if (i == countries.length - 1 && j != 0 && j != countries.length - 1) {
					countries[i][j].southN = countries[0][j];
					countries[i][j].northN = countries[i - 1][j];
					countries[i][j].westN = countries[i][j - 1];
					countries[i][j].eastN = countries[i][j + 1];

				} else if (j == 0 && i != 0 && i != countries.length - 1) {
					countries[i][j].southN = countries[i + 1][j];
					countries[i][j].northN = countries[i - 1][j];
					countries[i][j].westN = countries[i][countries.length - 1];
					countries[i][j].eastN = countries[i][j + 1];

				} else if (j == countries.length - 1 && i != 0 && i != countries.length - 1) {
					countries[i][j].southN = countries[i + 1][j];
					countries[i][j].northN = countries[i - 1][j];
					countries[i][j].westN = countries[i][j - 1];
					countries[i][j].eastN = countries[i][0];

				}
			}
		}
	}

	public void setMiddlesNeighbours() {
		for (int i = 0; i < countries.length; i++) {
			for (int j = 0; j < countries.length; j++) {
				if (j != 0 && j != countries.length - 1 && i != 0 && i != countries.length - 1)
					countries[i][j].southN = countries[i + 1][j];
				countries[i][j].northN = countries[i - 1][j];
				countries[i][j].westN = countries[i][j - 1];
				countries[i][j].eastN = countries[i][j + 1];
			}
		}
	}

}

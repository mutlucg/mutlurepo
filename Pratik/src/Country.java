package example;

public class Country {
	Country westN;
	Country eastN;
	Country northN;
	Country southN;
	
	int population;
	int healthyPopulation;
	int sickPopulation;
	int deadPopulation;
	int infectedPopulation;

	public Country(int p) {
		population = p;

	}

	boolean isInfected(){
		if(infectedPopulation>0 ||deadPopulation>0 || sickPopulation>0)
			return true;

		return false;
	}

	public int getPopulation() {
		// TODO Auto-generated method stub
		return population;
	}

	public void setPopulation(int population) {
		this.population=population;		
	}
}
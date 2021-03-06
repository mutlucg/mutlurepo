
import java.util.ArrayList;
import java.util.Random;

public class Person {

	private Country location;
	private Radio radio;
	private int infectionDay;
	private boolean isInfected;
	private  boolean isSick;
	private boolean isDead;
	private boolean isImmune;
	private boolean looksInfectious;
	private int currentDay;
	private int transportation;
	private int travelDay;
	private boolean isSuper;

	public Person() {
		radio = new Radio();
	}

	public void survive(){
		currentDay=location.currentDay;
		travel();
		chooseADayToMove();
		getInfected();
		getSick();
		die();
		getImmune();
		getHealty();
	}

	public void travel(){
		if (currentDay== travelDay){
			transportation=chooseTransportation();
			if (transportation == 1) {
				chooseDestinationForFlight();
			} else chooseDestinationForNonFlight();
		}
	}

	public int chooseTransportation() {
		Random r = new Random();
		int rand = r.nextInt(100);
		if (rand < radio.transportationParam) {
			transportation = 1;
		} else {
			transportation = 0;
		}
		return transportation;
	}

	private void chooseDestinationForFlight() {
		Country oldLocation=location;
		ArrayList<Country> destinationCountries = new ArrayList<>();
		for (Country c : radio.nonInfectedCountries) {
			destinationCountries.add(c);
		}
		Random r = new Random();
		int option = r.nextInt(destinationCountries.size());
		location = destinationCountries.get(option);
		moveFromACountry(oldLocation);
		moveToACountry(location);
	}

	private void chooseDestinationForNonFlight() {
		ArrayList<Country> destinationCountries = new ArrayList<>();
		for (Country c : radio.nonInfectedCountries) {   ///for icine girmiyor
			for (int i = 0; i < location.neighbours.size(); i++) {
				if (c == location.neighbours.get(i)) {
					destinationCountries.add(c);
				}
			}
			Country oldLocation = location;
			Random r = new Random();
			if(destinationCountries.size()>0){
				int option = r.nextInt(destinationCountries.size());
				location = destinationCountries.get(option);
				moveFromACountry(oldLocation);
				moveToACountry(location);
			}
		}
	}

	private void moveFromACountry(Country oldLocation) {
		oldLocation.person.remove(this);
		oldLocation.setPopulation(oldLocation.getPopulation()-1);
		if (isInfected) oldLocation.setInfectedPopulation(oldLocation.getInfectedPopulation()-1);
		else if (isSick) oldLocation.setSickPopulation(oldLocation.getSickPopulation()-1);
		else oldLocation.setHealthyPopulation(oldLocation.getHealthyPopulation()-1);
	}

	private void moveToACountry(Country location) {
		location.person.add(this);
		location.setPopulation(location.getPopulation()+1);
		if (isInfected) location.setInfectedPopulation(location.getInfectedPopulation()+1);
		else if (isSick) location.setSickPopulation(location.getSickPopulation()+1);
		else location.setHealthyPopulation(location.getHealthyPopulation()+1);
	}

	public void chooseADayToMove(){
		if (currentDay==1||currentDay==travelDay){
			Random r=new Random();
			travelDay=currentDay + (r.nextInt(5)+1);
		}
	}

	public void getInfected() {
		if(!isSuper && !isInfected && infectionDay!=0 && !isImmune && travelDay==currentDay){
			Random r = new Random();
			int infect = r.nextInt(100);
			if ( infect < 40){
				this.isInfected = true;
				location.setInfectedPopulation(location.getInfectedPopulation()+1);
				location.setHealthyPopulation(location.getHealthyPopulation()-1);
			}
			else
				this.isInfected = false;
		}
	}

	public void getSick(){
		if (isInfected && currentDay-infectionDay==6){
			isSick=true;
			looksInfectious= true;
			location.setSickPopulation(location.getSickPopulation()+1);
		}
	}

	public void die() {
		if(isSick && currentDay-infectionDay==14){
			Random r = new Random();
			int infect = r.nextInt(100);
			if (infect < 25){
				isDead=true;
				location.setSickPopulation(location.getSickPopulation()-1);
				location.setdeadPopulation(location.getdeadPopulation()+1);
				location.setPopulation(location.getPopulation()-1);
			}
		}
	}

	public void getImmune(){
		if (isSick && !isDead && currentDay-infectionDay==16){
			isImmune=true;
			isSick=false;
			looksInfectious= false;
			location.setSickPopulation(location.getSickPopulation()-1);
		}
	}

	public void getHealty() {
		if (isImmune && currentDay - infectionDay == 18) {
			isImmune = false;
			isInfected = false;
			location.setHealthyPopulation(location.getHealthyPopulation() + 1);
			location.setInfectedPopulation(location.getInfectedPopulation() - 1);
		}
	}

	public boolean isInfected() {
		return isInfected;
	}

	public void setInfectionDay(int infectionDay) {
		this.infectionDay=infectionDay;	
	}

	public void setSuper(boolean isSuper) {
		this.isSuper=isSuper;		
	}

	public boolean isSuper() {
		return isSuper;
	}

	public void setInfected(boolean isInfected) {
		this.isInfected=isInfected;		
	}

	public void setLocation(Country location) {
		this.location=location;
		
	}


}
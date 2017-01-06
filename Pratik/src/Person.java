package example;

import java.util.ArrayList;
import java.util.Random;

public class Person {


	Country position;
	int infectionDay;
	boolean isInfected;
	boolean isSick;
	boolean isDead;
	boolean isImmune;
	boolean looksInfectious;
	int chosenDay;
	World world;

	public Person(World world){

		this.world=world;
	}

	public void getInfected() {

		if (world.getCurrentDay()==chosenDay){
			Random r = new Random();
			int infect = r.nextInt(100);
			if ( infect < 40){
				this.isInfected = true;
				infectionDay=world.getCurrentDay();
				world.setHealthyPopulation(world.getHealthyPopulation()-1);
				world.setInfectedPopulation(world.getInfectedPopulation()+1);

			}
			else
				this.isInfected = false;
		}

	}

	public boolean getSick(){
		if (world.getCurrentDay()-infectionDay==6){

			isSick=true;
			looksInfectious= true;

			world.setInfectedPopulation(world.getInfectedPopulation()-1);
			world.setSickPopulation(world.getSickPopulation()+1);

		}
		return isSick;
	}

	public void die() {

		if(world.getCurrentDay()-infectionDay==14){

			Random r = new Random();
			int infect = r.nextInt(100);
			if (infect < 25){
				isDead=true;
				world.setSickPopulation(world.getSickPopulation()-1);
				world.setPopulation(world.getPopulation()-1);
				world.setDeadPopulation(world.getDeadPopulation()+1);



			}
		}

	}

	public void getImmune(){

		if (world.getCurrentDay()-infectionDay==16){
			isImmune=true;
			isSick=false;
			looksInfectious= false;
			world.setSickPopulation(world.getSickPopulation()-1);

		}

	}


	public void getHealty(){

		if (world.getCurrentDay()-infectionDay==18){
			isImmune=false;
			isInfected=false;
			world.setHealthyPopulation(world.getHealthyPopulation()+1);
			//	world.setInfectedPopulation(world.getInfectedPopulation()-1);
		}

	}

	public void survive(){

		if(!isInfected && !isImmune)	getInfected();
		if(isInfected) getSick();
		if (isSick) {
			die();
			if(!isDead)getImmune();
		}
		if (!isDead && isImmune)getHealty();
		chooseADayToMove();
	}

	public void chooseADayToMove(){
		if (world.getCurrentDay()==chosenDay){
			Random r=new Random();
			chosenDay=world.getCurrentDay()+r.nextInt(6);
			chooseDestination();
		}
	}

	public void chooseDestination(){
		ArrayList<Country> destinationCountries= new ArrayList<Country>();
		
		if(!position.eastN.isInfected()) destinationCountries.add(position.eastN); 
		if(!position.northN.isInfected()) destinationCountries.add(position.northN); 
		if(!position.westN.isInfected())  destinationCountries.add(position.westN); 
		if(!position.southN.isInfected()) destinationCountries.add(position.southN); 

		Random r=new Random();
		int option= r.nextInt(destinationCountries.size());
		position= destinationCountries.get(option);
	}

	public void setPosition(Country country) {
		position=country;

	}
}
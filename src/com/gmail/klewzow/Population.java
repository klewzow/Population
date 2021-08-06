package com.gmail.klewzow;

import java.io.Serializable;
import java.util.Arrays;

public class Population implements Serializable {

	private static final long serialVersionUID = 1L;

	private Human[] human = new Human[0];

	public Population() {
		super();
	}

	public void addHumanToPopulation(Human h) {
		this.human = Arrays.copyOf(this.human, (this.human.length + 1));
		this.human[this.human.length - 1] = h;
		 
	}

	public Human[] getHuman() {
		return human;
	}

	public void setHuman(Human[] human) {
		this.human = human;
	}

	@Override
	public String toString() {
		return "Population [  Human = " + Arrays.toString(human) + "]";
	}

}

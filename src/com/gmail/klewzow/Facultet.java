package com.gmail.klewzow;

import java.io.Serializable;
import java.util.Arrays;

public class Facultet implements Serializable {

	private static final long serialVersionUID = 1L;
	private GroupStudent[] groups;
	private String facultet;

	public Facultet(String facultet) {
		super();
		this.groups = new GroupStudent[0];
		this.facultet = facultet;

	}

	public Facultet() {
		super();
	}

	public void addGrupsToFacultet(GroupStudent gr) {
		this.groups = Arrays.copyOf(this.groups, this.groups.length + 1);
		this.groups[this.groups.length - 1] = gr;
		gr.setFacultet(this.getFacultet());
	}

	public String getFacultet() {
		return facultet;
	}

	public GroupStudent[] getGroups() {
		return groups;
	}

	@Override
	public String toString() {
		return "Facultet " + facultet + " [ groups = " + groups.length + " ]\n";
	}

}

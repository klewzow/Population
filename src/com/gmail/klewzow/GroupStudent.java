package com.gmail.klewzow;

import java.io.Serializable;
import java.util.Arrays;

public class GroupStudent implements Serializable {

	private static final long serialVersionUID = 1L;
	private Student[] group;
	private int numberGroup;
	private String facultet;
	private int course;

	public GroupStudent(int numberGroup, String facultet, int course) {
		super();
		this.group = new Student[0];
		this.numberGroup = numberGroup;
		this.facultet = facultet;
		this.course = course;
	}

	public GroupStudent() {
		super();
	}

	public void addStudentInGroup(Student student) {

		this.group = Arrays.copyOf(this.group, this.group.length + 1);
		this.group[this.group.length - 1] = student;
	}

	public void delStudentInGroup(Student student) {

		for (int i = 0; i < this.group.length; i += 1) {
			if (this.group[i].equals(student)) {
				this.group[i].setStudent(false);
				this.group[i] = null;
			}
		}
		Arrays.sort(this.group, (a, b) -> SortInterface.sortInterface(a, b));
		this.group = Arrays.copyOf(this.group, this.group.length - 1);
	}

	public void sortGrupById() {

		Arrays.sort(this.group,
				((a, b) -> (SortInterface.sortInterface(a, b) != SortInterface.NOT_NULL
						? SortInterface.sortInterface(a, b)
						: a.getHuman().getId() - b.getHuman().getId())));

	}

	public Student[] getGroup() {
		return group;
	}

	public void setGroup(Student[] group) {
		this.group = group;
	}

	public int getNumberGroup() {
		return numberGroup;
	}

	public void setNumberGroup(int numberGroup) {
		this.numberGroup = numberGroup;
	}

	public String getFacultet() {
		return facultet;
	}

	public void setFacultet(String facultet) {
		this.facultet = facultet;
	}

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "GroupStudent [     numberGroup=" + numberGroup + ", facultet=" + facultet + ", course=" + course + "]";
	}

}

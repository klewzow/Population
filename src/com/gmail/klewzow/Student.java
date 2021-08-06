package com.gmail.klewzow;

import java.io.Serializable;

public class Student extends Human implements Serializable {

	private static final long serialVersionUID = 1L;
	private Human human;
	private boolean student;

	public Student() {
		super();
	}

	public Student(Human human) {
		super(human.getName(), human.getSurname(), human.getAge(), human.getPhone());
		this.human = human;
		this.student = true;
	}

 

	public Human getStudent() {
		return human;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public boolean isStudent() {
		return student;
	}
	@Override
	public String getName() {
		return getStudent().getName();
	}
	@Override
	public String getSurname() {
		return getStudent().getSurname();
	}
	@Override
	public int getAge() {
		return getStudent().getAge();
	}
	public void setStudent(boolean student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Student [human=" + human + ", student=" + student + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((human == null) ? 0 : human.hashCode());
		result = prime * result + (student ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (human == null) {
			if (other.human != null)
				return false;
		} else if (!human.equals(other.human))
			return false;
		if (student != other.student)
			return false;
		return true;
	}

}

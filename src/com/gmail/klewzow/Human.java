package com.gmail.klewzow;

import java.io.Serializable;

public class Human extends Population implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private int age;
	private int id;
	private String phone;

	public Human(String name, String surname, int age, String phone) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.phone = phone;
		this.setId();
	}

	public Human() {
		super();
		this.setId();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	private void setId() {
		this.id = (hashCode() % 1000) < 0 ? (hashCode() % 1000) * -1 : (hashCode() % 1000);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Human [name=\t" + name + "\t\t, surname=" + surname + "\t\t, age=" + age + ", id= " + id + ", phone=" + phone
				+ "] \n ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Human other = (Human) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

}

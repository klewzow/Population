package com.gmail.klewzow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Booster {
	private Facultet[] fc;
	Population people = new Population();
	RestoreFile<?> rf = (file) -> {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	};

	public Booster() {
		super();
		this.people = (Population) rf.restoreFile("Population");
		this.fc = this.allFacultet();
		this.saveData(this.fc);

	}

	public void saveData(Facultet[] fc) {
		for (Facultet f : fc) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f.getFacultet() + ".fac"))) {
				oos.writeObject(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public <T> T[] addsToArray(T arr[], String fileName) {
		T tmp[] = arr;
		tmp = Arrays.copyOf(tmp, tmp.length + 1);
		tmp[tmp.length - 1] = (T) rf.restoreFile(fileName);
		return (T[]) tmp;
	}

	public void allPiple() {
		for (Human h : people.getHuman()) {
			System.out.println(h.getName() + " " + h.getSurname() + " " + h.getAge());
		}
	}

	public void allStudent() {
		for (Facultet f : this.fc) {
			System.out.println("  facultet - " + f.getFacultet());
			System.out.println();
			for (GroupStudent gs : f.getGroups()) {
				System.out.println("Curse - " + gs.getCourse() + "Student - " + gs.getGroup().length);
				for (Student st : gs.getGroup()) {
					System.out.println("Name - " + st.getHuman().getName() + "  " + st.getHuman().getSurname()
							+ " Age - " + st.getHuman().getAge() + " Student - " + st.isStudent());
				}
			}
			System.out.println();
		}
	}

	public Student searchStudent(String str, int w) {
		Student std = null;
		for (Facultet f : this.fc) {
			for (GroupStudent gs : f.getGroups()) {
				for (Student st : gs.getGroup()) {
					if (std != null)
						continue;
					switch (w) {
					case 0: {
						std = this.search(str, st, gs);
						break;
					}
					case 1: {
						std = this.delStudent(st, str, gs);

						break;
					}

					default:
						throw new IllegalArgumentException("Unexpected value: " + w);
					}
				}
			}
		}
		if (std == null) {
			System.out.println("None");
		}
		return std;
	}

	private Student search(String str, Student st, GroupStudent gs) {
		if ((st.getHuman().getSurname()).equals(str)) {

			System.out.println("Name - " + st.getHuman().getName() + "  " + st.getHuman().getSurname() + " Age - "
					+ st.getHuman().getAge() + "  Group number - " + gs.getNumberGroup() + "  Facultet - "
					+ gs.getFacultet());
			return st;
		}
		return null;
	}

	private Student delStudent(Student st, String str, GroupStudent gs) {
		if (str.equals(st.getHuman().getSurname())) {
			System.out.println(st.getHuman().getSurname());
			gs.delStudentInGroup(st);
			return st;
		}
		return null;

	}

	public Student addStudent() {
		boolean save = true;
		Scanner sc = new Scanner(System.in);
		int inc = 0;
		Student newStudent = null;
		Human tmp = scanHuman(sc, inc);
		people.addHumanToPopulation(tmp);
		System.out.println("Add to students? Y\\N ?");
		String r = sc.nextLine().toUpperCase();
		if (r.equals("Y")) {
			newStudent = createStudent(tmp, sc);
			save = false;
			System.out.println("Student - " + newStudent.getHuman().getName() + " " + newStudent.getHuman().getSurname()
					+ " added");
		} else {
			System.out.println("Human - " + tmp + " create");
			save = false;
		}

		return newStudent;
	}

	private Student createStudent(Human tmp, Scanner sc) {

		Student std = new Student(tmp);
		int tmpInt = 0;
		while (true) {
			int i = 0;
			for (Facultet f : this.fc) {
				System.out.println("Select facultet : " + f.getFacultet() + " : " + (i + 1));
				i += 1;
			}
			try {
				tmpInt = Integer.parseInt(sc.nextLine());

				if (tmpInt < 1 || tmpInt > this.fc.length)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				continue;
			}
			for (GroupStudent gr : this.fc[tmpInt - 1].getGroups()) {
				gr.addStudentInGroup(std);
			}
			return std;
		}
	}

	private Human scanHuman(Scanner sc, int inc) {
		System.out.println("input name");
		String name = sc.nextLine();
		System.out.println("input surname");
		String surName = sc.nextLine();
		System.out.println("input age");
		String age = "";
		int ag = 0;
		while (true) {
			age = sc.nextLine();
			try {
				ag = Integer.parseInt(age);
				if (ag <= 15 && ag > 70)
					throw new NumberFormatException();
			} catch (NumberFormatException e) {
				System.out.println("Incorect age \n input age > 15");
				continue;
			}
			break;
		}
		return new Human(name, surName, ag, setPhone());
	}

	String setPhone() {

		return ((Integer.toString((int) ((Math.random() * (999 - 100) + 100)))) + "-"
				+ (Integer.toString((int) ((Math.random() * (99 - 10) + 10)))) + "-"
				+ (Integer.toString((int) ((Math.random() * (99 - 10) + 10)))));
	}

	public Facultet[] allFacultet() {
		Facultet[] tmp = new Facultet[0];
		for (File f : new File(".").listFiles()) {
			if (f.getName().lastIndexOf(".") < 0) {
				continue;
			}
			if (f.getName().substring((f.getName().lastIndexOf(".") + 1), f.getName().length()).equals("fac")) {
				tmp = (this.addsToArray(tmp, (f.getName())));
			}
		}
		return tmp;
	}

	public Facultet[] getFc() {
		return fc;
	}

}

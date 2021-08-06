package com.gmail.klewzow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleMy implements Runnable {
	private Thread tr;
	private Booster booster;

	public ConsoleMy() {
		super();
		this.booster = new Booster();
		this.tr = new Thread(this);
		tr.start();
	}

	private void consoller() throws IOException {
		System.out.println("Input command :");
		Scanner sc = new Scanner(System.in);
		try {
			String str = sc.nextLine();
			if ((str.split(" ").length) > 1 && (str.split(" ").length) <= 2) {
				handlerDouble(str.split(" "));
			} else if ((str.split(" ").length) == 1) {
				handler(str);
			} else {
				System.err.println("Error command");
			}
		} catch (NoSuchElementException e) {
			sc.close();
			this.tr.interrupt();
		}
	}

	private void handler(String str) {
		if (str.equals("exit")) {
			this.tr.interrupt();
		} else if (str.equals("?")) {
			System.out.println(reader("help.txt"));
		} else if (str.equals("all")) {
			booster.allPiple();
		} else if (str.equals("facultet")) {
			System.out.println(Arrays.toString(booster.getFc()));
		} else if (str.equals("student")) {
			booster.allStudent();
		} else if (str.equals("add")) {
			booster.addStudent();
			booster.saveData(booster.getFc());
		}
	}

	private void handlerDouble(String[] str) {
		if (str[0].equals("search")) {
			booster.searchStudent(str[1], 0);
		} else if (str[0].equals("del")) {
			booster.searchStudent(str[1], 1);
			booster.saveData(booster.getFc());
		}
	}

	private String reader(String str) {
		String resStr = "";
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(str)))) {
			while ((resStr = br.readLine()) != null) {
				sb.append(resStr + System.lineSeparator());
			}
		} catch (IOException e) {
			System.out.println(str + "  not found");
		}
		return sb.toString();
	}

	public void getTr() {
		try {
			this.tr.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("input ? for help");
		while (!this.tr.isInterrupted()) {
			try {
				consoller();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

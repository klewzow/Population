package com.gmail.klewzow;

@FunctionalInterface
public interface RestoreFile<T> {
	public  T restoreFile(String name);
}
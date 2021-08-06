package com.gmail.klewzow;

public interface SortInterface {
	public static final int NOT_NULL = 10;

	public static int sortInterface(Object a, Object b) {

		if (a == null && b == null) {
			return 0;
		}
		if (a != null && b == null) {
			return  -1;
		}
		if (a == null && b != null) {
			return  1;
		}
		return NOT_NULL;
	}
}

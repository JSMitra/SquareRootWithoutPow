package com.practice;

public class Utility {

	/**
	 * computes n^p
	 * @param n
	 * @param p
	 * @returns n^p
	 */
	public static long power(long n, long p) {
		long i = p;
		long result = 1L;
		while(i>0) {
			result *= n;
			i--;
		}
		return result;
	}
}

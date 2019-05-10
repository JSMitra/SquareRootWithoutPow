package com.practice;

import java.math.BigInteger;

public class SquareRootWithoutPow {

	static public int Compare(BigInteger b1, BigInteger b2) {
		return b1.compareTo(b2);
	}

	public static long getSquareRoot(long n) {
		long start = 1;
		long end = n;
		long select = (start + end) / 2;
		long iterations = 0;
		BigInteger bigIntN = new BigInteger("" + n);
		BigInteger big2 = new BigInteger("2");
		BigInteger big1 = new BigInteger("1");
		while (true) {
			BigInteger bigIntSelected = new BigInteger("" + select);
			BigInteger bigIntTwiceSelected = bigIntSelected.multiply(big2);
			BigInteger bigIntSquareOfSelected = bigIntSelected.multiply(bigIntSelected);

			int compareSquareSelectedWithN = Compare(bigIntSquareOfSelected, bigIntN);
			if (compareSquareSelectedWithN > 0) {
				BigInteger bigIntSquareSelectedMinus1 = bigIntSquareOfSelected.subtract(bigIntTwiceSelected).add(big1);

				int compareSquareSelectMinus1WithN = Compare(bigIntSquareSelectedMinus1, bigIntN);
				if (compareSquareSelectMinus1WithN > 0) {
					end = select;
					select = (start + end) / 2;
				} else if (compareSquareSelectMinus1WithN < 0) {
					break;
				} else {
					select = select - 1;
					break;
				}
			} else if (compareSquareSelectedWithN < n) {
				BigInteger bigIntSquareSelectedPlus1 = bigIntSquareOfSelected.add(bigIntTwiceSelected).add(big1);

				int compareSquareSelectPlus1WithN = Compare(bigIntSquareSelectedPlus1, bigIntN);
				if (compareSquareSelectPlus1WithN > 0) {
					select = select + 1;
					break;
				} else if (compareSquareSelectPlus1WithN < 0) {
					start = select;
					select = (start + end) / 2;
				} else {
					break;
				}
			} else {
				break;
			}
			iterations++;
		}
		// comment it if you don't want to print the number of iterations
		System.out.println(iterations);
		return select;
	}

	public static void main(String[] args) {

		long rootOfN = getSquareRoot(4238764530878L);

		System.out.println("Square root=" + rootOfN);
	}

}

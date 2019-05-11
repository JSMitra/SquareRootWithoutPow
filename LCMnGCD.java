package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A class meant to get LCM & GCD of a given list of numbers through the public
 * static getLCM & getGCD methods
 * 
 * @author JSMitra
 *
 */
public class LCMnGCD {

	public static long getGCD(long n1, long n2) {
		if (n1 == n2) {
			return n1;
		}

		if (n2 > n1) {
			long temp = n1;
			n1 = n2;
			n2 = temp;
		}
		if (n1 % n2 == 0) {
			return n2;
		}
		Map<Long, Long> primeFactorsOfN1 = PrimeNumberHelper.getPrimeFactorsWithPowers(n1);
		Map<Long, Long> primeFactorsOfN2 = PrimeNumberHelper.getPrimeFactorsWithPowers(n2);

		long gcd = 0;
		Map<Long, Long> gcdFactorsForm = new HashMap<>();

		Iterator<Long> n1Iter = primeFactorsOfN1.keySet().iterator();
		while (n1Iter.hasNext()) {
			long primeFactorOfN1 = n1Iter.next();
			long powerOfPrimeFactorOfN1 = primeFactorsOfN1.get(primeFactorOfN1);
			long powerOfPrimeFactorOfN2 = 0L;
			if (primeFactorsOfN2.containsKey(primeFactorOfN1)) {
				powerOfPrimeFactorOfN2 = primeFactorsOfN2.get(primeFactorOfN1);

				if (powerOfPrimeFactorOfN1 <= powerOfPrimeFactorOfN2) {
					gcdFactorsForm.put(primeFactorOfN1, powerOfPrimeFactorOfN1);
				} else {
					gcdFactorsForm.put(primeFactorOfN1, powerOfPrimeFactorOfN2);
				}
			}

		}

		gcd = PrimeNumberHelper.getNumberFromPrimeFactorPowers(gcdFactorsForm);
		return gcd;
	}

	public static long getLCM(long n1, long n2) {
		if (n1 == n2) {
			return n1;
		}

		if (n2 > n1) {
			long temp = n1;
			n1 = n2;
			n2 = temp;
		}
		if (n1 % n2 == 0) {
			return n1;
		}
		Map<Long, Long> primeFactorsOfN1 = PrimeNumberHelper.getPrimeFactorsWithPowers(n1);
		Map<Long, Long> primeFactorsOfN2 = PrimeNumberHelper.getPrimeFactorsWithPowers(n2);

		long lcm = 0;
		Map<Long, Long> lcmFactorsForm = new HashMap<>();

		Iterator<Long> n1Iter = primeFactorsOfN1.keySet().iterator();
		while (n1Iter.hasNext()) {
			long primeFactorOfN1 = n1Iter.next();
			long powerOfPrimeFactorOfN1 = primeFactorsOfN1.get(primeFactorOfN1);
			long powerOfPrimeFactorOfN2 = 0L;
			if (primeFactorsOfN2.containsKey(primeFactorOfN1)) {
				powerOfPrimeFactorOfN2 = primeFactorsOfN2.get(primeFactorOfN1);
			}
			if (powerOfPrimeFactorOfN1 >= powerOfPrimeFactorOfN2) {
				lcmFactorsForm.put(primeFactorOfN1, powerOfPrimeFactorOfN1);
			} else {
				lcmFactorsForm.put(primeFactorOfN1, powerOfPrimeFactorOfN2);
			}
			primeFactorsOfN2.remove(primeFactorOfN1);
		}

		Iterator<Long> n2Iter = primeFactorsOfN2.keySet().iterator();
		while (n2Iter.hasNext()) {
			long primeFactorOfN2 = n2Iter.next();
			long powerOfPrimeFactorOfN2 = primeFactorsOfN2.get(primeFactorOfN2);
			lcmFactorsForm.put(primeFactorOfN2, powerOfPrimeFactorOfN2);
		}
		lcm = PrimeNumberHelper.getNumberFromPrimeFactorPowers(lcmFactorsForm);
		return lcm;
	}

	/**
	 * Function to return LCM of a list of numbers
	 * 
	 * @param numbers
	 * @return
	 */
	public static long getLCM(List<Long> numbers) {
		numbers.sort(null);
		int size = numbers.size();
		int i = size - 1;
		long lcm = numbers.get(i);
		i--;
		while (i >= 0) {
			lcm = getLCM(lcm, numbers.get(i));
			i--;
		}

		return lcm;
	}

	/**
	 * Function to return GCD of a list of numbers
	 * 
	 * @param numbers
	 * @return
	 */
	public static long getGCD(List<Long> numbers) {
		numbers.sort(null);
		int size = numbers.size();
		int i = size - 1;
		long gcd = numbers.get(i);
		i--;
		while (i >= 0) {
			gcd = getGCD(gcd, numbers.get(i));
			if (gcd == 1) {
				break;
			}
			i--;
		}

		return gcd;
	}

	public static void main(String[] args) {
		List<Long> numbers = new ArrayList<>();
		numbers.add(24L);
		numbers.add(36L);
		numbers.add(72L);
		numbers.add(5L);

		System.out.println(getLCM(24, 36));
		System.out.println(getLCM(numbers));

		System.out.println(getGCD(24, 36));
		System.out.println(getGCD(numbers));
	}
}

package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * PrimeNumberHelper is a class that serves the following purposes: 
 * 1)It tells if a given number is prime 
 * 2)It gives a list of prime factors of a given number upto is square root
 * 3)It gives the list of primes along with their powers to form the given number 
 * 4)It keep accumulating prime numbers as it is fed with newer inputs
 * 
 * Note: It utilizes SquareRootWithoutPow class for optimizing prime number
 * calculation
 * 
 * @author JSMitra
 *
 */
public class PrimeNumberHelper {

	private static List<Long> listOfPrimes = new ArrayList<>();

	static {
		listOfPrimes.add(2L);
		listOfPrimes.add(3L);
		listOfPrimes.add(5L);
		listOfPrimes.add(7L);
	}

	/**
	 * constructs a new list of primes from the recorded list of primes
	 * 
	 * @return
	 */
	public static List<Long> getRecordedPrimesList() {
		return new ArrayList<Long>(listOfPrimes);
	}

	private static void addPrimeNumber(long p) {
		if (!listOfPrimes.contains(p)) {
			listOfPrimes.add(p);
			listOfPrimes.sort(null);
		}
	}

	/**
	 * checks from the recorded list of primes if n is present else computes prime
	 * is brute force way. If n is discovered to be a prime, n would be added to the
	 * list of primes
	 * 
	 * @param n
	 * @return true if n is prime and false otherwise
	 */
	public static boolean isPrime(long n) {

		if (listOfPrimes.contains(n)) {
			return true;
		}

		if (n % 2 == 0) {
			return false;
		}

		long rootOfN = SquareRootWithoutPow.getSquareRoot(n);

		Iterator<Long> primeIter = listOfPrimes.iterator();
		long lastCheckedPrimeNumber = 0;
		while (primeIter.hasNext()) {
			lastCheckedPrimeNumber = primeIter.next();
			if (lastCheckedPrimeNumber > rootOfN) {
				break;
			}
			if (n % lastCheckedPrimeNumber == 0) {
				return false;
			}
		}
		for (long i = lastCheckedPrimeNumber; i <= rootOfN; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		addPrimeNumber(n);
		return true;
	}

	/**
	 * gets a list of prime factors of n upto square root of N
	 * 
	 * @param n
	 * @return list of prime factors of n upto square root of N
	 */
	public static List<Long> getPrimeFactorsUptoSquareRoot(long n) {

		List<Long> primeFactors = new ArrayList<>();
		long limit = SquareRootWithoutPow.getSquareRoot(n);
		long lastCheckedPrimeFactor = 0;
		Iterator<Long> primeIter = listOfPrimes.iterator();
		while (primeIter.hasNext()) {
			lastCheckedPrimeFactor = primeIter.next();
			if (lastCheckedPrimeFactor > limit) {
				break;
			}
			if (n % lastCheckedPrimeFactor == 0) {
				primeFactors.add(lastCheckedPrimeFactor);
			}
		}

		// this loop can be made more optimized by specifically
		// checking for numbers ending with 1,3,7,9 as the rest we
		// know are not primes
		for (long i = lastCheckedPrimeFactor + 2; i <= limit; i += 2) {

			// skip multiples of 5
			if (i % 10 == 5) {
				continue;
			}
			if (isPrime(i)) {
				if (n % i == 0) {
					primeFactors.add(i);
				}
			}

		}
		return primeFactors;

	}

	/**
	 * get prime factors of a n along with their powers for example if n = 59290L,
	 * the list will be depicted in the debugger as [{2=1}, {5=1}, {7=2}, {11=2}]
	 * which means 2^1 x 5^1 x 7^2 x 11^2
	 * 
	 * @param n
	 * @return List of Maps where each Map has a prime factor and its power.
	 *         Together sum of all the primes with their powers form the number n
	 */
	private static List<Map<Long, Long>> getPrimeFactorsWithPowers(long n) {
		List<Map<Long, Long>> primeFactorsWithPowers = new ArrayList<>();

		List<Long> primeFactors = getPrimeFactorsUptoSquareRoot(n);
		Iterator<Long> primeIter = primeFactors.iterator();
		long n2 = n;
		while (primeIter.hasNext()) {
			long primeFactor = primeIter.next();
			long power = 0;
			while (n2 % primeFactor == 0) {
				power++;
				n2 = n2 / primeFactor;
			}
			Map<Long, Long> powers = new HashMap<>();
			powers.put(primeFactor, power);
			primeFactorsWithPowers.add(powers);

		}
		if (n2 > 0) {
			Map<Long, Long> powers = new HashMap<>();
			powers.put(n2, 1L);
			primeFactorsWithPowers.add(powers);
		}
		return primeFactorsWithPowers;
	}

	public static void main(String[] args) {

		List<Map<Long, Long>> primesWithPowers = getPrimeFactorsWithPowers(837482995L);

		System.out.println(primesWithPowers);

	}
}

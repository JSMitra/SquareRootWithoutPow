package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class for computing co-primes between in the range [n1, n2] where n2 and n2
 * are two integers >= 1
 * 
 * @author JSMitra
 *
 */
public class CoPrimesCalculator {

	public static List<Pair<Long>> getCoPrimes(long n) {
		List<Pair<Long>> coPrimePairs = new ArrayList<>();

		Set<Long> listOfPrimesUnderN = PrimeNumberHelper.getListOfPrimes(n);

		Map<Long, Set<Long>> primeMultiplesMap = new HashMap<>();
		Iterator<Long> listOfPrimesUnderNIter = listOfPrimesUnderN.iterator();
		while (listOfPrimesUnderNIter.hasNext()) {
			primeMultiplesMap.put(listOfPrimesUnderNIter.next(), new HashSet<>());
		}
		Map<Long, Set<Long>> primeFactorsMap = new HashMap<>();
		Map<Long, Set<Long>> nonPrimeFactorsMap = new HashMap<>();

		for (long i = 2; i <= n; i++) {
			Set<Long> primeFactors = PrimeNumberHelper.getPrimeFactors(i);
			Iterator<Long> primeFactorsIter = primeFactors.iterator();
			while (primeFactorsIter.hasNext()) {
				primeMultiplesMap.get(primeFactorsIter.next()).add(i);
			}
			primeFactorsMap.put(i, primeFactors);
			Set<Long> nonPrimeFactors = CommonElements.getElementsUniqueToFirstSet(listOfPrimesUnderN, primeFactors);
			nonPrimeFactorsMap.put(i, nonPrimeFactors);

		}

		for (long i = 2; i <= n; i++) {
			Set<Long> nonPrimeFactors = nonPrimeFactorsMap.get(i);
			Iterator<Long> nonPrimeFactorsIter = nonPrimeFactors.iterator();
			while (nonPrimeFactorsIter.hasNext()) {
				long nonPrimeFactor = nonPrimeFactorsIter.next();
				if (nonPrimeFactor < i) {
					continue;
				}
				Set<Long> primeMultiples = primeMultiplesMap.get(nonPrimeFactor);
				Iterator<Long> primeMultiplesIter = primeMultiples.iterator();
				while (primeMultiplesIter.hasNext()) {
					long primeMultiple = primeMultiplesIter.next();
					if (!primeFactorsMap.get(primeMultiple).contains(i)) {
						coPrimePairs.add(new Pair<Long>(i, primeMultiple));
					}

				}
			}
		}

		return coPrimePairs;
	}

	public static void main(String[] args) {
		List<Pair<Long>> coPrimes = getCoPrimes(1000L);
		Iterator<Pair<Long>> coPrimesIter = coPrimes.iterator();
		while(coPrimesIter.hasNext()) {
			Pair<Long> coPrime = coPrimesIter.next();
			System.out.println(coPrime);
		}
	}
}

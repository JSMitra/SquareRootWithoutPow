package com.practice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CommonElements {

	public static Set<Long> getCommonElements(Set<Long> a1, Set<Long> a2){
		Set<Long> commonElementSet = new HashSet<>();
		Iterator<Long> a1Iter = a1.iterator();
		while(a1Iter.hasNext()) {
			long elem = a1Iter.next();
			if(a2.contains(elem)) {
				commonElementSet.add(elem);
			}
		}
		return commonElementSet;
	}
	
	/**
	 * Returns the elements present only in set a1
	 * @param a1
	 * @param a2
	 * @return
	 */
	public static Set<Long> getElementsUniqueToFirstSet(Set<Long> a1, Set<Long> a2){
		Set<Long> uniqueElementsOfA1 = new HashSet<>();
		Iterator<Long> a1Iter = a1.iterator();
		while(a1Iter.hasNext()) {
			long elem = a1Iter.next();
			if(!a2.contains(elem)) {
				uniqueElementsOfA1.add(elem);
			}
		}
		return uniqueElementsOfA1;
	}
	
	public static void main(String[] args) {
		Set<Long> a1 = new HashSet<>();
		Set<Long> a2 = new HashSet<>();
		
		a1.add(2L);
		a1.add(3L);
		a1.add(4L);
		
		a2.add(3L);
		a2.add(4L);
		a2.add(5L);
		
		System.out.println(getCommonElements(a1, a2));
		
	}
}

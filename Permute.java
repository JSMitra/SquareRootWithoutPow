package com.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

class PermuteWork {
    private int numberOfItems;
	private int index;
	private int value;
	private Set<Integer> remainingItems;

	PermuteWork(int n, int index, int val, Set<Integer> r) {
		this.numberOfItems = n;
		this.index = index;
		this.value = val;
		if (r != null) {
			this.remainingItems = new HashSet<>(r);
			remainingItems.remove(value);
		}

	}
	
	public int getNumberOfItems() {
		return this.numberOfItems;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		remainingItems.add(this.index);
		this.index = index;
		remainingItems.remove(this.index);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Set<Integer> getRemainingItems() {
		return remainingItems;
	}


}
/**
 * A class meant to do permutations
 * 
 * @author JSMitra
 *
 */
public class Permute {

	private static int[] constructWholeNumberArray(int numberOfObjects, boolean nullInit) {
		int[] wholeNumberArray = new int[numberOfObjects];
		for (int i = 0; i < numberOfObjects; i++) {
			if(nullInit) {
				wholeNumberArray[i] = -1;
			}else {
			wholeNumberArray[i] = i;
			}
		}
		return wholeNumberArray;
	}

	private static int[] cloneArray(int[] wholeNumberArray) {
		int numberOfObjects = wholeNumberArray.length;
		int[] wholeNumberArrayClone = new int[numberOfObjects];
		for (int i = 0; i < numberOfObjects; i++) {
			wholeNumberArrayClone[i] = wholeNumberArray[i];
		}
		return wholeNumberArrayClone;
	}

	private static Set<Integer> getWholeNumberSet(int n) {
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i<n;i++) {
			set.add(i);
		}
		return set;
	}
	
	
	private static int[] executeWork(int[] array, PermuteWork pm, Stack<PermuteWork> workStack) {
		
		int n = pm.getNumberOfItems();
		int last_index = n-1;
		if(pm.getIndex() < 0) {
			for(int i=0;i<n;i++) {
				workStack.push(new PermuteWork(n, 0, i, getWholeNumberSet(n)));
			}
		}else {
			int i = pm.getIndex();
			int v = pm.getValue();
			array[i] = v;
			if(i < last_index) {
				Set<Integer> remainingItems = pm.getRemainingItems();
				Iterator<Integer> remainingItemsIter = remainingItems.iterator();
				while(remainingItemsIter.hasNext()) {
					workStack.push(new PermuteWork(n, i+1, remainingItemsIter.next(), remainingItems));
				}
			}else {
				return array;
			}
			
		}
		return null;
	}
	public static List<int[]> getPermutationsWithoutRecursion(int numberOfObjects) {
		List<int[]> permutations = new ArrayList<>();
		Stack<PermuteWork> workStack = new Stack<>();
		PermuteWork pmItem = new PermuteWork(numberOfObjects, -1, -1, null);
		workStack.push(pmItem);
		int[] array = constructWholeNumberArray(numberOfObjects,true);
		while(!workStack.isEmpty()) {
			PermuteWork pm = workStack.pop();
			int[] result = executeWork(array, pm, workStack);
			if(result != null) {
				permutations.add(cloneArray(result));
			}
		}
		return permutations;
	}

	public static void main(String[] args) {
		int N = 10;
		long startTime = System.currentTimeMillis();
		List<int[]> permutations = Permute.getPermutationsWithoutRecursion(N);
		long secondsTaken = (System.currentTimeMillis() - startTime)/1000L;
		
		// uncomment it for printing out all the permutations
		/*Iterator<int[]> permutationsIter = permutations.iterator();
		while (permutationsIter.hasNext()) {
			int[] permutationSet = permutationsIter.next();
			for (int n : permutationSet) {
				System.out.print(n + " ");
			}
			System.out.println("\n");
		}*/
		
		System.out.println("Seconds taken to compute permutations of "+N +" items = "+secondsTaken);
		System.out.println("Number of permutations = "+permutations.size());
	}
}

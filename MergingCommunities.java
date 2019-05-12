package com.practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Solution to HackerRank problem 'Merging Communities'
 * 
 * @author JSMitra
 *
 */
public class MergingCommunities {

	private static int modulus(int n) {
		return (n > 0) ? n : -n;
	}

	private static int getGroup(int[] array, int member) {
		if (array[member] < 0)
			return member;
		return getGroup(array, array[member]);
	}

	private static void merge(List<Integer[]> commands, int N) {
		int[] array = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			array[i] = -1;
		}

		Iterator<Integer[]> commandsIter = commands.iterator();
		while (commandsIter.hasNext()) {
			Integer[] command = commandsIter.next();
			if (command.length == 2) {
				// merge both the groups
				int g1 = command[0];
				int g2 = command[1];
				
				int newG1 = getGroup(array, g1);
				int newG2 = getGroup(array, g2);

				if (newG1 == newG2)
					continue;

				if (newG1 < newG2) {

					array[newG1] += array[newG2];
					array[newG2] = newG1;

				} else {
					array[newG2] += array[newG1];
					array[newG1] = newG2;
				}
			} else if (command.length == 1) {
				
				int member = command[0];
				System.out.println(modulus(array[getGroup(array, member)]));
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int Q = in.nextInt();
		List<Integer[]> commands = new ArrayList<>();
		for (int i = 0; i < Q; i++) {

			String c = in.next();
			switch (c.charAt(0)) {
			case 'M': {
				Integer[] merge = new Integer[2];
				merge[0] = in.nextInt();
				merge[1] = in.nextInt();
				commands.add(merge);
			}
				break;
			case 'Q': {
				Integer[] query = new Integer[1];
				query[0] = in.nextInt();
				commands.add(query);
			}
				break;
			}
		}
		merge(commands, N);
		in.close();
	}

}

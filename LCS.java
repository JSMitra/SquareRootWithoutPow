package com.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class CharPoint {
	public int row;
	public int col;
	public boolean isMatched;
	public int weight;

	public CharPoint() {
		row = -1;
		col = -1;
	}

	public CharPoint(int r, int c) {
		row = r;
		col = c;
	}

}

public class LCS {

	static int findPosOfFromIndex(int c, int[] s, int fromIndex) {
		if (s == null)
			return -1;
		for (int i = fromIndex; i < s.length; i++) {
			if (s[i] == c)
				return i;
		}
		return -1;
	}

	static List<Integer> getPostionsOfCharInString(int c, int[] s) {
		List<Integer> positions = new ArrayList<>();
		int index = findPosOfFromIndex(c, s, 0);

		while (index >= 0) {
			positions.add(index);
			index = findPosOfFromIndex(c, s, index + 1);
		}
		return positions;
	}

	static int getLargestWeightBottomRightList(int row, int column, CharPoint[][] matrix, int maxRows, int maxCols) {
		int weight = 0;
		int nextRow = row + 1;

		while (nextRow < maxRows) {
			for (int i = column + 1; i < maxCols; i++) {
				if (matrix[nextRow][i].isMatched && matrix[nextRow][i].weight > weight) {
					weight = matrix[nextRow][i].weight;
				}
			}
			nextRow++;
		}
		return weight;
	}

	static List<CharPoint> getBottomRightItems(int row, int column, CharPoint[][] matrix, int maxRows, int maxCols,
			int weight) {
		List<CharPoint> items = new ArrayList<>();
		for (int s1Iter = row + 1; s1Iter < maxRows; s1Iter++)
			for (int s2Iter = column + 1; s2Iter < maxCols; s2Iter++) {
				if (matrix[s1Iter][s2Iter].weight == weight) {
					items.add(new CharPoint(s1Iter, s2Iter));
				}
			}
		return items;
	}

	public static String getLCS(int[] s1, int[] s2) {
		String result = "";

		// allocate a matrix
		int s1Len = s1.length;
		int s2Len = s2.length;

		HashMap<Integer, List<Integer>> matchingPoints = new HashMap<>();
		new HashMap<>();
		new HashMap<>();
		int largestWeight = 0;
		CharPoint[][] matrix = new CharPoint[s1Len][s2Len];
		for (int s1Iter = 0; s1Iter < s1Len; s1Iter++)
			for (int s2Iter = 0; s2Iter < s2Len; s2Iter++)
				matrix[s1Iter][s2Iter] = new CharPoint();

		for (int s1Iter = s1Len - 1; s1Iter >= 0; s1Iter--) {
			int c1 = s1[s1Iter];

			List<Integer> matchingColumns = null;
			if (matchingPoints.containsKey(c1)) {
				matchingColumns = matchingPoints.get(c1);
			} else {
				matchingColumns = getPostionsOfCharInString(c1, s2);
				matchingPoints.put(c1, matchingColumns);
			}
			Iterator<Integer> matchingColumnsIter = matchingColumns.iterator();

			while (matchingColumnsIter.hasNext()) {
				int column = matchingColumnsIter.next();
				matrix[s1Iter][column].isMatched = true;

				int bottomRightListWeight = getLargestWeightBottomRightList(s1Iter, column, matrix, s1Len, s2Len);
				matrix[s1Iter][column].weight = bottomRightListWeight + 1;
				if (matrix[s1Iter][column].weight > largestWeight) {
					largestWeight = matrix[s1Iter][column].weight;
				}
			}
		}

		int currentWeight = largestWeight;
		List<Integer> lcs = new ArrayList<>();
		for (int s1Iter = 0; s1Iter < s1Len && currentWeight > 0; s1Iter++) {
			for (int s2Iter = 0; s2Iter < s2Len && currentWeight > 0; s2Iter++) {

				// System.out.print(matrix[s1Iter][s2Iter].weight + " ");
				if (matrix[s1Iter][s2Iter].weight == currentWeight) {
					lcs.add(s2[s2Iter]);
					currentWeight--;
					break;
				}

			}
			// System.out.println("");

		}
		int lcsLen = lcs.size();
		int lcsIndex = 0;
		while (lcsIndex < lcsLen) {
			System.out.print(lcs.get(lcsIndex) + " ");
			lcsIndex++;
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n1 = Integer.parseInt(in.next());
		int n2 = Integer.parseInt(in.next());

		int[] s1 = new int[n1];
		for (int i = 0; i < n1; i++) {
			s1[i] = Integer.parseInt(in.next());
		}
		int[] s2 = new int[n2];
		for (int i = 0; i < n2; i++) {
			s2[i] = Integer.parseInt(in.next());
		}
		in.close();

		// String s1 = "ABCDE";
		// String s2 = "ADCBE";
		System.out.println(getLCS(s1, s2));
	}

}

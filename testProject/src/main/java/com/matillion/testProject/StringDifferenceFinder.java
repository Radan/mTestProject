package com.matillion.testProject;

/**
 * In this scenario, there are two strings of equal length, however between them
 * there are a number of differences. Compare the two strings and return the
 * number of differences in characters between the two.
 * @author radhakrishnan
 *
 */
public class StringDifferenceFinder {

	/**
	 * @param stringOne the first String, must not be null
	 * @param stringTwo the second String, must not be null
	 * @return distance
	 * @throws IllegalArgumentException if either input is {@code null} or if they
	 *                                  do not have the same length
	 */
	public int getNumberOfDifferences(final String stringOne, final String stringTwo) {

		if (stringOne == null || stringTwo == null) {
			throw new IllegalArgumentException("Strings must not be null");
		}

		if (stringOne.length() != stringTwo.length()) {
			throw new IllegalArgumentException("Strings must have the same length");
		}

		int numberOfDifferences = 0;

		for (int i = 0; i < stringOne.length(); i++) {
			if (stringOne.charAt(i) != stringTwo.charAt(i)) {
				numberOfDifferences++;
			}
		}

		return numberOfDifferences;
	}

}

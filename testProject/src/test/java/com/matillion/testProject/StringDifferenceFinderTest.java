package com.matillion.testProject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StringDifferenceFinderTest {

	private StringDifferenceFinder finder;

	@Before
	public void setUp() throws Exception {
		this.finder = new StringDifferenceFinder();
	}

	@After
	public void tearDown() throws Exception {
		this.finder = null;
	}

	@Test
	public void testGetNumberOfDifferences() {
		assertEquals(0, this.finder.getNumberOfDifferences("", ""));
		assertEquals(0, this.finder.getNumberOfDifferences("pappa", "pappa"));
		assertEquals(1, this.finder.getNumberOfDifferences("1011101", "1011111"));
		assertEquals(2, this.finder.getNumberOfDifferences("ATCG", "ACCC"));
		assertEquals(3, this.finder.getNumberOfDifferences("karolin", "kerstin"));
		assertEquals(5, this.finder.getNumberOfDifferences("D23W8MCCIZQOP9", "D236862CEZQOPS"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetNumberOfDifferencesNullStrings() {
		this.finder.getNumberOfDifferences(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetNumberOfDifferencesUnEqualStrings() {
		this.finder.getNumberOfDifferences("test", "testing");
	}

}

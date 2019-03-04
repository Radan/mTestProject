package com.matillion.testProject;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeDatabaseManagerTest {

	private EmployeeDatabaseManager testObject;

	@Before
	public void setUp() throws Exception {
		this.testObject = new EmployeeDatabaseManager();
	}

	@After
	public void tearDown() throws Exception {
		this.testObject = null;
	}
	
	@Test
	public void testDisplayEmployeeRecords(){		
		String[] args = {"HQ General Management","Monthly","Graduate Degree"};
		testObject.displayEmployeeRecords(args);
		assertEquals(3, testObject.getEmployees().size());
	}
	
	@Test
	public void testDisplayEmployeeRecordsInvalidParam(){		
		String[] args = {"invalid","Monthly","Graduate Degree"};
		testObject.displayEmployeeRecords(args);
		assertEquals(0, testObject.getEmployees().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDisplayEmployeeRecordsNullParam(){		
		String[] args = {"HQ General Management","Monthly"};
		testObject.displayEmployeeRecords(args);
	}

}

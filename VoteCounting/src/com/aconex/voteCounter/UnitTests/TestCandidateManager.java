/**
 * 
 */
package com.aconex.voteCounter.UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aconex.voteCounter.Manager.CandidateManager;
import com.aconex.voteCounter.Model.Candidate;

/**
 * @author Gandhali
 * About Class: This class contains unit test methods to test functionality of CandidateManager class.
 * 
 */
class TestCandidateManager {

	private CandidateManager candidate = new CandidateManager();
	
	/**
	 * Test method for {@link com.aconex.voteCounter.Manager.CandidateManager#readCandidates()}.
	 */
	@Test
	void testReadCandidates() {
		//Prepare test data
				boolean readCandidateResult;
				ArrayList<Candidate> candidateList;
				String fileName = "xyz.txt";
				//call the ReadCandidate Method
				readCandidateResult = candidate.readCandidates(fileName);
				candidateList = candidate.getCandidates();
				//Assert the test results
			
			assertEquals(true, readCandidateResult);
			assertEquals(candidateList, candidate.getCandidates());
	}
	
	/*
	 * Method name: testNegativeReadCandidates
	 * Method Description: Test case asserts the invalid scenario when file name is invalid.
	 * Input parameter: invalid file name
	 * expected result from ReadCandidate: should return false as readCandidate operation fails.
	 */
	@Test
	void testNegativeReadCandidates() {
		//Prepare test data
		boolean readCandidateResult;
		ArrayList<Candidate> candidateList;
		String fileName = "abc.txt";
		//call the ReadCandidate Method
		readCandidateResult  = candidate.readCandidates(fileName);
		candidateList = candidate.getCandidates();
		//Assert the test results
		assertEquals(false, readCandidateResult);
	}
	
}

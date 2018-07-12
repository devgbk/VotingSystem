package com.aconex.voteCounter.UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aconex.voteCounter.Manager.CandidateManager;
import com.aconex.voteCounter.Model.Candidate;

class TestNegaviteCandidateManager {
	

	CandidateManager candidate = new CandidateManager();
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	/*
	 * Method name: testNegativeReadCandidates
	 * Method Description: Test case asserts the invalid scenario when file name is invalid.
	 * Input parameter: invalid file name
	 * expected result from ReadCandidate: should return false as readCandidate operation fails.
	 */
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

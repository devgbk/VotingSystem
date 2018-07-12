package com.aconex.voteCounter.UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.aconex.voteCounter.Manager.BallotManager;
import com.aconex.voteCounter.Manager.CandidateManager;
import com.aconex.voteCounter.Model.Ballot;
import com.aconex.voteCounter.Model.Candidate;

class TestElectCandidate {
	ArrayList<Ballot> testBallots = new ArrayList<Ballot>(); //Form Test ballots
	BallotManager ballotManager = new BallotManager();
	Candidate candidate;
	CandidateManager candidateManager = new CandidateManager();
	@BeforeEach
	void setUp() throws Exception {
	}

//	@Test
//	void testCastVotes() {
//		ballotManager.castVotes(testBallots);
//		
//	}

	@Test
	void testGetQuota() {
//		public int getQuota(ArrayList<Ballot> ballots) 
		//1. Prepare Test Data input
		//To Do: Call getBallotsMethod
		int expectedResultQuota = 5;
		int actualResultQuota;
		//2. Execute test scenario
		actualResultQuota = ballotManager.getQuota(testBallots);
		
		//3. Assert test results
		assertEquals(expectedResultQuota, actualResultQuota);
		
	}
	//With Successful Candidate Elected As a Winner
	@Test
	void testSelectWinnerPositive() {

	}

	@Test
	void testElectCandidate() {
		
		//Stage1: prepare data -- list of candidates and list of ballots
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		candidateManager.readCandidates("test.txt");
		
		
		ArrayList<Candidate> votes = new ArrayList<Candidate>();
		votes.add(candidates.get(0));
		votes.add(candidates.get(1));
		votes.add(candidates.get(3));
		votes.add(candidates.get(2));
		
		ballots.add(new Ballot(votes));
		
		
	}

	@Test
	void testEliminateCandidate() {
		fail("Not yet implemented");
	}

	@Test
	void testEliminateBallot() {
		fail("Not yet implemented");
	}
 	@Test
	void testFindMaxVoteCount() {
		fail("Not yet implemented");
	}

	@Test
	void testFindMinVoteCount() {
		fail("Not yet implemented");
	}

}

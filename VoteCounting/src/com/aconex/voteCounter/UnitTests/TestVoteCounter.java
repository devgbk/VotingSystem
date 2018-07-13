package com.aconex.voteCounter.UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.aconex.voteCounter.Manager.CandidateManager;
import com.aconex.voteCounter.Model.Ballot;
import com.aconex.voteCounter.Model.Candidate;
import com.aconex.voteCounter.view.VoteCounterUI;

class TestVoteCounter {

	CandidateManager candidateManager = new CandidateManager();
	VoteCounterUI voteCounterUI = new VoteCounterUI();
	
	@Test
	void testFindWinner() {
		//Stage 1: Prepare test data
		
		
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("ABDC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("BAD", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("CABD", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("CDAB", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("DA", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("DB", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("BAC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("CBAD", candidates);
		ballots.add(ballot);
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(1), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}
	
	@Test
	void testFindWinnerRandomEliminationCase() {
		//Stage 1: Prepare test data
		
		
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("AB", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("AB", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("BA", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("BA", candidates);
		ballots.add(ballot);
		
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(1), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}
	
	//test -- (abc, bac, cab)
	
	@Test
	void testFindWinnerRandomEliminationCase2() {
		
		
		//Stage 1: Prepare test data
			
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("ABC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("BAC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("CAB", candidates);
		ballots.add(ballot);
		
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(0), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}
	//test --(a,b,c)
	
	//Test case where random elimination needs to be done and ballots get exhausted
	@Test
	void testFindWinnerBallotExhaustionCase() {
		//Stage 1: Prepare test data
		
		
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("A", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("B", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("C", candidates);
		ballots.add(ballot);
		
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(1), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}
	//test -- (a,b)
	
	
	//Test case where ballot is exhausted and quota needs to be recalculated
	@Test
	void testFindWinnerBallotExhaustionCase2() {
		//Stage 1: Prepare test data
		
		
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("ABC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("ABC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("DAC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("C", candidates);
		ballots.add(ballot);
		
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(0), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}
	
	
	//Test case where there is a clear winner
	@Test
	void testFindWinnerClearWinnerCase() {
		//Stage 1: Prepare test data
		
		
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("ACD", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("ABC", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("ADF", candidates);
		ballots.add(ballot);
		ballot = voteCounterUI.processVotes("AFI", candidates);
		ballots.add(ballot);
		
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(0), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}

	
	//Test case for only one ballot
	@Test
	void testFindWinnerDirectWinnerCase() {
		//Stage 1: Prepare test data
		
		
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("CAB", candidates);
		ballots.add(ballot);
				
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(2), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}


	//Test case for invalid ballot entries
	@Test
	void testFindWinnerInvalidInputCase() {
		//Stage 1: Prepare test data
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("ACD", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		
		ballot = voteCounterUI.processVotes("BAC", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		ballot = voteCounterUI.processVotes("BDF", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		ballot = voteCounterUI.processVotes("AFZ", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		ballot = voteCounterUI.processVotes("AFK", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(1), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}

	
	//Test case for duplicate votes
	@Test
	void testFindWinnerInvalidInputDuplicateVoteCase() {
		//Stage 1: Prepare test data
		
		Candidate winner = null;
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		Ballot ballot;
		ArrayList<Candidate> candidates ;
		candidateManager.readCandidates("xyz.txt");
		candidates = candidateManager.getCandidates();
		ballot = voteCounterUI.processVotes("ACD", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		
		ballot = voteCounterUI.processVotes("BAC", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		ballot = voteCounterUI.processVotes("BDF", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		ballot = voteCounterUI.processVotes("AFF", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		ballot = voteCounterUI.processVotes("ADD", candidates);
		if(ballot!=null) {
			ballots.add(ballot);
		}
		
		
		
		//Stage 2: Call method
		
		winner = voteCounterUI.findWinner(ballots, candidates);
		
		//Stage 3: assert
		if(winner!= null) {
			assertEquals(candidates.get(1), winner);
		}
		else {
			fail("Null winner. Test case failed");
		}
			
		
	}

}

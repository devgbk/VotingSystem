package com.aconex.voteCounter.UnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.aconex.voteCounter.Manager.CandidateManager;
import com.aconex.voteCounter.Model.Ballot;
import com.aconex.voteCounter.Model.Candidate;
import com.aconex.voteCounter.view.VoteCounterUI;

class TestVoterCounter {

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
	void testFindWinnerBoundryCase() {
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
	
	//test -- (ab,ab,ba,ba)
	//test -- (abc, bac, cab)
	//test --(a,b,c)
	//test -- (a,b)
	

	@Test
	void testGetVote() {
		fail("Not yet implemented");
	}

}

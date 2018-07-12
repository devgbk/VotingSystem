package com.aconex.voteCounter.Model;

import java.util.ArrayList;

/**
 * @author Gandhali
 * About Class: This class contains the structure of a single ballot
 * 
 */

public class Ballot {
	
	ArrayList<Candidate> votes = new ArrayList<Candidate>(); // list of preferences of the voter
	private boolean exhausted; // to check whether the ballot is exhausted 
	int currentVote;	//a pointer to the current preference of the voter to keep a track of votecount
	
	public Ballot(ArrayList<Candidate> votes) {
		this.votes = votes;
		exhausted = false;
		currentVote=0;
	}
	
	
	public int getCurrentVote() {
		return currentVote;
	}


	public void increamentCurrentVote() {
		currentVote++;
}


	public ArrayList<Candidate> getVotes() {
		return votes; 
	}

	public void setVotes(ArrayList<Candidate> votes) {
		this.votes = votes;
	}

	public boolean isExhausted() {
		return exhausted;
	}

	public void setExhausted(boolean exhausted) {
		this.exhausted = exhausted;
	}
	
	
}

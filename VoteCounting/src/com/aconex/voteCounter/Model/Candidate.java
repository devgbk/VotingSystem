package com.aconex.voteCounter.Model;

public class Candidate {
	private char index;
	private String name;
	private int votes;
	private boolean eliminated;
	
	
	public Candidate(char index, String name) {
		this.index = index;
		this.name = name;
		this.votes =0;
		eliminated = false;
	}
	@Override
	public String toString() {
		return index + ": " + name ;
	}
	
	public void increamentVoteCount() {
		votes++;
	}
	public char getIndex() {
		return index;
	}
	public void setIndex(char index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVoteCount() {
		return votes;
	}
	public boolean isEliminated() {
		return eliminated;
	}
	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}
	
	
	
}

package com.aconex.voteCounter.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.aconex.voteCounter.Manager.BallotManager;
import com.aconex.voteCounter.Manager.CandidateManager;
import com.aconex.voteCounter.Model.Ballot;
import com.aconex.voteCounter.Model.Candidate;


/**
 * @author Gandhali
 * Class Name : VoteCounterUI
 * About Class: This class interacts with the user to get the input for 
 * 				ballots and displays the winner
 * 
 */
public class VoteCounterUI {
	
	ArrayList<Candidate> candidates ; 
	Candidate winner;
	Scanner sc;
	CandidateManager candidateManager ;
	BallotManager ballotManager;
	ArrayList<Ballot> ballots;
	
	 
	public VoteCounterUI() {
		sc = new Scanner(System.in);
		candidateManager = new CandidateManager();
		candidates = candidateManager.getCandidates();
		
		ballotManager = new BallotManager();
		ballots = new ArrayList<Ballot>();
	}
	
	public ArrayList<Ballot> getBallots() {
		return ballots;
	}

	public void setBallots(ArrayList<Ballot> ballots) {
		this.ballots = ballots;
	}
	
	/**
	 * Method Name: run
	 * Method Description: main method which connects all the code.
	 */
	public void run() {
		displayMenu();
		winner = findWinner(ballots, candidates);
		displayWinner();
	}
	
	/**
	 * Method Name: findWinner
	 * Method Description: calls the 'electCandidate' method in ballotManager
	 * 						to find the winner.
	 */
	public Candidate findWinner(ArrayList<Ballot> ballots, ArrayList<Candidate> candidates) {
		return ballotManager.electCandidate(ballots, candidates);
	}
	
	/**
	 * Method Name: displayMenu
	 * Method Description: displays the menu(List of candidates) to the users
	 * 						and allows them to submit their preferences.
	 */
	public void displayMenu() {
		candidateManager.readCandidates("xyz.txt");
		Ballot ballot;
		String input = null;
		for(Candidate candidate : candidates) {
			System.out.println(candidate);
		}
		do {
			 input = getVote();
			 ballot = processVotes(input, candidates);
			 if(ballot!=null) {
				 ballots.add(ballot);
			 }
			 
		}while(!input.equalsIgnoreCase("tally"));

	}
	
	/**
	 * Method Name: getVote
	 * Method Description: gets the preference list from user.
	 */
	public String getVote() {
		System.out.print(">");
		return sc.nextLine().replaceAll("\\s+","").toUpperCase();
	}
	
	/**
	 * Method Name: processVotes
	 * Method Description: validates the votes and form a ballot object and adds to the
	 * 						list of ballots.
	 */
	public Ballot processVotes(String input, ArrayList<Candidate> candidates) {
		
		Ballot ballot;
		boolean invalidBallot = false;
		if(isValidVote(input)) {
			char[] votes = input.toCharArray();
			ArrayList<Candidate> ballotVotes = new ArrayList<Candidate>();
			for(char vote: votes) {
				Candidate c = getCandidate(vote, candidates);
				if(c==null)
				{
					invalidBallot = true;
					break;
				}
				
				ballotVotes.add(c);
			}	
			if(!invalidBallot) {
				ballot = new Ballot(ballotVotes);
				return ballot;
			}
		}
		return null;
		
	}
	
	/**
	 * Method Name: getCandidate
	 * Method Description: validate whether the vote has a valid candidate assigned for that input letter. 
	 */
	public Candidate getCandidate(char vote, ArrayList<Candidate> candidates) {
			for(Candidate candidate: candidates) {
				if(candidate.getIndex()== vote)
					return candidate;
			}
		return null;
	}
	
	/**
	 * Method Name: isValidVote
	 * Method Description: validate if the input string has only characters 
	 */
	public boolean isValidVote(String vote) {
		return vote.matches("[a-zA-Z]+");
	}
	
	/**
	 * Method Name: dosplayWinner
	 * Method Description: display the winner to the user.
	 */
	public void displayWinner() {
		System.out.println("Candidate " + winner + " selected as winner");
		
	}
	
}

package com.aconex.voteCounter.Manager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aconex.voteCounter.Model.Ballot;
import com.aconex.voteCounter.Model.Candidate;


/**
 * @author Gandhali
 * About Class: This class performs the operations to elect the winner from the list of candidates
 * 
 */


public class BallotManager {
	
	private List<Candidate> candidates ;
	private Candidate winner;
	
	/*
	 * Method Name: castVotes
	 * Method Description: 1st round of election where 1st preference candidate 
	 * of every ballot is casted a vote.
	 */
	public void castVotes(ArrayList<Ballot> ballots){
		for(Ballot b: ballots) {
				b.getVotes().get(0).increamentVoteCount();
		}
	}
	
	/*
	 * Method Name: getQuota
	 * Method Description: Calculates the quota(number of required votes for winning)
	 * 					   based on number of non-exhausted ballots.
	 * 	
	 * Quota = (> 50% of number of ballots) 
	*/
	public int getQuota(ArrayList<Ballot> ballots) {
		int count =0;
		for(Ballot b: ballots) {
			if(!b.isExhausted())
				count++;
		}
		return (count/2)+1;
	}

	
	/*
	 * Method Name: selectWinner
	 * Method Description: Selects a winner in case of majority i.e., a candidates posses maximum 
	 * 						number of votes and the vote count is greater than quota.  
	*/
	public Candidate selectWinner(ArrayList<Ballot> ballots, ArrayList<Candidate> candidates) {
		int maxVoteCount = findMaxVoteCount(candidates);
		Random randomGenerator = new Random(); //to eliminate a candidate randomly in case of tie
		int index = 0;
		 
		/*if(getNonEliminatedCandidateCount(candidates)>2 && (getNonEliminatedCandidateCount(candidates)==getMaxVoteCandidateCount(candidates))) {
			 if maximum number of non-eliminated candidates are more than 2 and
			all have the same vote count which is maximum vote count then
			eliminate a candidate randomly
			
			index = randomGenerator.nextInt(candidates.size());
			
			while(!candidates.get(index).isEliminated()) {
				index = randomGenerator.nextInt(candidates.size());
			}
			candidates.get(index).setEliminated(true);
			return null;
			
		}		
		else {*/
				if(getNonEliminatedCandidateCount(candidates)==1) {
					for(Candidate c: candidates) {
						if(!c.isEliminated())
							return c;
					}
				}
				else {
					if(maxVoteCount >= getQuota(ballots)) {
						for(Candidate c: candidates) {
							if(c.getVoteCount() == maxVoteCount) {
								return c;
							}
						}
					}
				}
			
		//}
		
		return null;
	}
	
	
	/*
	 * Method Name: electWinner
	 * Method Description: Complete process to elect a candidate as a winner.
	 * 						Inclusive of selection and elimination of candidates
	 * 						until a winner is found.  
	*/
	public Candidate electCandidate(ArrayList<Ballot> ballots, ArrayList<Candidate> candidates) {
		
		winner = null;
		int maxVoteCount = findMaxVoteCount(candidates);
		Random randomGenerator = new Random(); //to eliminate a candidate randomly in case of tie
		int index = 0;
		if(ballots.size()>1) {
			castVotes(ballots);
			winner = selectWinner(ballots, candidates);
			while(winner == null) {
				
				 
				if(getNonEliminatedCandidateCount(candidates)>=2 && (getNonEliminatedCandidateCount(candidates)==getMaxVoteCandidateCount(candidates))) {
					/* if maximum number of non-eliminated candidates are more than 2 and
					all have the same vote count which is maximum vote count then
					eliminate a candidate randomly
					*/
					index = randomGenerator.nextInt(candidates.size());
					
					while(candidates.get(index).isEliminated()) {
						index = randomGenerator.nextInt(candidates.size());
					}
					candidates.get(index).setEliminated(true);
					System.out.println(candidates.get(index) + " eliminated");
					
				}
				else {
					eliminateCandidate(candidates, ballots);
				}
				
				reVote(ballots);
				winner = selectWinner(ballots, candidates);
				
			}
		}
		else {
			winner = ballots.get(0).getVotes().get(0);
		}
		
		return winner;
	}
	
	private void reVote(ArrayList<Ballot> ballots) {
		//boolean reNewedVote = false;
		Candidate c;
for(Ballot b: ballots) {
			if(!b.isExhausted()) {
				c =b.getVotes().get(b.getCurrentVote());
				if(c.isEliminated()) {
					if(b.getCurrentVote()<b.getVotes().size()-1) {
						b.increamentCurrentVote();
					}
					else {
						b.setExhausted(true);
						break;
					}
					for(int i = b.getCurrentVote() ; i<b.getVotes().size();i++) {
						if(b.getVotes().get(i).isEliminated()) {
							if(b.getCurrentVote()<b.getVotes().size()-1) {
								b.increamentCurrentVote();
							}
							else {
								b.setExhausted(true);
							}
							/*if(b.getCurrentVote() > b.getVotes().size()-1) {
								b.setExhausted(true);
							}*/
						}
						else {
							b.getVotes().get(i).increamentVoteCount();
							break;
						}
					}
				}
				
			}
		}
		
	}
	/*
	 * Method Name: eliminateBallot
	 * Method Description: Eliminate a ballot if all the candidates in
	 * 						preference list of votes are eliminated, 
	 * 						set the ballot as exhausted and exclude it from
	 * 						the count of total number of votes(for calculation of quota)
	 *
	*/

	public void eliminateCandidate(ArrayList<Candidate> candidates, ArrayList<Ballot> ballots) {
		int leastVotes = findMinVoteCount(ballots, candidates);
		
		for(Candidate c: candidates) {
			if(!c.isEliminated()) {
				if(c.getVoteCount() == leastVotes)
				{
					c.setEliminated(true);
					System.out.println(c + " eliminated");
				}
			}
			
			
		}
		
		/*Candidate candidateToEliminate = null;
		for(Candidate c : candidates) {
			if(c.getVoteCount()<leastvotes) {
				leastvotes = c.getVoteCount();
				candidateToEliminate = c;
			}
		}
		if(candidateToEliminate!=null) {
			candidateToEliminate.setEliminated(true);
			System.out.println("Candidate " + candidateToEliminate.getName() + " eliminated");
			
		}*/
		
	}
	
	public void eliminateBallot(Ballot ballot) {
		boolean flag=true;
		for(Candidate c: candidates) {
			if(!c.isEliminated())
			{
				flag=false;
				break;
			}
			
		}
		ballot.setExhausted(flag);
	}
	
	public int findMaxVoteCount(ArrayList<Candidate> candidates) {
		int max=-1;
		
		for(Candidate c: candidates) 
		{
			if(!c.isEliminated()) {
				if(c.getVoteCount()>max) {
					max = c.getVoteCount();
				}
			}
			
		}
		
		return max;
	}
	
	public int findMinVoteCount(ArrayList<Ballot> ballots, ArrayList<Candidate> candidates) {
		int min=getQuota(ballots);
		
		for(Candidate c: candidates) 
		{
			if(!c.isEliminated()) {
				if(c.getVoteCount() < min ) {
					min = c.getVoteCount();
				}
			}
			
		}
		return min;
	}
	/*
	 * Method Desc: returns the count of non-eliminated candidates 
	 */
	public int getNonEliminatedCandidateCount(ArrayList<Candidate> candidates) {
		int count = 0;
		for(Candidate c: candidates) {
			if(!c.isEliminated()) {
				count++;
			}
		}
		
		return count;
	}
	
	/*
	 * Method desc: returns the count of candidates who have maximum votes
	 * 
	 */
	public int getMaxVoteCandidateCount(ArrayList<Candidate> candidates) {
		int count = 0;
		int maxCount = findMaxVoteCount(candidates);
		for(Candidate c: candidates) {
			if(!c.isEliminated()) {
				if(c.getVoteCount() == maxCount)
					count++;
			}
			
		}
		
		return count;
	}
	
	
}

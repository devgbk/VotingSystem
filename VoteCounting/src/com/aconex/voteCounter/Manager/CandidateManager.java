package com.aconex.voteCounter.Manager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.aconex.voteCounter.Model.Candidate;

public class CandidateManager {
	 ArrayList<Candidate> candidates = new ArrayList<Candidate>();
	 
	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}

/*	public void setCandidates(ArrayList<Candidate> candidates) {
		this.candidates = candidates;
	}*/

	public boolean readCandidates(String fileName) {
		String line;
//		String fileName = "xyz.txt";
		
		try {
				
	            FileReader fileReader =  new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            int i=65;
	            while((line = bufferedReader.readLine()) != null) {
	                
	            	candidates.add(new Candidate((char)i, line.trim()));
	            	i++;
	            }   
	
	            // Always close files.
	            bufferedReader.close(); 
	            return true;
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" +  fileName + "'");
            return false;
        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");  
            return false;
        }
	}
	
	/*public static void main(String args[]) {
		readCandidates();
		
		for(Candidate c: candidates) {
			System.out.println(c);
		}
	}*/
}

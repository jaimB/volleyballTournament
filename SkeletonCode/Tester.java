package SkeletonCode;

import java.util.Scanner;

import SkeletonCode.SingleElimination;
import SkeletonCode.Team;
import SkeletonCode.Tournament;

//This file is cancer please god, kill it.
public class Tester {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String name = "a";
		String sDate = "b";
		String eDate = "c";
		String structure = "d";

		System.out.println(name + "; " + sDate + "; " + eDate + "; " + structure);

		Tournament t = new Tournament(name, sDate, eDate, structure);
		Team team1 = new Team("Team1");
		Team team2 = new Team("Team2", eDate);
		Team team3 = new Team("Team3");
		Team team4 = new Team("Team4");
		Team team5 = new Team("Team5");
		
		t.addTeam(team1);
		t.addTeam(team2);
		t.addTeam(team3);
		t.addTeam(team4);
		t.addTeam(team5);
		Structure s = new SingleElimination(t.getTeamList());
		for (int i = 0; i < t.getTeamList().size() ; i++){System.out.println(t.getTeamList().get(i).getTeamName());}
		
		System.out.println(s.getBrackets().get(0).getGames().get(0).getTeamTwo().getTeamName());
	
		for (int i = 0; i < s.getBrackets().get(0).getGames().size(); i++){
			s.getBrackets().get(0).getGames().get(i).setScoreOne(15);
			s.getBrackets().get(0).getGames().get(i).setScoreTwo(13);
			s.getBrackets().get(0).getGames().get(i).completeGame();	
		}
		System.out.println(s.getBrackets().get(0).getWinners().get(0).getTeamName() + " "  + s.getBrackets().get(0).getWinners().get(1).getTeamName() + " " + s.getBrackets().get(0).getWinners().get(2).getTeamName());
		
		s.advanceTournament();
		//b.makeGames();
		s.getBrackets().get(0).getGames().get(0).setScoreOne(15);
		s.getBrackets().get(0).getGames().get(0).setScoreTwo(13);
		s.getBrackets().get(0).getGames().get(0).completeGame();
		
		System.out.println(s.getBrackets().get(0).getWinners().get(0).getTeamName());
/*		
		System.out.println("Divisions:");
		
		s = new Divisions(t.getTeamList(), 2);
		for (int i = 0; i < s.getBrackets().size(); i++){
			for(int j = 0; j < s.getBrackets().get(i).getGames().size(); j++){
				s.getBrackets().get(i).getGames().get(j).setScoreOne(15);
				s.getBrackets().get(i).getGames().get(j).setScoreTwo(13);
				s.getBrackets().get(i).getGames().get(j).completeGame();
			}
		}
		for (int i = 0; i < s.getBrackets().size(); i++) {
			for (int j = 0; j < s.getBrackets().get(i).getWinners().size(); j++){System.out.print(s.getBrackets().get(i).getWinners().get(j).getTeamName() + " ");} 
		}
		
		System.out.println("");
		s.advanceTournament();
		
		for (int i = 0; i < s.getBrackets().size(); i++){
			for(int j = 0; j < s.getBrackets().get(i).getGames().size(); j++){
				s.getBrackets().get(i).getGames().get(j).setScoreOne(15);
				s.getBrackets().get(i).getGames().get(j).setScoreTwo(13);
				s.getBrackets().get(i).getGames().get(j).completeGame();
			}
		}
		
		
		s.advanceTournament();
		
		for (int i = 0; i < s.getBrackets().size(); i++){
			for(int j = 0; j < s.getBrackets().get(i).getGames().size(); j++){
				s.getBrackets().get(i).getGames().get(j).setScoreOne(15);
				s.getBrackets().get(i).getGames().get(j).setScoreTwo(13);
				s.getBrackets().get(i).getGames().get(j).completeGame();
			}
		}
		
		for (int i = 0; i < s.getBrackets().size(); i++) {
			for (int j = 0; j < s.getBrackets().get(i).getWinners().size(); j++){System.out.print(s.getBrackets().get(i).getWinners().get(j).getTeamName() + " ");} 
		}
		*/
	}
}
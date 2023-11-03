package it.polito.tdp.exam.db;

import java.util.List;

import it.polito.tdp.exam.model.People;
import it.polito.tdp.exam.model.PeopleSalary;

public class TestBaseballDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BaseballDAO dao = new BaseballDAO();

//		List<People> players = dao.readAllPlayers();
//		System.out.println(players.size());
		List<PeopleSalary> peopleSalary = dao.getPlayersSalaryTeamYear("Chicago Cubs", 1985);
		System.out.println(peopleSalary);
	
	}

}

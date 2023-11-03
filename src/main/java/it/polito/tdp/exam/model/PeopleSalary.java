package it.polito.tdp.exam.model;

import java.util.Objects;

public class PeopleSalary {

	private People player;
	private Double salary;
	
	public PeopleSalary(People player, Double salary) {
		this.player = player;
		this.salary = salary;
	}

	public People getPlayer() {
		return player;
	}

	public void setPlayer(People player) {
		this.player = player;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(player, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PeopleSalary other = (PeopleSalary) obj;
		return Objects.equals(player, other.player) && Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return "PeopleSalary [player=" + player + ", salary=" + salary + "]";
	}
	
}

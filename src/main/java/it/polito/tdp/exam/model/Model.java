package it.polito.tdp.exam.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.exam.db.BaseballDAO;

public class Model {
	
	private BaseballDAO dao;
	private Graph<Integer, DefaultWeightedEdge> grafo ;
	private Map<Integer, List<PeopleSalary>> annoToPlayers;

	
	public Model() {
		this.dao = new BaseballDAO();
		this.annoToPlayers = new HashMap<Integer, List<PeopleSalary>>();
		
	}
	
	
	public void creaGrafo(String name) {
		BaseballDAO dao = new BaseballDAO() ;
		
		
		this.grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class) ;
		
		
		// al grafo aggiungo i vertici
		List<Integer> vertici = this.dao.getVertici(name);
		Graphs.addAllVertices(this.grafo, vertici) ;
		
		
		 //Leggere i giocatori per ogni anno
        this.annoToPlayers.clear();
        for (int anno : vertici) {
            this.annoToPlayers.put(anno, this.dao.getPlayersSalaryTeamYear(name, anno));
        }
		
		
		//verificare ogni coppia di anni, e creare un arco con il peso corrispondente
		for (int i = 0; i <vertici.size(); i++) {
			for (int j = i+1; j < vertici.size(); j++) {
				List<PeopleSalary> salari1 = new ArrayList<PeopleSalary>(this.annoToPlayers.get(vertici.get(i)));
				List<PeopleSalary> salari2 = new ArrayList<PeopleSalary>(this.annoToPlayers.get(vertici.get(j)));
				double peso = Math.abs(getCumulativeSalary(salari1) - getCumulativeSalary(salari2));
					Graphs.addEdgeWithVertices(this.grafo, vertici.get(i), vertici.get(j), peso);
				}
			}
		
		
	}
	
		
	 public double getCumulativeSalary(List<PeopleSalary> giocatori) {
	      double salaryCum = 0;
	      for (PeopleSalary ps : giocatori)
	          salaryCum += ps.getSalary();

	      return salaryCum;
	    }


	public List<String> getTeamsNames() {
		return this.dao.getTeamsNames();
	}


	public Set<Integer> getVertici() {
		return this.grafo.vertexSet();
	}


	public Set<DefaultWeightedEdge> getArchi() {
		return this.grafo.edgeSet();
	}
	
	
	public List<Dettaglio> getDettagli(int anno) {
		List<Dettaglio> result = new ArrayList<Dettaglio>();
		List<Integer> adiacenti = Graphs.neighborListOf(this.grafo, anno);
		
		// aggiungo a dettaglio anno e peso associati
	    for(Integer nodo : adiacenti) {
	        DefaultWeightedEdge arco = this.grafo.getEdge(anno, nodo);
	        double peso = this.grafo.getEdgeWeight(arco);
	        result.add(new Dettaglio(nodo, (int) peso)); // Aggiunge il peso come intero a Dettaglio
	    }
	    
		Collections.sort(result);
		return result;
		
	}
}

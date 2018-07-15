package br.com.softplan.sienge.calculofrete.road;

public enum RoadType {

	PAVIMENTADA("Pavimentada", 0.54D),
	NAO_PAVIMENTADA("Não pavimentada", 0.62D);
	
	private String description;
	private double cost;
	
	RoadType(String description, double cost) {
		this.description = description;
		this.cost = cost;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public double getCost() {
		return this.cost;
	}
}

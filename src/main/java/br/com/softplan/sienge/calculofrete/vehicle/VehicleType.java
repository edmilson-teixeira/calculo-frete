package br.com.softplan.sienge.calculofrete.vehicle;

public enum VehicleType {

	CAMINHAO_BAU("Caminhao Baú", 1.00D),
	CAMINHAO_CACAMBA("Caminhão Caçamba", 1.05D),
	CARRETA("Carreta", 1.12D);
	
	private String description;
	private double rate;
	
	VehicleType(String description, double rate) {
		this.description = description;
		this.rate = rate;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public double getRate() {
		return this.rate;
	}
	
}

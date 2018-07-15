package br.com.softplan.sienge.calculofrete.service;

import br.com.softplan.sienge.calculofrete.exception.VehicleNotFoundException;

public interface FreightCalculatorService {

	double calulate(double pavedRoadDistance, double notPavedRoadDistance, String vehicleName, int weightCargo) throws VehicleNotFoundException;
	
}

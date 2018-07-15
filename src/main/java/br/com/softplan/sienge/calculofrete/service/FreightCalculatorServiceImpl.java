package br.com.softplan.sienge.calculofrete.service;

import org.springframework.stereotype.Service;

import br.com.softplan.sienge.calculofrete.exception.VehicleNotFoundException;
import br.com.softplan.sienge.calculofrete.road.RoadType;
import br.com.softplan.sienge.calculofrete.vehicle.VehicleType;

@Service("freightCalculatorService")
public class FreightCalculatorServiceImpl implements FreightCalculatorService {

	private static final int FREE_WEIGHT_LIMIT = 5;
	private static final double COST_OF_TONNE_EXCEEDED = 0.02D;
	
	@Override
	public double calulate(double pavedRoadDistance, double notPavedRoadDistance, String vehicleName, int weightCargo) throws VehicleNotFoundException {
		VehicleType vehicleType = this.getVehicleType(vehicleName);
		double costCargo = this.calculateNormalCost(pavedRoadDistance, notPavedRoadDistance, vehicleType);
		if (weightCargo > FREE_WEIGHT_LIMIT) {
			costCargo += this.calculateExceededCost(pavedRoadDistance, notPavedRoadDistance, weightCargo - FREE_WEIGHT_LIMIT);
		}
		return costCargo;
	}

	private VehicleType getVehicleType(String vehicleName) throws VehicleNotFoundException {
		if (vehicleName == null || vehicleName.isEmpty()) {
			throw new VehicleNotFoundException("Nenhum tipo de veículo selecionado.");
		}
		try {
			return VehicleType.valueOf(vehicleName);
		} catch (IllegalArgumentException e) {
			throw new VehicleNotFoundException(String.format("Tipo de veículo não encontrado: %s.", vehicleName));
		}
	}

	private double calculateNormalCost(double pavedRoadDistance, double notPavedRoadDistance, VehicleType vehicleType) {
		return this.calculateRoadCost(pavedRoadDistance, notPavedRoadDistance) * vehicleType.getRate();
	}

	private double calculateExceededCost(double pavedRoadDistance, double notPavedRoadDistance, int exceededWeightCargo) {
		return (pavedRoadDistance + notPavedRoadDistance) * exceededWeightCargo * COST_OF_TONNE_EXCEEDED;
	}

	private double calculateRoadCost(double pavedRoadDistance, double notPavedRoadDistance) {
		return pavedRoadDistance * RoadType.PAVIMENTADA.getCost() + notPavedRoadDistance * RoadType.NAO_PAVIMENTADA.getCost();
	}

}

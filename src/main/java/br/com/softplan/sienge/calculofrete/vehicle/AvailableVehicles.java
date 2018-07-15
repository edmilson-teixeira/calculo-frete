package br.com.softplan.sienge.calculofrete.vehicle;

import java.util.ArrayList;
import java.util.List;

public class AvailableVehicles {

	private List<Vehicle> vehicles;
	
	public AvailableVehicles() {
		this.vehicles = new ArrayList<Vehicle>();
		for (VehicleType type : VehicleType.values()) {
			vehicles.add( new Vehicle(type.name(), type.getDescription()) );
		}
	}
	
	public List<Vehicle> getVehicles() {
		return this.vehicles;
	}
}

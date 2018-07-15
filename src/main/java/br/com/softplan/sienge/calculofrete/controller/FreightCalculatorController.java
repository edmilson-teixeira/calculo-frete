package br.com.softplan.sienge.calculofrete.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.softplan.sienge.calculofrete.exception.VehicleNotFoundException;
import br.com.softplan.sienge.calculofrete.service.FreightCalculatorService;
import br.com.softplan.sienge.calculofrete.vehicle.AvailableVehicles;

@Controller
public class FreightCalculatorController {

	@Autowired
	private FreightCalculatorService freightCalculatorService;
	
	@GetMapping("/vehicle-type")
	public @ResponseBody ResponseEntity<AvailableVehicles> getVehicleTypes() {
		AvailableVehicles availableVehicles = new AvailableVehicles();
		return new ResponseEntity<AvailableVehicles>(availableVehicles, HttpStatus.OK);
	}
	
    @GetMapping("/calculate")
    public @ResponseBody ResponseEntity<String> calculate(
    		@RequestParam double pavedRoadDistance, 
    		@RequestParam double notPavedRoadDistance, 
    		@RequestParam String vehicleType, 
    		@RequestParam int weightCargo) {
    	try {
	    	double value = freightCalculatorService.calulate(pavedRoadDistance, notPavedRoadDistance, vehicleType, weightCargo);
	    	BigDecimal roundValue = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
	        return new ResponseEntity<String>(roundValue.toString(), HttpStatus.OK);
    	} catch (VehicleNotFoundException e) {
    		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	}
    }

}

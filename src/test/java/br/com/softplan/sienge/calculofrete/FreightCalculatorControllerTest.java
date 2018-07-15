package br.com.softplan.sienge.calculofrete;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.sienge.calculofrete.controller.FreightCalculatorController;
import br.com.softplan.sienge.calculofrete.vehicle.AvailableVehicles;
import br.com.softplan.sienge.calculofrete.vehicle.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FreightCalculatorControllerTest {

	@Autowired
    private TestRestTemplate restTemplate;
	@Autowired
	FreightCalculatorController freightCalculatorController;
    
	private String buildQueryUrl(int pavedRoadDistance, int notPavedRoadDistance, String vehicleType, int weightCargo) {
    	return new StringBuilder("pavedRoadDistance=")
    			.append(pavedRoadDistance)
    			.append("&notPavedRoadDistance=")
    			.append(notPavedRoadDistance)
    			.append("&vehicleType=")
    			.append(vehicleType)
    			.append("&weightCargo=")
    			.append(weightCargo).toString();
    }
	
    @Test
    public void containsThreeVehicles() {
    	ResponseEntity<AvailableVehicles> freightCalculatorResponse = restTemplate.getForEntity("/vehicle-type", AvailableVehicles.class);
        assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(new Vehicle("CAMINHAO_BAU", "Caminhao Baú"));
        vehicles.add(new Vehicle("CAMINHAO_CACAMBA", "Caminhão Caçamba"));
        vehicles.add(new Vehicle("CARRETA", "Carreta"));
        assertThat(freightCalculatorResponse.getBody().getVehicles().equals(vehicles));
    }
    
    @Test
    public void invalidVehicleName() {
	    String queryString = "/calculate?" + this.buildQueryUrl(0, 0, "CAMINHAO_INEXISTENTE", 0);
	    ResponseEntity<String> freightCalculatorResponse = restTemplate.getForEntity(queryString, String.class);
    	assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(freightCalculatorResponse.getBody().equals("Tipo de veículo não encontrado: CAMINHAO_INEXISTENTE."));
    }
    
    @Test
    public void vehicleNotFound() {
	    String queryString = "/calculate?" + this.buildQueryUrl(0, 0, "", 0);
	    ResponseEntity<String> freightCalculatorResponse = restTemplate.getForEntity(queryString, String.class);
    	assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(freightCalculatorResponse.getBody().equals("Nenhum tipo de veículo selecionado."));
    }
    
    @Test
    public void pavedWithBucketTruckOverExceededCargo() {
    	String queryString = "/calculate?" + this.buildQueryUrl(100, 0, "CAMINHAO_CACAMBA", 8);
    	ResponseEntity<String> freightCalculatorResponse = restTemplate.getForEntity(queryString, String.class);
        assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(freightCalculatorResponse.getBody().equals("62.70"));
    }
    
    @Test
    public void notPavedWithTruckTrunkLowerCargo() {
    	String queryString = "/calculate?" + this.buildQueryUrl(0, 60, "CAMINHAO_BAU", 4);
    	ResponseEntity<String> freightCalculatorResponse = restTemplate.getForEntity(queryString, String.class);
        assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(freightCalculatorResponse.getBody().equals("37.20"));
    }
    
    @Test
    public void notPavedWithTruckOverExceededCargo() {
    	String queryString = "/calculate?" + this.buildQueryUrl(0, 180, "CARRETA", 12);
    	ResponseEntity<String> freightCalculatorResponse = restTemplate.getForEntity(queryString, String.class);
        assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(freightCalculatorResponse.getBody().equals("150.19"));
    }
 
    @Test
    public void bothRoadsWithTruckTrunkOverExceededCargo() {
    	String queryString = "/calculate?" + this.buildQueryUrl(80, 20, "CAMINHAO_BAU", 6);
    	ResponseEntity<String> freightCalculatorResponse = restTemplate.getForEntity(queryString, String.class);
        assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(freightCalculatorResponse.getBody().equals("57.60"));
    }
    
    @Test
    public void bothRoadsWithBucketTruckOverLimitCargo() {
    	String queryString = "/calculate?" + this.buildQueryUrl(50, 30, "CAMINHAO_CACAMBA", 5);
    	ResponseEntity<String> freightCalculatorResponse = restTemplate.getForEntity(queryString, String.class);
        assertThat(freightCalculatorResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(freightCalculatorResponse.getBody().equals("47.88"));
    }
}

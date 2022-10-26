package marechal.loan.cars_lease.car;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cars")
@CrossOrigin(origins = "http://localhost:3000")
public class CarController {
	@Autowired
	CarService carService;
	
	@GetMapping(value = {"", "/"})
	public List<Car> getAllCars() {
		return carService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> getCarById(@PathVariable("id") long id) {
		Car car = carService.getById(id);
		if(car != null)
			return new ResponseEntity<>(car, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/color/{color}")
	public ResponseEntity<List<Car>> getCarsByColor(@PathVariable("color") String color){
		List<Car> cars = carService.getByColor(color);
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	
	@GetMapping("/brand/{brand}")
	public ResponseEntity<List<Car>> getCarsByBrand(@PathVariable("brand") String brand){
		List<Car> cars = carService.getByBrandC(brand);
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id){
		if(carService.deleteById(id)==true)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Car> createCar(@RequestBody Car car){
		try {
			Car createdCar = carService.add(car);
			return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

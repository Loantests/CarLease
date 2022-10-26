package marechal.loan.cars_lease.contract;

import java.util.List;
import java.util.Optional;

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
@RequestMapping("/api/v1/contracts")
@CrossOrigin(origins = "http://localhost:3000/")
public class ContractController {
	@Autowired
	ContractService contractService;
	
	@GetMapping(value = {"", "/"})
	public List<Contract> listContract(){
		return contractService.getAllContracts();
	}
	
	@GetMapping(value = {"/{id}", "/{id}/"})
	public ResponseEntity<Contract> getContractById(@PathVariable("id") long id){
		Contract contract = contractService.getById(id);
		
		if(contract != null)
			return new ResponseEntity<>(contract, HttpStatus.OK);
		else 
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Contract> createContract(@RequestBody Contract contract) {
		try {
			Contract createdContract = contractService.add(contract);
			return new ResponseEntity<>(createdContract, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Contract> deleteContractBtId(@PathVariable("id") long id){
		if(contractService.deleteById(id)==true)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

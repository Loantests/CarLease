package marechal.loan.cars_lease.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {
	@Autowired
	ContractRepository contractRepository;
	
	public Contract add(Contract contract) {
		return contractRepository.save(contract);
	}
	
	public List<Contract> getAllContracts(){
		return contractRepository.findAll();
	}
	
	public Contract getById(Long id) {
		return contractRepository.findById(id).orElse(null);
	}

	public boolean deleteById(long id) {
		try {
			contractRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

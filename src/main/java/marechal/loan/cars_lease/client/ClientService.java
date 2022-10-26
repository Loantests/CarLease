package marechal.loan.cars_lease.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> getAll(){
		return clientRepository.findAll();
	}
	
	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	public Client getById(long id) {
		return clientRepository.findById(id).orElse(null);
	}
	
	public boolean deleteById(long id) {
		try {
			clientRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Client getByFnameAndLname(String fname, String lname){
		return clientRepository.findByFnameAndLname(fname, lname).orElse(null);
	}
	
	/*public boolean update(Client client) {
		try {
			clientRepository.save(client);
			return true;
		} catch (Exception e) {
			return false;
		}
	}*/
}

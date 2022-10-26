package marechal.loan.cars_lease;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import marechal.loan.cars_lease.car.Car;
import marechal.loan.cars_lease.car.CarService;
import marechal.loan.cars_lease.client.Client;
import marechal.loan.cars_lease.client.ClientService;
import marechal.loan.cars_lease.contract.Contract;
import marechal.loan.cars_lease.contract.ContractService;
import marechal.loan.cars_lease.invoice.Invoice;

@SpringBootApplication
public class CarsLeaseApplication implements CommandLineRunner{
	@Autowired
	CarService carService;
	@Autowired
	ContractService contractService;
	@Autowired
	ClientService clientService;
	
	public static void main(String[] args) {
		SpringApplication.run(CarsLeaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Car car = new Car();
		car.setBrand("Mercedes");
		car.setColor("black");
		car.setFirstUse(Date.valueOf("2000-02-02"));
		carService.add(car);
		car = new Car();
		car.setBrand("Renault");
		car.setColor("blue");
		carService.add(car);
		
		Contract contract = new Contract();
		contract.setDate(Date.valueOf("2022-03-03"));
        contract.setStartLease(Date.valueOf("2022-03-03"));
        contract.setEndLease(Date.valueOf("2022-03-03"));
        contract.setTotalPrice(5000);
        contract.setAdvance(1500);
        contract.setLeftToPay(3500);
        contract.setPlaceOfReturn("Paris");
		contractService.add(contract);
		
		contract = new Contract();
        contract.setPlaceOfReturn("Lille");
		contractService.add(contract);
		
		Client client = new Client();
		client.setFname("Loan");
		client.setLname("Marechal");
		client.setAddress("Saint-Vrain");
		client.setDob(Date.valueOf("2001-12-28"));
		clientService.save(client);
		
		client = new Client();
		client.setFname("Une");
		client.setLname("Personne");
		client.setAddress("Quelque part");
		clientService.save(client);
		
		Invoice invoice = new Invoice();
		contract.setId(1);
		contract.setDate(null);
        contract.setStartLease(Date.valueOf("2022-03-03"));
        contract.setEndLease(Date.valueOf("2022-03-03"));
        contract.setTotalPrice(5000);
        contract.setAdvance(1500);
        contract.setLeftToPay(3500);
        contract.setPlaceOfReturn("Paris");
		invoice.setContract(contract);
	}

}

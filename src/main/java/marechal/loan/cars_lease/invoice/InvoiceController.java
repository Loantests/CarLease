package marechal.loan.cars_lease.invoice;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/invoice")
@CrossOrigin(origins = "http://localhost:3000")
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping(value = {"", "/", "/all", "/all/"})
	public List<Invoice> getAllInvoices(){
		return invoiceService.getAll();
	}
	
	@GetMapping(value = {"/{id}", "/{id}/"})
	public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") long id){
		Invoice invoice = invoiceService.getById(id);
		if(invoice != null)
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value = {"", "/"})
	public ResponseEntity<Invoice> createInvoice (@RequestBody Invoice invoice) {
		try {
			Invoice createdInvoice = invoiceService.save(invoice);
			return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = {"", "/"})
	public ResponseEntity<Invoice> deleteInvoice (@PathVariable("id") long id) {
		if(invoiceService.deleteById(id) == true)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(value = {"/{id}", "/{id}/"})
	public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice){
		Invoice existingInvoice = invoiceService.getById(id);
		
		if(existingInvoice != null) {
			Invoice updatedInvoice = new Invoice();
			updatedInvoice.setId(existingInvoice.getId());
			updatedInvoice.setAddress(invoice.getAddress());
			updatedInvoice.setAmount(invoice.getAmount());
			updatedInvoice.setContract(invoice.getContract());
			updatedInvoice.setDate(invoice.getDate());
			return new ResponseEntity<>(invoiceService.save(updatedInvoice), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

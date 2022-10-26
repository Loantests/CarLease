package marechal.loan.cars_lease.option;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import marechal.loan.cars_lease.car.Car;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "optionn")
public class Option {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	
	@ManyToMany(mappedBy = "options")
	@JsonProperty(access = Access.READ_WRITE)
    private List<Car> cars;
}

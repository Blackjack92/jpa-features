package ch.business.decision.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("VEHICLE")
@Entity
public class Vehicle implements IWrapperContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String plateNumber;

}

package com.htp.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Table(name = "m_adress")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "n_house")
    private String houseNumber;

    @Column(name = "n_flat")
    private Integer flatNumber;

    @Column(name = "n_floor")
    private Integer floorNumber;

    @Column(name = "n_porch")
    private Integer porchNumber;

    @Column(name = "is_pizza")
    private Boolean isPizzaAdress;

    public Adress(String street, String houseNumber, Integer flatNumber, Integer floorNumber, Integer porchNumber, Boolean isPizzaAdress){
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
        this.floorNumber = floorNumber;
        this.porchNumber = porchNumber;
        this.isPizzaAdress = isPizzaAdress;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

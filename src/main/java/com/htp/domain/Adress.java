package com.htp.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
public class Adress {

    private Long id;

    private String street;

    private String houseNumber;

    private Integer flatNumber;

    private Integer floorNumber;

    private Integer porchNumber;

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

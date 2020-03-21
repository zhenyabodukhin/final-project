package com.htp.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "m_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode(exclude = {"id"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "adress_id")
    private Long adressId;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_done")
    private boolean isDone;

    public Order(Long userId, Long adressId, String phoneNumber){
        this.userId = userId;
        this.adressId = adressId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "m_users")
@Data
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(exclude = {"id", "roles", "orders"})
@ToString(exclude = {"roles", "orders"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "changed")
    private Timestamp changed;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "userRole")
    private Set<Role> roles = Collections.emptySet();

//    @JsonManagedReference
//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "userOrder")
//    private Set<Order> orders = Collections.emptySet();

}

package com.htp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "m_roles")
@Data
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"id", "userRole"})
@ToString(exclude = {"userRole"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role")
    private String role;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User userRole;

}

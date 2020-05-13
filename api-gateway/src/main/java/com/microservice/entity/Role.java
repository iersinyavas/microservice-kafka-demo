package com.microservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Role {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "role")
    private String role;

}
package com.microservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
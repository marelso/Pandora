package com.pandora.backend.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "workshops")
@Getter
@Setter
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Account includedBy;
    private boolean deleted;
}

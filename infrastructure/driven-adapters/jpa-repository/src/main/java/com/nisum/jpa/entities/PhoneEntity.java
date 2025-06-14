package com.nisum.jpa.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;
}

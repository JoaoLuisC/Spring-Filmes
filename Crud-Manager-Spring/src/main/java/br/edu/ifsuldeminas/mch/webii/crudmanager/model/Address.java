package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "addresses")
public class Address {

    public Address () {}

    public Address(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Logradouro é obrigatório!")
    private String place;

    @NotNull(message = "Número é obrigatório!")
    private Integer number;

    @NotBlank(message = "CEP é obrigatório!")
    private String zipCode;
}

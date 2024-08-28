package br.edu.ifsuldeminas.mch.webii.crudmanager.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "cinemas")
public class Cinema {

    public Cinema() {}

    public Cinema(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Sala é obrigatória!")
    private String room;

    @NotBlank(message = "Nome do cinema é obrigatório!")
    private String cinemaName;

    @NotNull(message = "Preço é obrigatório!")
    private Double price;

    @NotBlank(message = "Horário é obrigatório!")
    private String showTime;

    @NotBlank(message = "Data é obrigatória!")
    private String date;

    @OneToMany(mappedBy = "cinema", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Movie> movies;
}

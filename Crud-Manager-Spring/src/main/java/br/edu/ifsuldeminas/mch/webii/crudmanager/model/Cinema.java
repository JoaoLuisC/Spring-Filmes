package br.edu.ifsuldeminas.mch.webii.crudmanager.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

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

    @ManyToMany
    @JoinTable(
        name = "cinema_movie",
        joinColumns = @JoinColumn(name = "cinema_id"),
        inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;
}

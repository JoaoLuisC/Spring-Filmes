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
@Table(name = "movies")
public class Movie {

    public Movie() {}

    public Movie(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Título é obrigatório!")
    private String title;

    @NotNull(message = "Duração é obrigatória!")
    private Integer duration; // Em minutos

    @NotBlank(message = "Gênero é obrigatório!")
    private String genre;

    @NotBlank(message = "Classificação etária é obrigatória!")
    private String ageRating;
}

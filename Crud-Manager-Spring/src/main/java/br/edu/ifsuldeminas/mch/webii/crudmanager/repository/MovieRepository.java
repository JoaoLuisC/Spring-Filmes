package br.edu.ifsuldeminas.mch.webii.crudmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}

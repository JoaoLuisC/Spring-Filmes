package br.edu.ifsuldeminas.mch.webii.crudmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Cinema;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Movie;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.CinemaRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.MovieRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDB implements CommandLineRunner{

    @Autowired
    private CinemaRepository cinemaRepo;

    @Autowired
    private MovieRepository movieRepo;

    @Override
    public void run(String... args) throws Exception {
        // Adicionando Filmes
        Movie movie1 = new Movie();
        movie1.setTitle("Inception");
        movie1.setDuration(148);
        movie1.setGenre("Sci-Fi");
        movie1.setAgeRating("+13");
        movieRepo.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("The Dark Knight");
        movie2.setDuration(152);
        movie2.setGenre("Action");
        movie2.setAgeRating("+16");
        movieRepo.save(movie2);

        Movie movie3 = new Movie();
        movie3.setTitle("The Godfather");
        movie3.setDuration(175);
        movie3.setGenre("Crime");
        movie3.setAgeRating("+18");
        movieRepo.save(movie3);

        // Adicionando Cinemas
        Cinema cinema1 = new Cinema();
        cinema1.setRoom("Sala 1");
        cinema1.setCinemaName("Cineplex Central");
        cinema1.setPrice(20.00);
        cinema1.setShowTime("20:00");
        cinema1.setDate("2024-08-28");
        cinema1.setMovie(movie1);
        cinemaRepo.save(cinema1);

        Cinema cinema2 = new Cinema();
        cinema2.setRoom("Sala 2");
        cinema2.setCinemaName("CineCity Mall");
        cinema2.setPrice(25.00);
        cinema2.setShowTime("22:00");
        cinema2.setDate("2024-08-29");
        cinema2.setMovie(movie2);
        cinemaRepo.save(cinema2);

        Cinema cinema3 = new Cinema();
        cinema3.setRoom("Sala 3");
        cinema3.setCinemaName("Grand Cinema");
        cinema3.setPrice(18.00);
        cinema3.setShowTime("18:00");
        cinema3.setDate("2024-08-30");
        cinema3.setMovie(movie3);
        cinemaRepo.save(cinema3);
    }
}

package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Cinema;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Movie;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.CinemaRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private CinemaRepository cinemaRepo;

    @GetMapping
    public String listMovies(Model model) {
        List<Movie> movies = movieRepo.findAll();
        model.addAttribute("movies", movies);
        return "movie"; 
    }

    @GetMapping("/form")
    public String showForm(@ModelAttribute("movie") Movie movie) {
        return "movie_form";
    }

    @PostMapping("/save")
    public String saveMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            return "movie_form";
        }
        movieRepo.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/update/{id}") 
    public String updateMovie(@PathVariable("id") Integer id, Model model) {  
        Optional<Movie> movieOptional = movieRepo.findById(id);
        Movie movie;

        if (movieOptional.isPresent()) {
            movie = movieOptional.get();
        } else {
            movie = new Movie();
        }

        model.addAttribute("movie", movie);
        return "movie_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") Integer id) {
        try{
            movieRepo.deleteById(id);
        }catch(DataIntegrityViolationException e){
            List<Cinema> relatedCinemas = cinemaRepo.findByMovieId(id);

            for (Cinema cinema : relatedCinemas) {
                cinemaRepo.delete(cinema);
            }

            movieRepo.deleteById(id);
        }
        return "redirect:/movies";
    }
}

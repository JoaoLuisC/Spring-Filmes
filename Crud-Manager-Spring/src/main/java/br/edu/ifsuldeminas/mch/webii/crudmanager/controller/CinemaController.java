package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Cinema;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Movie;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.CinemaRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.MovieRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaRepository cinemaRepo;

    @Autowired
    private MovieRepository movieRepo;

    @GetMapping
    public String listCinemas(Model model) {
        List<Cinema> cinemas = cinemaRepo.findAll();
        model.addAttribute("cinemas", cinemas);
        return "cinema"; 
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("cinema", new Cinema());
        model.addAttribute("allMovies", movieRepo.findAll()); // Para preencher o dropdown com filmes
        return "cinema_form";
    }

    @Transactional
    @PostMapping("/save")
    public String saveCinema(@ModelAttribute Cinema cinema, @RequestParam(value = "movies", required = false) List<Integer> movieIds) {
        if (movieIds != null) {
            Set<Movie> movies = new HashSet<>();
            for (Integer movieId : movieIds) {
                Movie movie = movieRepo.findById(movieId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + movieId));
                movies.add(movie);
            }
            cinema.setMovies(movies);
        }
        cinemaRepo.save(cinema);
        return "redirect:/cinemas";
    }

    @GetMapping("/edit/{id}")
    public String editCinema(@PathVariable("id") Integer id, Model model) {
        Cinema cinema = cinemaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid cinema Id:" + id));
        model.addAttribute("cinema", cinema);
        model.addAttribute("allMovies", movieRepo.findAll()); // Para preencher o dropdown com filmes
        return "cinema_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCinema(@PathVariable("id") Integer id) {
        cinemaRepo.deleteById(id);
        return "redirect:/cinemas";
    }
}

package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Movie;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepo;

    @GetMapping
    public String listMovies(Model model) {
        List<Movie> movies = movieRepo.findAll();
        model.addAttribute("movies", movies);
        return "movie"; // Nome do template deve corresponder ao nome do arquivo HTML
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie_form";
    }

    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie) {
        movieRepo.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable("id") Integer id, Model model) {
        Movie movie = movieRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));
        model.addAttribute("movie", movie);
        return "movie_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable("id") Integer id) {
        movieRepo.deleteById(id);
        return "redirect:/movies";
    }
}

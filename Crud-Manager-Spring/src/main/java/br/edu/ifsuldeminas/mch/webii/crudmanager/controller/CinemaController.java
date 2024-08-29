package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Cinema;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Movie;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.CinemaRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.MovieRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public String showForm(@ModelAttribute("cinema") Cinema cinema, Model model) {
        List<Movie> allMovies = movieRepo.findAll();  
        model.addAttribute("allMovies", allMovies);
        return "cinema_form";
    }

    @Transactional
    @PostMapping("/register")
    public String registerCinema(@Valid @ModelAttribute("cinema") Cinema cinema, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Movie> allMovies = movieRepo.findAll();
            model.addAttribute("allMovies", allMovies);
            return "cinema_form";
        }
        
        cinemaRepo.save(cinema);
        return "redirect:/cinemas";
    }

    @GetMapping("/update/{id}")
    public String updateCinema(@PathVariable("id") Integer id, Model model) {
        Optional<Cinema> cinemaOptional = cinemaRepo.findById(id);
        Cinema cinema;

        if (cinemaOptional.isPresent()) {
            cinema = cinemaOptional.get();
        } else {
            cinema = new Cinema();
        }

        List<Movie> allMovies = movieRepo.findAll();
        model.addAttribute("cinema", cinema);
        model.addAttribute("allMovies", allMovies);

        return "cinema_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCinema(@PathVariable("id") Integer id) {
        cinemaRepo.deleteById(id);
        return "redirect:/cinemas";
    }
}

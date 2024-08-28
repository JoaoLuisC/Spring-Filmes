package br.edu.ifsuldeminas.mch.webii.crudmanager.controller;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Cinema;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.CinemaRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return "cinema"; // Nome do template deve corresponder ao nome do arquivo HTML
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("cinema", new Cinema());
        model.addAttribute("movies", movieRepo.findAll()); // Para preencher o dropdown com filmes
        return "cinema_form";
    }

    @PostMapping("/save")
    public String saveCinema(@ModelAttribute Cinema cinema) {
        cinemaRepo.save(cinema);
        return "redirect:/cinemas";
    }

    @GetMapping("/edit/{id}")
    public String editCinema(@PathVariable("id") Integer id, Model model) {
        Cinema cinema = cinemaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid cinema Id:" + id));
        model.addAttribute("cinema", cinema);
        model.addAttribute("movies", movieRepo.findAll()); // Para preencher o dropdown com filmes
        return "cinema_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCinema(@PathVariable("id") Integer id) {
        cinemaRepo.deleteById(id);
        return "redirect:/cinemas";
    }
}

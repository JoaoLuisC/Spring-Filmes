package br.edu.ifsuldeminas.mch.webii.crudmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Address;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Movie;
import br.edu.ifsuldeminas.mch.webii.crudmanager.model.User;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.AddressRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.MovieRepository;
import br.edu.ifsuldeminas.mch.webii.crudmanager.repository.UserRepository;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class InitializeDB implements CommandLineRunner{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private MovieRepository movieRepo;

    @Override
    public void run(String... args) throws Exception {
        User luiz = new User();
        luiz.setName("Luiz Henrique Souza");
        luiz.setGender("M");
        luiz.setEmail("luiz@mail.com");
        
        Address address1 = new Address();
        address1.setPlace("Rua 25 de março");
        address1.setNumber(11);
        address1.setZipCode("37130-123");
        
        User gui = new User();
        gui.setName("Guilherme Souza");
        gui.setGender("M");
        gui.setEmail("gui@mail.com");

        Address address2 = new Address();
        address2.setPlace("Rua Sete");
        address2.setNumber(125);
        address2.setZipCode("37130-000");
        

        addressRepo.save(address1);
        addressRepo.save(address2);
        addressRepo.flush();

        luiz.setAddress(address1);
        gui.setAddress(address2);

        userRepo.save(luiz);
        userRepo.save(gui);

        Movie filme1 = new Movie();
        filme1.setTitle("titulo teste");
        filme1.setDuration(210);
        filme1.setGenre("ação");
        filme1.setAgeRating("+18");
        movieRepo.save(filme1);

    }

}

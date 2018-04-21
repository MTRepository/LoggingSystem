package pl.michal.logger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michal.logger.controllers.repositories.UserRepository;
import pl.michal.logger.controllers.services.UserService;
import pl.michal.logger.models.UserModel;

import java.util.Optional;

@Controller
public class MainController {

    // user repository -
    final UserRepository userRepository;

    // podpina usera pod userservice
    final UserService userService;

    @Autowired
    public MainController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // dodawanie uzytkownika dla klasy
    @ModelAttribute
    public Model startModel(Model model){
        model.addAttribute("user", userService.getUser());
        return model;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    // odniesienie do templatki
    @PostMapping("/login")
    public String loginPost(@RequestParam("login") String login,
                            @RequestParam("password") String password,
                            Model model){

        // Optional<UserModel> exist = userRepository.findByLoginAndPassword(login, Utils.hash256SHA(password));

        // pakuje pobranego usera do optionala
        Optional<UserModel> exist = userRepository.findByLoginAndPassword(login, password);

        // jezeli obiekt w optionalu istnieje to go pobiera
        if(exist.isPresent()){

            // ustawia status na zalogowany
            userService.setLogged(true);

            // ustawia usera na pobranego
            userService.setUser(exist.get());
            return "redirect:/content";
        }

        userService.setFalseLoginCounter(userService.getFalseLoginCounter() + 1);
        model.addAttribute("info", "Bad login or password");
        return "login";
    }

    @GetMapping("/content")
    @ResponseBody
    public String printContent(){
        if(userService.isLogged() == true) {
            return "Jestes zalogowany";
        } else {
            return "przykro mi...";
        }

    }




}

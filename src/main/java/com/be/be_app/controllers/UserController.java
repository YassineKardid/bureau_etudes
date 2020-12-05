package com.be.be_app.controllers;

import com.be.be_app.exceptions.DefaultException;
import com.be.be_app.models.ConfirmationToken;
import com.be.be_app.repositories.*;
import com.be.be_app.models.User;
import com.be.be_app.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private SecteurRepository secteurRepository;
    @Autowired
    private SiteRepository siteRepository;
    @PostMapping(path="/users")
    public @ResponseBody User createUser(@RequestBody User user, @RequestParam("profile") Long profileId,
                                         @RequestParam("secteur") Long secteurId, @RequestParam("site") Long siteId)
    {
        User existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if(existingUser != null){
            System.out.println("User "+ user.getEmail() +" already exist");
        }
        else{
            profileRepository.findById(profileId).map(profile -> {
                user.setProfile(profile);
                return user;
            }).orElseThrow(()-> new DefaultException("Can't set a profile"));
            siteRepository.findById(siteId).map(site -> {
                user.setSite(site);
                return user;
            }).orElseThrow(()-> new DefaultException("Can't set site"));
            secteurRepository.findById(secteurId).map(secteur -> {
                user.setSecteur(secteur);
                return user;
            }).orElseThrow(()->new DefaultException("Can't set secteur"));
            userRepository.save(user);

            /*ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Registration complete");
            mailMessage.setFrom("be_app@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);*/

        }
        return user;
    }
    @PostMapping(path = "/users/confirm-account")
    public @ResponseBody String confirmUserAccount(@RequestParam("token") String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null){
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
        }
        else {
            System.out.println("registration lik is invalid");
        }
        return "somethings";
    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}

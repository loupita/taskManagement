package org.sid.jwtspringsec.web;

import org.sid.jwtspringsec.entities.AppUser;
import org.sid.jwtspringsec.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm registerForm){

        if (!registerForm.getPassword().equals(registerForm.getRePassword()))
            throw new RuntimeException("You must confirm your password");
        AppUser user = accountService.findUserByUsername(registerForm.getUsername());
        if (user!=null) throw new RuntimeException("This user already exist !");
        AppUser appUser = new AppUser();
        appUser.setUsername(registerForm.getUsername());
        appUser.setPassword(registerForm.getPassword());
        accountService.saveUser(appUser);
        accountService.addRoleToUser(registerForm.getUsername(), "USER");
        return appUser;
    }
}

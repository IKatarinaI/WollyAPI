package com.backend.backend.api;
import com.backend.backend.domain.Cryptocurrency;
import com.backend.backend.domain.User;
import com.backend.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    private String getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return "";
    }

    @GetMapping("/getCryptoList")
    public ResponseEntity<List<Cryptocurrency>> getCryptoList(){
        String username = getLoggedInUser();
        if(username == ""){
            throw new UsernameNotFoundException("");
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/getCryptoList").toUriString());
        return ResponseEntity.ok().body(userService.getCryptoList(username));
    }

    @GetMapping("/currentUserMoney")
    public ResponseEntity<Double> currentUserMoney(){
        String username = getLoggedInUser();
        if(username == ""){
            throw new UsernameNotFoundException("");
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/currentUserMoney").toUriString());
        return ResponseEntity.ok().body(userService.getUsersMoney(username));
    }

    @PatchMapping("/addMoney")
    public ResponseEntity<?> addMoney(@RequestParam(name="value") Double value){
        String username = getLoggedInUser();
        if(username == ""){
            throw new UsernameNotFoundException("");
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/addMoney").toUriString());
        userService.addMoney(username, value);
        return ResponseEntity.ok("success");
    }

    @PatchMapping("/buyCryptocurrency")
    public ResponseEntity<?> buyCryptocurrency(@RequestParam(name="cryptoName") String cryptoName,
                                               @RequestParam(name="value") Double value){
        String username = getLoggedInUser();
        if(username == ""){
            throw new UsernameNotFoundException("");
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/buyCryptocurrency").toUriString());
        userService.buyCryptocurrency(username, cryptoName,value);
        return ResponseEntity.ok("success");
    }

    @PatchMapping("/sellCryptocurrency")
    public ResponseEntity<?> sellCryptocurrency(@RequestParam(name="cryptoName") String cryptoName,
                                                @RequestParam(name="value") Double value){
        String username = getLoggedInUser();
        if(username == ""){
            throw new UsernameNotFoundException("");
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/sellCryptocurrency").toUriString());
        userService.sellCryptocurrency(username, cryptoName,value);
        return ResponseEntity.ok("success");
    }

    // kako da im vratim jwt kao odgovor
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam(name="firstName") String firstName,
                                         @RequestParam(name="lastName") String lastName,
                                         @RequestParam(name="email") String email,
                                         @RequestParam(name="password") String password){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/register").toUriString());
        User user = userService.createUser(firstName,lastName, email, password);
        return ResponseEntity.created(uri).body(user);
    }
}

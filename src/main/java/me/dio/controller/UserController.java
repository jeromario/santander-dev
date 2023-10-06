package me.dio.controller;

import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<User>findById(@PathVariable Long id){
        User byId = userService.findById(id);
        return ResponseEntity.ok(byId);
    }

    @PostMapping
    public ResponseEntity<User>create(@RequestBody User user){
        User userCreate = userService.create(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User>update(@PathVariable Long id ,@RequestBody User user){
        User userUpdate = userService.update(id,user);

        return ResponseEntity.ok(userUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){

        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

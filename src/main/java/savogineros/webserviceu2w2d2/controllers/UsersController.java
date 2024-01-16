package savogineros.webserviceu2w2d2.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import savogineros.webserviceu2w2d2.entities.User;
import savogineros.webserviceu2w2d2.services.UsersService;

import java.util.ArrayList;
import java.util.List;

// 1. GET http://localhost:3001/users
// 2. POST http://localhost:3001/users (+body)
// 3. GET http://localhost:3001/users/:id
// 4. PUT http://localhost:3001/users/:id (+body)
// 5. DELETE http://localhost:3001/users/:id

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    // 1. GET http://localhost:3001/users
    @GetMapping
    public List<User> getUsersList() {
        return  usersService.getUsersList();
    }

    // 2. POST http://localhost:3001/users (+body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // (201) Per creare uno status più preciso del solito 200 OK
    public User save(@RequestBody User body) {
        usersService.saveUser(body);
        return body;
    }

    // 3. GET http://localhost:3001/users/:id
    @GetMapping("/{id}")
    public User findById(@PathVariable int id) {  // ATTENZIONE: qui automaticamente Spring trasforma l'id da stringa a intero
        return usersService.findById(id);         // e il nome dell'end-point deve corrispondere a quello del parametro id = id
    }

    // 4. PUT http://localhost:3001/users/:id (+body)
    @PutMapping("/{id}")
    public User findByIdAndUpdate(@PathVariable int id, @RequestBody User body) {
        return usersService.findByIdAndUpdate(id,body);
    }

    // 5. DELETE http://localhost:3001/users/:id
    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable int id) {
        usersService.findByIdAndDelete(id);
    }

}

package ro.home.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.home.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
Kill process by port on Windows
*open CMD*
netstat -ano | findstr :<PORT>
taskkill /PID <PID> /F

 */
/*
Dandu-se obiectul User cu atributele: id, username, password, age, realizeaza un API ce implementeaza operatiile CRUD:
* adaugare
* obtinere de useri (cu filtru de varsta minima optional)
* modificare completa de user
* modificare a parolei utilizatorului
* stergere de utilizator dupa id
* stergete de utilizator dupa username

Orice utilizator trebuie aiba username nenul, varsta minima 18 si parola de minim 4 caractere.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private static final List<User> users = new ArrayList<>();
    private static long id = 0L;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        user.setId(id);
        id++;
        users.add(user);
        return user;
    }

    // obtinere de useri (cu filtru de varsta minima optional)
    @GetMapping
    public List<User> getUser(@RequestParam(value = "ageMin", required = false) Integer ageMin) {

        return users.stream()
                .filter(user -> ageMin == null || user.getAge() >= ageMin)
                .collect(Collectors.toList());
    }

    //modificare completa de user
    @PutMapping
    public User update(@RequestBody User user) {
        var dbUser = users.stream()
                .filter(u -> u.getId().equals(user.getId()))
                .findFirst()
                .orElse(null);

        if (dbUser != null) {
            dbUser.setUsername(user.getUsername());
            dbUser.setPassword(user.getPassword());
            return dbUser;
        } else {
            user.setId(id);
            id++;
            users.add(user);
            return user;
        }
    }

    //modificare a parolei utilizatorului
    @PatchMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(@RequestParam("password") String password,
                                          @PathVariable("id") Long id) {
        var dbUser = users.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (dbUser != null) {
            dbUser.setPassword(password);
            return ResponseEntity.ok(dbUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //stergere de utilizator dupa id
    @DeleteMapping("/id/{id}")
    public void deleteId(@PathVariable("id") Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }

    //stergete de utilizator dupa username
    @DeleteMapping("/username/{username}")
    public void deleteUserName(@PathVariable("username") String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }
}


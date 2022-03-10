package ro.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.home.model.Cat;

import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/cats")
public class CatController {

    private static final List<Cat> CATS = List.of(
            new Cat(1, "Mitzi", "negru", 3),
            new Cat(2, "Misha", "alb", 2)
    );

    @GetMapping
    //http://109.101.227.234:8080/cats?ageMin=3&ageMax=2
    public List<Cat> getAll(@RequestParam(value = "ageMin", required = false) Integer ageMin,
                            @RequestParam(value = "ageMax", required = false) Integer ageMax){

        return CATS.stream()
                .filter(cat -> ageMin == null || cat.getAge() >= ageMin)
                .filter(cat -> ageMax == null || cat.getAge() <= ageMax)
                .collect(Collectors.toList());
    }

    // GET http://localhost:8871/cats/1
    // ResponseEntity - obiectul care cumuleaza datele raspunsului: content, headere, status
    @GetMapping("/{id}")
    public ResponseEntity getCat(@PathVariable("id") int id){
        var cat = CATS.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (cat != null) {
            return ResponseEntity.ok(cat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
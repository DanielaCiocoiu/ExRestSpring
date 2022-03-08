package ro.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.home.model.Cat;

import java.util.List;
import java.util.stream.Collectors;

/*
Declara o clasa Produs care are: id, nume, pret, categorie (hint: enum), stoc
Declara o lista cu niste produse predefinite.

Defineste end-pointuri prin care sa obtii:
 * toate produsele (*)
 * produsul cu un anumit id
 * valoarea totala a produselor dintr-o categorie
 * valoare stocului unui produs dupa un id datele
 * toate produsele dintr-o categorie (*)
 * toate produsele dintr-o gama de pret (*)
 * toate produsele cu stoc epuizat (*)

Defineste in Postman o colectie cu cate un request definit pentru fiecare end-point

Functionalitatile marcate cu (*) pot fi realizare intr-un singur end-point cu mai multe filtre

*/

@RestController
@RequestMapping("/cats")
public class CatController {

    private static final List<Cat> CATS = List.of(
            new Cat(1, "Mitzi", "negru", 3),
            new Cat(2, "Misha", "alb", 2)
    );

    @GetMapping
    public List<Cat> getAll(@RequestParam(value = "ageMin", required = false) Integer ageMin,
                            @RequestParam(value = "ageMax", required = false) Integer ageMax){

        return CATS.stream()
                .filter(cat -> ageMin == null || cat.getAge() >= ageMin)
                .filter(cat -> ageMax == null || cat.getAge() <= ageMax)
                .collect(Collectors.toList());
    }

    // GET http://localhost:8080/cats/1
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
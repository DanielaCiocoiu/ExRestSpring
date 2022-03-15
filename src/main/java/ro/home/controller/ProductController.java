package ro.home.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.home.model.Category;
import ro.home.model.Product;

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
@RequestMapping("/products")
public class ProductController {

    private static final List<Product> PRODUCTS = List.of(
            new Product(1, "bread", 10, Category.FOOD, 10),
            new Product(2, "oil", 11, Category.FOOD, 15),
            new Product(3, "dresses", 20, Category.TEXTILE, 0),
            new Product(4, "jeans", 25, Category.TEXTILE, 140),
            new Product(5, "sugar", 25, Category.FOOD, 120),
            new Product(6, "trousers", 25, Category.TEXTILE, 0)

    );

    //http://localhost:8871/products
    // toate produsele
    //toate produsele dintr-o categorie (*)
    //toate produsele dintr-o gama de pret (*)
    //toate produsele cu stoc epuizat (*)
    @GetMapping
    public List<Product> getAll(@RequestParam(value = "category", required = false) Category category,
                                @RequestParam(value = "priceMin", required = false) Integer priceMin,
                                @RequestParam(value = "priceMax", required = false) Integer priceMax,
                                @RequestParam(value = "stock", required = false) Boolean stock) {

        return PRODUCTS.stream()
                .filter(p -> category == null || p.getCategory().equals(category))// - fara hardcodare - aleg valoarea din Postman: ex FOOD sau TEXTILE
                .filter(p -> priceMin == null || p.getPrice() >= priceMin)
                .filter(p -> priceMax == null || p.getPrice() <= priceMax)
                .filter(p -> stock == null || stock || p.getStock() == 0)
                .collect(Collectors.toList());
    }

    // GET http://localhost:8871/products/1
    // produsul cu un anumit id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getproduct(@PathVariable("id") int id) {

        var product = PRODUCTS.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //valoarea totala a produselor dintr-o categorie - fara hardcodare
    // GET http://localhost:8871/products/sum
    @GetMapping("/sum")
    public long getSum(@RequestParam(value = "category") Category category) {
        Integer sum = PRODUCTS.stream().filter(p -> p.getCategory().equals(category))
                .map(x -> x.getPrice())
                .reduce(0, Integer::sum);
        return sum;
    }


    //valoare stocului unui produs dupa un id dat
    // GET http://localhost:8871/products/stock/1
    @GetMapping(path = "/stock/{id}")
    public int getStock(@PathVariable("id") int id) {

        Integer sum = PRODUCTS.stream().filter(p -> p.getId() == id)
                .map(p-> p.getStock())
                .reduce(0, Integer::sum);
        return sum;
    }

}

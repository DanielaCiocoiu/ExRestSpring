package ro.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string")
public class StringController {

    // GET http://localhost:8871/string/reverse?word=Annabella
    @GetMapping(path = "/reverse")
    public String reverse(@RequestParam(value = "word") String word) {

        return new StringBuilder(word).reverse().toString().toLowerCase();
    }


    // GET http://localhost:8871/string/count?word=String&word=count
    @GetMapping(path = "/count")
    public int count(@RequestParam(value = "word") String word) {

        return word.length();

    }
}

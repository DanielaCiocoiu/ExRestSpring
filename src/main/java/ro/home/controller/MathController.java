package ro.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
2.	Defineste un API (conform standardelor) care sa aiba urmatoarele functionalitati:
        a.	Calcularea sumei a 2 numere
        b.	Calcularea produsului a 2 numere
        c.	Calcularea sumei a N numere date (hint)
        d.	Calcularea numarului de caractere dintr-un string
        e.	Inversarea unui string, cu posibilitatea de a face lowercase sau uppercase string-ul returnat, daca este specificat explicit acest lucru
        */

@RestController
@RequestMapping("/math")
public class MathController {

    // GET http://localhost:8871/math/addition?num1=2&num2=25
    @GetMapping(path = "/addition")
    public int addition(@RequestParam(value = "num1") int num1, @RequestParam(value = "num2") int num2) {
        return num1 + num2;
    }

    // GET http://localhost:8871/math/multi?num1=2&num2=25
    @GetMapping(path = "/multi")
    public int multiply(@RequestParam(value = "num1") int num1, @RequestParam(value = "num2") int num2) {
        return num1 * num2;
    }


    // GET http://localhost:8871/math/sum?number=2&number=25&number=5
    @GetMapping(path = "/sum")
    public Integer sum(@RequestParam(value = "number", required = false) List<Integer> numbersList) {

        Integer sum = numbersList.stream().reduce(0, Integer::sum);
        return sum;
    }
}


package ro.home.model;

enum Category {
    TEXTILE,
    FOOD;
}

public class Product {

    //  id, nume, pret, categorie (hint: enum), stoc

    private int id;
    private String nume;
    private int price;
    private Category category;
    private int stoc;


}

package ro.home.model;

import java.util.Objects;

public class Product {

    //  id, nume, pret, categorie (hint: enum), stoc

    private int id;
    private String nume;
    private int price;
    private Category category;
    private int stock;

    public Product(int id, String nume, int price, Category category, int stock) {
        this.id = id;
        this.nume = nume;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStoc(int stoc) {
        this.stock = stock;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && stock == product.stock && Objects.equals(nume, product.nume) && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, price, category, stock);
    }

    @Override
    public String toString() {
        return "Product{" +
                " id= " + id +
                ", nume= '" + nume + '\'' +
                ", price= " + price +
                ", category= " + category +
                ", stokc= " + stock +
                '}';
    }
}

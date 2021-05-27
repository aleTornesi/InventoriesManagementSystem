package it.iisvittorioveneto.quartaB.gruppo3.inventoriesmanagementsystem;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Tag {
    private String tag;
    private final List<Product> products;

    public Tag() {this(null, new LinkedList<>());}

    public Tag(String tag, Collection<Product> products) {
        this.tag = tag;
        this.products = new LinkedList<>(products);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Product[] getProducts(){
        return this.products.toArray(Product[]::new);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}

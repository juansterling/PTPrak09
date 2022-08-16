package com.example.p_pt09_2072009.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Menu {
    @Id
    @Column(name = "idmenu")
    private int idmenu;
    @Basic
    @Column(name = "namamenu")
    private String namamenu;
    @Basic
    @Column(name = "price")
    private Double price;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_idcategory", referencedColumnName = "idcategory", nullable = false)
    private Category categoryByCategoryIdcategory;

    public int getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    public String getNamamenu() {
        return namamenu;
    }

    public void setNamamenu(String namamenu) {
        this.namamenu = namamenu;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return idmenu == menu.idmenu && Objects.equals(namamenu, menu.namamenu) && Objects.equals(price, menu.price) && Objects.equals(description, menu.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmenu, namamenu, price, description);
    }

    public Category getCategoryByCategoryIdcategory() {
        return categoryByCategoryIdcategory;
    }

    public void setCategoryByCategoryIdcategory(Category categoryByCategoryIdcategory) {
        this.categoryByCategoryIdcategory = categoryByCategoryIdcategory;
    }
}

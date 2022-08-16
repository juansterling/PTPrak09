package com.example.p_pt09_2072009.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @Column(name = "idcategory")
    private int idcategory;
    @Basic
    @Column(name = "category")
    private String category;

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category1 = (Category) o;
        return idcategory == category1.idcategory && Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcategory, category);
    }

    @Override
    public String toString() {
        return category;
    }
}

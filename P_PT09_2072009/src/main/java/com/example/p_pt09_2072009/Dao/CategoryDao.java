package com.example.p_pt09_2072009.Dao;

import com.example.p_pt09_2072009.Model.Category;
import com.example.p_pt09_2072009.Util.MyConnection;
import javafx.collections.FXCollections;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CategoryDao implements Daointerface<Category>{
    @Override
    public List<Category> getData() {
        List<Category> cList;
        cList = FXCollections.observableArrayList();
        Session s = MyConnection.getsession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Category.class);
        cq.from(Category.class);
        cList = s.createQuery(cq).getResultList();
        s.close();
        return cList;
    }

    @Override
    public void addData(Category data) {
        Session s = MyConnection.getsession();
        Transaction t  = s.beginTransaction();
        try {
            s.save(data);
            t.commit();
        }catch (Exception e){
            System.out.println(e);
            t.rollback();
        }
        s.close();
    }

    @Override
    public void deleteData(Category data) {
        Session s = MyConnection.getsession();
        Transaction t  = s.beginTransaction();
        try {
            s.delete(data);
            t.commit();
        }catch (Exception e){
            System.out.println(e);
            t.rollback();
        }
        s.close();
    }

    @Override
    public void updateData(Category data) {
        Session s = MyConnection.getsession();
        Transaction t  = s.beginTransaction();
        try {
            s.update(data);
            t.commit();
        }catch (Exception e){
            System.out.println(e);
            t.rollback();
        }
        s.close();
    }
}

package com.example.p_pt09_2072009.Dao;

import com.example.p_pt09_2072009.Model.Menu;
import com.example.p_pt09_2072009.Util.MyConnection;
import javafx.collections.FXCollections;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MenuDao implements Daointerface<Menu> {

    @Override
    public List<Menu> getData() {
        List<Menu> translist;
        translist = FXCollections.observableArrayList();
        Session s = MyConnection.getsession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Menu.class);
        cq.from(Menu.class);
        translist = s.createQuery(cq).getResultList();
        s.close();
        return translist;
    }

    @Override
    public void addData(Menu data) {
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
    public void deleteData(Menu data) {
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
    public void updateData(Menu data) {
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

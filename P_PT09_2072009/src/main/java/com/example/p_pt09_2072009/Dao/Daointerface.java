package com.example.p_pt09_2072009.Dao;

import java.util.List;

public interface Daointerface<T>{
    List<T> getData();
    void addData(T data);
    void deleteData(T data);
    void updateData(T data);
}

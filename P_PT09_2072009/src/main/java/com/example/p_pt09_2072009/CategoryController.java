package com.example.p_pt09_2072009;

import java.io.IOException;

import com.example.p_pt09_2072009.Dao.CategoryDao;
import com.example.p_pt09_2072009.Model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoryController {
    public TextField idcategory;
    public TextField namacategory;
    public Button btnSavecat;
    public TableView<Category> tabelcat;
    public TableColumn<Integer, Category> colidcat;
    public TableColumn<String, Category> colnamacat;
    ObservableList<Category> listcat;

    public CategoryController() {
    }

    public void initialize() throws IOException {
        this.ShowData();
    }

    public void ShowData() {
        CategoryDao dao = new CategoryDao();
        this.listcat = FXCollections.observableArrayList(dao.getData());
        this.tabelcat.setItems(this.listcat);
        this.colidcat.setCellValueFactory(new PropertyValueFactory("idcategory"));
        this.colnamacat.setCellValueFactory(new PropertyValueFactory("category"));
    }

    public void savecatbtn(ActionEvent actionEvent) {
        CategoryDao dao = new CategoryDao();
        if (!this.idcategory.getText().isEmpty() && !this.namacategory.getText().isEmpty()) {
            Category cat = new Category();
            cat.setIdcategory(Integer.parseInt(idcategory.getText()));
            cat.setCategory(namacategory.getText());
            dao.addData(cat);
            System.out.println(cat);
            this.ShowData();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Please Fill all the field", new ButtonType[]{ButtonType.OK});
            alert.show();
        }

    }
}
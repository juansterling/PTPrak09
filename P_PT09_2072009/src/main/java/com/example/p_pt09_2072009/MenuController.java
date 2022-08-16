package com.example.p_pt09_2072009;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.p_pt09_2072009.Dao.CategoryDao;
import com.example.p_pt09_2072009.Dao.MenuDao;
import com.example.p_pt09_2072009.Model.Category;
import com.example.p_pt09_2072009.Model.Menu;
import com.example.p_pt09_2072009.Util.MyConnection;
import com.example.p_pt09_2072009.thread.callbackinterface;
import com.example.p_pt09_2072009.thread.threadgroup;
import com.example.p_pt09_2072009.thread.threadsimple;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class MenuController {
    public TextField idmenu;
    public TextField namamenu;
    public TextField harga;
    public TextArea desc;
    public ComboBox<Category> isicategory;
    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public Button btnDelete;
    public TableView<Menu> tabelmenu;
    public TableColumn<String, Menu> colid;
    public TableColumn<String, Menu> colnama;
    public TableColumn<Float, Menu> colharga;
    public TableColumn<Category, Menu> colcategory;
    public MenuItem showcat;
    public MenuItem close;
    public MenuItem simplerep;
    public MenuItem grouprep;
    private Stage stage;
    ObservableList<Menu> listmenu= FXCollections.observableArrayList();
    ObservableList<Menu> listmenu2;
    ObservableList<Category> listcat;

    public MenuController() {
    }

    public void initialize() throws IOException {
        this.stage = new Stage();
        this.showcat.setAccelerator(KeyCombination.keyCombination("Alt+F2"));
        this.close.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        this.simplerep.setAccelerator(KeyCombination.keyCombination("Alt+S"));
        this.grouprep.setAccelerator(KeyCombination.keyCombination("Alt+G"));
        CategoryDao cDao = new CategoryDao();
        this.listcat = FXCollections.observableArrayList(cDao.getData());
        this.isicategory.setItems(this.listcat);
        this.ShowData();
        this.btnUpdate.setDisable(true);
        this.btnDelete.setDisable(true);
    }

    public void showcat(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Category-View.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 600.0, 300.0);
        CategoryController ctgController = (CategoryController)fxmlLoader.getController();
        this.stage.setTitle("Category Management");
        this.stage.setScene(scene);
        this.stage.showAndWait();
    }

    public void close(ActionEvent actionEvent) {
        this.idmenu.getScene().getWindow().hide();
    }

    public void ShowData() {
        MenuDao dao = new MenuDao();
        this.listmenu.clear();
        this.listmenu = FXCollections.observableArrayList(dao.getData());
        this.tabelmenu.setItems(this.listmenu);
        this.colid.setCellValueFactory(new PropertyValueFactory("idmenu"));
        this.colnama.setCellValueFactory(new PropertyValueFactory("namamenu"));
        this.colharga.setCellValueFactory(new PropertyValueFactory("price"));
        this.colcategory.setCellValueFactory(new PropertyValueFactory("categoryByCategoryIdcategory"));

    }

    public void savebtn(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        if (this.idmenu.getText() != null && this.namamenu.getText() != null && this.harga.getText() != null && this.desc.getText() != null && this.isicategory.getValue() != null) {
            Menu m = new Menu();
            m.setIdmenu(Integer.parseInt(this.idmenu.getText()));
            m.setNamamenu(this.namamenu.getText());
            m.setDescription(this.desc.getText());
            m.setPrice(Double.parseDouble(this.harga.getText()));
            m.setCategoryByCategoryIdcategory(this.isicategory.getValue());
            dao.addData(m);
            this.ShowData();
            this.resetbtn();
        } else {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Please Fill all the field", new ButtonType[]{ButtonType.OK});
            alert.showAndWait();
        }

    }
    public void klikcategory() {
        CategoryDao cDao = new CategoryDao();
        this.listcat = FXCollections.observableArrayList(cDao.getData());
        this.isicategory.setItems(this.listcat);
    }
    public void resetbtn() {
        this.idmenu.clear();
        this.namamenu.clear();
        this.harga.clear();
        this.desc.clear();
        this.isicategory.getSelectionModel().select(-1);
        idmenu.setDisable(false);
    }


    public void updatebtn(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        Menu m = new Menu();
        m.setIdmenu(Integer.parseInt(this.idmenu.getText()));
        m.setNamamenu(this.namamenu.getText());
        m.setDescription(this.desc.getText());
        m.setPrice(Double.parseDouble(this.harga.getText()));
        m.setCategoryByCategoryIdcategory(this.isicategory.getValue());
        dao.updateData(m);
        this.ShowData();
        this.resetbtn();

        Alert alert = new Alert(AlertType.CONFIRMATION, "Berhasil update data", new ButtonType[]{ButtonType.OK});
        alert.showAndWait();
    }
    public void dataterpilih() {
        this.btnUpdate.setDisable(false);
        this.btnDelete.setDisable(false);
        tabelmenu.getSelectionModel().getSelectedItems();
        listmenu2 = tabelmenu.getSelectionModel().getSelectedItems();
        idmenu.setText(String.valueOf(tabelmenu.getSelectionModel().getSelectedItem().getIdmenu()));
        namamenu.setText(tabelmenu.getSelectionModel().getSelectedItem().getNamamenu());
        harga.setText(String.valueOf(tabelmenu.getSelectionModel().getSelectedItem().getPrice()));
        desc.setText(tabelmenu.getSelectionModel().getSelectedItem().getDescription());
        isicategory.setValue(tabelmenu.getSelectionModel().getSelectedItem().getCategoryByCategoryIdcategory());
        idmenu.setDisable(true);

    }
    public void deletebtn(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        ObservableList<Menu> listmenu2;
        listmenu2 = tabelmenu.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            for (Menu p: listmenu2) {
                dao.deleteData(p);
            }
        }
        ShowData();
    }
    public void showsimplerep(){
        threadsimple s1 = new threadsimple(new callbackinterface() {
            @Override
            public void oncomplete() {
                System.out.println("thread berhasil");
            }
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(s1);
        executorService.shutdown();

    }
    public void showgrouprep(){
        threadgroup s1 = new threadgroup(new callbackinterface() {
            @Override
            public void oncomplete() {
                System.out.println("thread berhasil");
            }
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(s1);
        executorService.shutdown();

    }
}

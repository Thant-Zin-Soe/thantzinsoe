/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.system.databaseHandler.Database;
import library.system.model.Book;
import library.system.model.Member;
import library.system.model.MemberDAO;

/**
 * FXML Controller class
 *
 * @author thantzinsoe
 */
public class MemberListController implements Initializable {

    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> idColumn;
    @FXML
    private TableColumn<Member, String> nameColumn;
    @FXML
    private TableColumn<Member, String> mobileColumn;
    @FXML
    private TableColumn<Member, String> addressColumn;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button deleteBtn;
    private  MemberDAO memberDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        memberDAO=new MemberDAO();
        loadTableData();
        intitColumn();
    }

    private void intitColumn() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobileColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

    }

    private void loadTableData() {
        ObservableList<Member> memberList = memberDAO.loadMemberTableData();
        memberTable.getItems().addAll(memberList);
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        Member member = memberTable.getSelectionModel().getSelectedItem();
        if (memberDAO.deleteMemebr(member.getId())) {
            memberTable.getItems().clear();
            loadTableData();
        }
    }
}

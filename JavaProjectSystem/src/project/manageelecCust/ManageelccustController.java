package project.manageelecCust;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.Electricity;
import project.Main;
import project.addcustomer.AddcustomerController;

public class ManageelccustController {

private boolean isLoad=false;
@FXML
private TableView<Electricity> electable;
@FXML
private TableColumn<Electricity, String> fName;
@FXML
private TableColumn<Electricity, String> lName;
@FXML
private TableColumn<Electricity, String> email;
@FXML
private TableColumn<Electricity, String> gender;
@FXML
private TableColumn<Electricity, String> startDate;
@FXML
private TableColumn<Electricity, String> endDate;
@FXML
private TableColumn<Electricity, String> utilityCost;
@FXML
private TableColumn<Electricity, String> manage;
@FXML
private Label lb_message;

ObservableList<Electricity> elecList =FXCollections.observableArrayList();

@FXML
private void loadData() throws SQLException {
	elecList.clear();
	ResultSet rs=Connector.getData("select *from eleccust");
	while(rs.next()) {
		elecList.add(new Electricity(rs.getString("firstName"),
				rs.getString("lastName"),
				rs.getString("email"),
				rs.getString("sex"),
				rs.getString("startDate"),
				rs.getString("endDate"),
				rs.getString("elctdept")
				));
		electable.setItems(elecList);
		lb_message.setText("Loading succesfull!");
		isLoad=true;
	}
	if(isLoad==false) {
		lb_message.setText("Unable to load customer data:");
	}
}
@FXML
private  void initialize() {
	fName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	lName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	email.setCellValueFactory(new PropertyValueFactory<>("email"));
	gender.setCellValueFactory(new PropertyValueFactory<>("sex"));
	startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
	endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
	utilityCost.setCellValueFactory(new PropertyValueFactory<>("elctdept"));
    Callback<TableColumn<Electricity,String>,TableCell<Electricity,String>> cellFoctory = (param) -> {
		
	 final TableCell<Electricity, String> cell=new TableCell<Electricity,String>(){
		 @Override
		 public  void updateItem(String item,boolean empty) {
			 super.updateItem(item,empty);
			 if(empty) {
				 setGraphic(null);
				 setText(null);
			 }else {
				 final Button btnupdate=new Button("Update");
				 btnupdate.setStyle(
						"-fx-background-color:#E65100;"+
				        "-fx-border-radius:5px;"+
						"-fx-text-color:white;"
						 );
				 btnupdate.setOnAction(event ->{

				 });
				final Button btn_delete=new Button("Delete");
				btn_delete.setStyle(
						"-fx-background-color:#E53935;"+
						"-fx-border-radius:5px;"+
						"-fx-text-color:white;"
						);
				btn_delete.setOnAction(event ->{
					Electricity elc=electable.getSelectionModel().getSelectedItem();
				Connector.updateData("delete from eleccust where email='"+elc.getEmail()+"'");
				
				try {
					loadData();
					lb_message.setText("Succesfully deleted!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				});
                HBox managebtn = new HBox(btnupdate, btn_delete);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(btnupdate, new Insets(1, 1, 0, 1));
                HBox.setMargin(btn_delete, new Insets(1, 1, 0, 1));

                setGraphic(managebtn);

                setText(null);
			 }
		 };
		 
	 };
	 return cell;
};
manage.setCellFactory(cellFoctory);
	
}
}

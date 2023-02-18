package project.login;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.Main;

public class LoginController {
	ObservableList<String > serviceList =FXCollections.observableArrayList("Customer","Electricity Officer","Water Service Officer","Telecom Officer");
private Main main;
@FXML
private Label lb_message;
@FXML
private TextField txt_luserName;
@FXML
private TextField txt_lpassword;
@FXML
private ChoiceBox lchoice;
@FXML
private void backHome() throws IOException {
	main.showHome();
}
@FXML
private void goToServicePage() throws IOException, SQLException {
	if(txt_luserName.getText().equals("")) {
		lb_message.setText("Email is required!");
	}else if(txt_lpassword.getText().equals("")) {
		lb_message.setText("Password required!");
	}else if(lchoice.getValue().equals("")) {
		lb_message.setText("Usertype required!");
	}else {
	ResultSet rs=Connector.getData("select* from userdata where username='"+txt_luserName.getText()+"'and usertype='"+lchoice.getValue()+"'");
	if(lchoice.getValue().equals("Customer")) {
		if(rs.next()) {
		main.showCustmenuloginPage();
		}else {
			lb_message.setText("You are not registerd as a Customer!");
		}
	}else if(lchoice.getValue().equals("Electricity Officer")) {
		if(rs.next()) {
			main.showElectricityPage();
		}else {
			lb_message.setText("You are not registerd as Electricity Officer!");
		}
	}else if(lchoice.getValue().equals("Water Service Officer")){
		if(rs.next()) {
			main.showWaterPage();
		}else {
			lb_message.setText("You are not registerd as Water Service Officer!");
		}
	}else if(lchoice.getValue().equals("Telecom Officer")){
		if(rs.next()) {
			main.showTelecomPage();
		}else {
			lb_message.setText("You are not registerd as Telecom Officer!");
		}		
	}else {
		lb_message.setText("Logged in succesfull!");
	}
	}
}
@FXML
private void initialize() {
	
	lchoice.setValue("Customer");
	lchoice.setItems(serviceList);
	
}
}

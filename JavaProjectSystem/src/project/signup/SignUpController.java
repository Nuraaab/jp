package project.signup;

import java.io.IOException;

import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import project.Main;

public class SignUpController {
	ObservableList<String > serviceList =FXCollections.observableArrayList("Customer","Electricity Officer","Water Service Officer","Telecom Officer");
	private Main main;
	@FXML
	private TextField txt_userName;
	@FXML
	private TextField txt_password;
	@FXML 
    ChoiceBox choice;

	
	@FXML
	private void backHomePage() throws IOException {
		main.showHome();
	}
	
	@FXML
	private void goToService() throws IOException {
		if(choice.getValue().equals("Customer")) {
			Connector.updateData("insert into userdata(username,password,usertype) values('"+txt_userName.getText()+"','"+txt_password.getText()+"','"+choice.getValue()+"')");
			main.showCustmenuPage();
		}else if(choice.getValue().equals("Electricity Officer")) {
			Connector.updateData("insert into userdata(username,password,usertype) values('"+txt_userName.getText()+"','"+txt_password.getText()+"','"+choice.getValue()+"')");
			main.showElectricityPage();
		}else if(choice.getValue().equals("Water Service Officer")) {
         Connector.updateData("insert into userdata(username,password,usertype) values('"+txt_userName.getText()+"','"+txt_password.getText()+"','"+choice.getValue()+"')");
			main.showWaterPage();
		}else if(choice.getValue().equals("Telecom Officer")) {
			Connector.updateData("insert into userdata(username,password,usertype) values('"+txt_userName.getText()+"','"+txt_password.getText()+"','"+choice.getValue()+"')");
			main.showTelecomPage();
		}else {
			System.out.println("error");
		}
	}
	
@FXML
private void initialize() {
	
	choice.setValue("Customer");
	choice.setItems(serviceList);
	
}
}

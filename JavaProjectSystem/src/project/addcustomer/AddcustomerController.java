package project.addcustomer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import project.Main;

public class AddcustomerController {
	private static Main main;
@FXML
private TextField eaddFname;
@FXML
private TextField eaddLname;
@FXML
private TextField eaddEmail;
@FXML
private ComboBox<String> eaddGender;
@FXML
private TextField eaddCity;
@FXML
private TextField eaddKebele;
@FXML
private TextField eaddhouseNumber;
@FXML
private DatePicker eaddstartDate;
@FXML
private DatePicker eaddendDate;
@FXML
private TextField eaddAmount;
@FXML
private Label addLabel;
ObservableList<String> genderList=FXCollections.observableArrayList("Male","Female");
//methodes
public void setTextField(String fname,String lname,String email,String gender,String city,String kebele,String housen,String amount) {
	eaddFname.setText(fname);
	eaddLname.setText(lname);
	eaddEmail.setText(email);
	eaddGender.setValue(gender);
	eaddCity.setText(city);
	eaddKebele.setText(kebele);
	eaddhouseNumber.setText(housen);
	eaddAmount.setText(amount);
}
@FXML
private void addData() throws SQLException {
	ResultSet res=Connector.getData("select * from eleccust where email='"+eaddEmail.getText()+"'");
	while(res.next()==false) {
		Connector.updateData("insert into eleccust(firstName,lastName,email,age,sex,woreda,kebele,houseNumber,elctdept,startDate,endDate) values('"+eaddFname.getText()+"','"+eaddLname.getText()+"','"+eaddEmail.getText()+"','21','"+eaddGender.getValue()+"','"+eaddCity.getText()+"','"+eaddKebele.getText()+"','"+eaddhouseNumber.getText()+"','"+eaddAmount.getText()+"','"+eaddstartDate.getValue()+"','"+eaddendDate.getValue()+"')");
	    break;
	}
	ResultSet res1=Connector.getData("select * from eleccust where email='"+eaddEmail.getText()+"'");
	while(res1.next()==true) {
		Connector.updateData("update eleccust set elctdept='"+eaddAmount.getText()+"',startDate='"+eaddstartDate.getValue()+"',endDate='"+eaddendDate.getValue()+"' where email='"+eaddEmail.getText()+"'");
	break;
	}
	addLabel.setText("Register succesfull:");
}
@FXML
private void cancle() throws IOException {
		main.showElectricityPage();
}

@FXML
private void initialize() {
	eaddGender.setValue("Male");
	eaddGender.setItems(genderList);
}
}

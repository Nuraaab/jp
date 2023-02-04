package project.custmenu;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.util.Callback;
import apiEntity.Account;
import apiRequest.APIClient;
import apiRequest.AccountAPI;
import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Electricity;
import model.Telecom;
import model.Water;
import project.Main;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustmenuController {
ObservableList<String> genderList=FXCollections.observableArrayList("Male","Female");
ObservableList<String> bankList =FXCollections.observableArrayList("Commercial Bank Of Ethiopia","Dashen Bank","Abysinia Bank","Wegagen Bank","Nib International Bank");
ObservableList<Electricity> elecList =FXCollections.observableArrayList();
ObservableList<Water> waterList =FXCollections.observableArrayList();
ObservableList<Telecom> teleList =FXCollections.observableArrayList();

@FXML
private Label message;
@FXML
private TableView<Electricity> elecTable;
@FXML
private TableColumn<Electricity, Integer> rowId;
@FXML
private TableColumn<Electricity, String> rowFirstName;
@FXML
private TableColumn<Electricity, String> rowLastName;
@FXML
private TableColumn<Electricity, String> rowEmail;
@FXML
private TableColumn<Electricity, String> rowStartDate;
@FXML
private TableColumn<Electricity, String> rowEndDate;
@FXML
private TableColumn<Electricity, String> rowAmmount;
@FXML
private TableColumn<Electricity, String> rowPayment;

@FXML
private TableView<Water> waterTable;
@FXML
private TableColumn<Water, Integer> wrowId;
@FXML
private TableColumn<Water, String> wrowFirstName;
@FXML
private TableColumn<Water, String> wrowLastName;
@FXML
private TableColumn<Water, String> wrowEmail;
@FXML
private TableColumn<Water, String> wrowStartDate;
@FXML
private TableColumn<Water, String> wrowEndDate;
@FXML
private TableColumn<Water, String> wrowAmmount;
@FXML
private TableColumn<Water, String> wowPayment;
@FXML
private TableView<Telecom> teleTable;
@FXML
private TableColumn<Telecom, Integer> trowId;
@FXML
private TableColumn<Telecom, String> trowFirstName;
@FXML
private TableColumn<Telecom, String> trowLastName;
@FXML
private TableColumn<Telecom, String> trowEmail;
@FXML
private TableColumn<Telecom, String> trowStartDate;
@FXML
private TableColumn<Telecom, String> trowEndDate;
@FXML
private TableColumn<Telecom, String> trowAmmount;
@FXML
private TableColumn<Telecom, String> trowPayment;
//for payment page
@FXML
private Label txt_pFname;
@FXML
private Label txt_pLname;
@FXML
private Label txt_pEmail;
@FXML
private Label txt_pCity;
@FXML
private Label txt_pkebele;
@FXML
private Label txt_phouseNumber;
@FXML
private Label txt_pAmount;
@FXML
private Label txt_pstartDate;
@FXML
private Label txt_pendDate;
@FXML
private Label lb_pFname;
@FXML
private Label lb_pLname;
@FXML
private Label lb_pEmail;
@FXML
private Label lb_pCity;
@FXML
private Label lb_pkebele;
@FXML
private Label lb_phouseNumber;
@FXML
private Label lb_pAmount;
@FXML
private Label lb_pstartDate;
@FXML
private Label lb_pendDate;
@FXML
private Label title;

//for payment page
@FXML
private TextField firstName;
@FXML
private TextField lastName;
@FXML
private TextField emailAddress;
@FXML
private TextField age;
@FXML
private ComboBox<String> Gender;
@FXML
private TextField city;
@FXML
private TextField kebele;
@FXML
private TextField  houseNumber;
@FXML
private Label validationMessage;
@FXML
private Label messageProfile;

//for api
@FXML
private TextField fullName;
@FXML
private TextField accountNumber;
@FXML
private TextField amount;
@FXML
private DatePicker date;
@FXML
private Label paymentMessage;
@FXML
private Label service;

//for api
//used as label for display
@FXML
private Label txt_fname;
@FXML
private Label txt_lname;
@FXML
private Label txt_email;
@FXML
private Label txt_age;
@FXML
private Label txt_sex;
@FXML
private Label txt_city;
@FXML
private Label txt_kebele;
@FXML
private Label txt_housenumber;

//for displaying profile

@FXML
private Label lbFname;
@FXML
private Label lbLname;
@FXML
private Label lbEmail;
@FXML
private Label lbAge;
@FXML
private Label lbSex;
@FXML
private Label lbCity;
@FXML
private Label lbKebele;
@FXML
private Label lbHouseNumber;
//for the tabPane
@FXML
private Tab information;
@FXML
private Tab profile;
@FXML
private Tab home;
@FXML
private Tab electricity;
@FXML
private Tab water;
@FXML
private Tab telecom;
@FXML
private TabPane tp;
private Main main;
private Boolean istrue=false;
@FXML
private Tab payement;
@FXML
private Tab paymentinfo;
@FXML
private void electricityUtility() {
	tp.getSelectionModel().select(electricity);
}
@FXML
private void waterUtility() {
	tp.getSelectionModel().select(water);
}
@FXML
private void teleUtility() {
	tp.getSelectionModel().select(telecom);
}
@FXML
private void profile() {
	tp.getSelectionModel().select(profile);
}
@FXML
public void displayElecData() throws SQLException {
Electricity	elecData=elecTable.getSelectionModel().getSelectedItem();
ResultSet res=Connector.getData("select * from eleccust where id='"+elecData.getId()+"'");
while(res.next()) {
	title.setText("Pay for your electric utility");
	txt_pFname.setText("First name:");
	lb_pFname.setText(res.getString("firstName"));
	txt_pLname.setText("Last Name:");
	lb_pLname.setText(res.getString("lastName"));
	txt_pEmail.setText("Email address:");
	lb_pEmail.setText(res.getString("email"));
	txt_pCity.setText("City/Wereda:");
	lb_pCity.setText(res.getString("woreda"));
	txt_pkebele.setText("Kebele:");
	lb_pkebele.setText(res.getString("kebele"));
	txt_phouseNumber.setText("House number:");
	lb_phouseNumber.setText(res.getString("houseNumber"));
	txt_pAmount.setText("Utility cost:");
	lb_pAmount.setText(res.getString("elctdept"));
	txt_pstartDate.setText("Utility start date:");
	lb_pstartDate.setText(res.getString("startDate"));
	txt_pendDate.setText("Utility end date:");
	lb_pendDate.setText(res.getString("endDate"));
	service.setText("electric");
}
}
@FXML
public void displayWaterData() throws SQLException {
Water waterData=waterTable.getSelectionModel().getSelectedItem();
ResultSet rws=Connector.getData("select * from watercust where id='"+waterData.getId()+"'");
while(rws.next()) {
	title.setText("Pay for your water utility");
	txt_pFname.setText("First name:");
	lb_pFname.setText(rws.getString("firstName"));
	txt_pLname.setText("Last Name:");
	lb_pLname.setText(rws.getString("lastName"));
	txt_pEmail.setText("Email address:");
	lb_pEmail.setText(rws.getString("email"));
	txt_pCity.setText("City/Wereda:");
	lb_pCity.setText(rws.getString("woreda"));
	txt_pkebele.setText("Kebele:");
	lb_pkebele.setText(rws.getString("kebele"));
	txt_phouseNumber.setText("House number:");
	lb_phouseNumber.setText(rws.getString("houseNumber"));
	txt_pAmount.setText("Utility cost:");
	lb_pAmount.setText(rws.getString("waterDept"));
	txt_pstartDate.setText("Utility start date:");
	lb_pstartDate.setText(rws.getString("startDate"));
	txt_pendDate.setText("Utility end date:");
	lb_pendDate.setText(rws.getString("endDate"));
	service.setText("water");
}
}
@FXML
public void dispalyTeleData() throws SQLException {
Telecom	teleData=teleTable.getSelectionModel().getSelectedItem();
ResultSet rts=Connector.getData("select * from telecust where id='"+teleData.getId()+"'");
while(rts.next()) {
	title.setText("Pay for your telecom utility");
	txt_pFname.setText("First name:");
	lb_pFname.setText(rts.getString("firstName"));
	txt_pLname.setText("Last Name:");
	lb_pLname.setText(rts.getString("lastName"));
	txt_pEmail.setText("Email address:");
	lb_pEmail.setText(rts.getString("email"));
	txt_pCity.setText("City/Wereda:");
	lb_pCity.setText(rts.getString("woreda"));
	txt_pkebele.setText("Kebele:");
	lb_pkebele.setText(rts.getString("kebele"));
	txt_phouseNumber.setText("House number:");
	lb_phouseNumber.setText(rts.getString("houseNnumber"));
	txt_pAmount.setText("Utility cost:");
	lb_pAmount.setText(rts.getString("teleDept"));
	txt_pstartDate.setText("Utility start date:");
	lb_pstartDate.setText(rts.getString("startDate"));
	txt_pendDate.setText("Utility end date:");
	lb_pendDate.setText(rts.getString("endDate"));
	service.setText("telecom");
}
}
@FXML
private void payment() {
		try {
	int userAccountNumber=Integer.valueOf(accountNumber.getText());
	AccountAPI accountAPI=APIClient.getClient().create(AccountAPI.class);
	accountAPI.findById(userAccountNumber).enqueue(new retrofit2.Callback<Account> (){

		@Override
		public void onFailure(Call<Account> arg0, Throwable t) {
			t.printStackTrace();
			
		}

		@Override
		public void onResponse(Call<Account> arg0, Response<Account> response) {
			if(response.isSuccessful()) {
				Account acc =response.body();
				int bankAccountNumber=acc.getAccount_number();
				int bank_balance=acc.getBalance();
				String bankHolderName=acc.getHolder_name();
				String bankName=acc.getBank_name();
				int userBalance=Integer.valueOf(amount.getText());
				int payedBalance=bank_balance-userBalance;
				if(payedBalance>0) {
					acc.setBalance(payedBalance);
					accountAPI.updateAccount(acc).enqueue(new retrofit2.Callback<Void> () {

						@Override
						public void onFailure(Call<Void> arg0, Throwable t) {
							
							t.printStackTrace();
						}

						@Override
						public void onResponse(Call<Void> arg0, Response<Void> response) {
						if(response.isSuccessful()) {
							if(service.getText().equals("electric")) {
								
										try {
											accountAPI.findById(1000500).enqueue(new retrofit2.Callback<Account> () {

												@Override
												public void onFailure(Call<Account> arg0, Throwable t) {
													t.printStackTrace();
												}

												@Override
												public void onResponse(Call<Account> arg0, Response<Account> response) {
													if(response.isSuccessful()) {
														Account account=response.body();
														int userbalance=Integer.valueOf(amount.getText());
														int bankbalance=account.getBalance();
														int holderBalance=userbalance+bankbalance;
														account.setBalance(holderBalance);
														accountAPI.updateAccount(account).enqueue(new retrofit2.Callback<Void> () {

															@Override
															public void onFailure(Call<Void> arg0, Throwable t) {
																t.printStackTrace();
															}

															@Override
															public void onResponse(Call<Void> call, Response<Void> response) {
																try {
																		int value=Integer.valueOf(lb_pAmount.getText());
																		int updatedData=value-userbalance;
																		String strValue=String.valueOf(updatedData);
																		Connector.updateData("update eleccust set elctdept='"+updatedData+"' where email='"+emailAddress.getText()+"'");
																} catch (Exception e) {
																	e.printStackTrace();
																} 
																
															}
															
														});
													}
												}
												
											});
											}catch(Exception e) {
												e.printStackTrace();
											}


								
							}else if(service.getText().equals("water")) {
										try {
											accountAPI.findById(1841000).enqueue(new retrofit2.Callback<Account> () {

												@Override
												public void onFailure(Call<Account> arg0, Throwable t) {
													t.printStackTrace();
												}

												@Override
												public void onResponse(Call<Account> arg0, Response<Account> response) {
													if(response.isSuccessful()) {
														Account waccount=response.body();
														int userbalance=Integer.valueOf(amount.getText());
														int bankbalance=waccount.getBalance();
														int holderBalance=userbalance+bankbalance;
														waccount.setBalance(holderBalance);
														accountAPI.updateAccount(waccount).enqueue(new retrofit2.Callback<Void> () {

															@Override
															public void onFailure(Call<Void> arg0, Throwable t) {
																t.printStackTrace();
															}

															@Override
															public void onResponse(Call<Void> call, Response<Void> response) {
																try {
																	int wvalue=Integer.valueOf(lb_pAmount.getText());
																	int wupdatedData=wvalue-userbalance;
																	Connector.updateData("update watercust set waterDept='"+wupdatedData+"' where email='"+emailAddress.getText()+"'");
															} catch (Exception e) {
																e.printStackTrace();
															} 	
															}
															
														});
													}
												}
												
											});
											}catch(Exception e) {
												e.printStackTrace();
											}
							}else if(service.getText().equals("telecom")) {
								try {
											accountAPI.findById(2562200).enqueue(new retrofit2.Callback<Account> () {

												@Override
												public void onFailure(Call<Account> arg0, Throwable t) {
													t.printStackTrace();
												}

												@Override
												public void onResponse(Call<Account> arg0, Response<Account> response) {
													if(response.isSuccessful()) {
														Account taccount =response.body();
														int userbalance=Integer.valueOf(amount.getText());
														int bankbalance=taccount.getBalance();
														int holderBalance=userbalance+bankbalance;
														taccount.setBalance(holderBalance);
														accountAPI.updateAccount(taccount).enqueue(new retrofit2.Callback<Void> () {

															@Override
															public void onFailure(Call<Void> arg0, Throwable t) {
																t.printStackTrace();
															}

															@Override
															public void onResponse(Call<Void> call, Response<Void> response) {
																try {
																	int tvalue=Integer.valueOf(lb_pAmount.getText());
																	int tupdatedData=tvalue-userbalance;
																	Connector.updateData("update telecust set teleDept='"+tupdatedData+"' where email='"+emailAddress.getText()+"'");
															} catch (Exception e) {
																e.printStackTrace();
															} 	
															}
															
														});
													}
												}
												
											});
											}catch(Exception e) {
												e.printStackTrace();
											}
							}else {
								System.out.println("payment error");
							}
						}
							
						}
						
					});
				}
			}
			
		}
		
	});
		}catch(Exception e) {
			e.printStackTrace();
		}

	tp.getSelectionModel().select(paymentinfo);	
}
@FXML
private void doCustomerMenu() throws SQLException{
	//update data to database
	if(firstName.getText().equals("")) {
		validationMessage.setText("first name required!");
	}else if(lastName.getText().equals("")) {
		validationMessage.setText("last name required!");
	}else if(emailAddress.getText().equals("")) {
		validationMessage.setText("email required!");
	}else if(age.getText().equals("")) {
		validationMessage.setText("age required!");
	}else if(Gender.getValue().equals("")) {
		validationMessage.setText("gender required!");
	}else if(city.getText().equals("")) {
		validationMessage.setText("city required!");
	}else if(kebele.getText().equals("")) {
		validationMessage.setText("kebele required!");
	}else if(houseNumber.getText().equals("")) {
		validationMessage.setText("house number required!");
	}else {
	
	String displayMessage="";
	ResultSet ers=Connector.getData("select * from eleccust where email='"+emailAddress.getText()+"'");
	while(ers.next()==false) {
	Connector.updateData("insert into eleccust(firstName,lastName,email,age,sex,woreda,kebele,houseNumber,elctDept,startDate,endDate) values('"+firstName.getText()+"','"+lastName.getText()+"','"+emailAddress.getText()+"','"+age.getText()+"','"+Gender.getValue()+"','"+city.getText()+"','"+kebele.getText()+"','"+houseNumber.getText()+"',0,'','')");
	displayMessage=displayMessage+"electricity utility, ";
	break;
	}
	ResultSet wrs=Connector.getData("select * from watercust where email='"+emailAddress.getText()+"'");
	while(wrs.next()==false) {
		Connector.updateData("insert into watercust(firstName,lastName,email,age,sex,woreda,kebele,houseNumber,waterDept,startDate,endDate) values('"+firstName.getText()+"','"+lastName.getText()+"','"+emailAddress.getText()+"','"+age.getText()+"','"+Gender.getValue()+"','"+city.getText()+"','"+kebele.getText()+"','"+houseNumber.getText()+"',0,'','')");
		displayMessage=displayMessage+"water utility, ";
		break;
	}
	ResultSet trs=Connector.getData("select * from telecust where email='"+emailAddress.getText()+"'");
	while(trs.next()==false) {
    Connector.updateData("insert into telecust(firstName,lastName,email,age,sex,woreda,kebele,houseNumber,teleDept,startDate,endDate) values('"+firstName.getText()+"','"+lastName.getText()+"','"+emailAddress.getText()+"','"+age.getText()+"','"+Gender.getValue()+"','"+city.getText()+"','"+kebele.getText()+"','"+houseNumber.getText()+"',0,'','')");
    displayMessage=displayMessage+"telecom utility";
    break;
	}

	istrue=true;
	//display on page
	txt_fname.setText("First Name");
	lbFname.setText(firstName.getText());
	
	txt_lname.setText("Last Name");
	lbLname.setText(lastName.getText());
	
	txt_email.setText("Email Address");
	lbEmail.setText(emailAddress.getText());
	
	
	txt_age.setText("Age");
	lbAge.setText(age.getText());
	
	txt_sex.setText("Gender");
	lbSex.setText(Gender.getValue());
	
	txt_city.setText("Woreda/City");
	lbCity.setText(city.getText());
	
	txt_kebele.setText("Kebele");
	lbKebele.setText(kebele.getText());	
	
	txt_housenumber.setText("House Number");
	lbHouseNumber.setText(houseNumber.getText());	
	messageProfile.setText("You are registered as "+displayMessage+"customer");

	information.setDisable(true);
	profile.setDisable(false);
	home.setDisable(false);
	electricity.setDisable(false);
	water.setDisable(false);
	telecom.setDisable(false);
	tp.getSelectionModel().select(profile);
	
	elecList.clear();
	ResultSet rs=Connector.getData("select id,firstName,lastName,email,elctdept,startDate,endDate from eleccust where email='"+emailAddress.getText()+"' ");
	while(rs.next()) {
		elecList.add(new Electricity(rs.getInt("id"),
				rs.getString("firstName"),
				rs.getString("lastName"),
				rs.getString("email"),
				rs.getString("elctdept"),
				rs.getString("startDate"),
				rs.getString("endDate")
				
				));
		elecTable.setItems(elecList);
	}
	
	waterList.clear();
	ResultSet rsW=Connector.getData("select id,firstName,lastName,email,waterDept,startDate,endDate from watercust where email='"+emailAddress.getText()+"' ");
	while(rsW.next()) {
		waterList.add(new Water(rsW.getInt("id"), 
				rsW.getString("firstName"),
				rsW.getString("lastName"),
				rsW.getString("email"),
				rsW.getString("waterDept"),
				rsW.getString("startDate"),
				rsW.getString("endDate")));
		
		waterTable.setItems(waterList);
	}
	teleList.clear();
	ResultSet rsT=Connector.getData("select id,firstName,lastName,email,teleDept,startDate,endDate from telecust where email='"+emailAddress.getText()+"' ");
	while(rsT.next()) {
		teleList.add(new Telecom(rsT.getInt("id"),
				rsT.getString("firstName"),
				rsT.getString("lastName"),
				rsT.getString("email"),
				rsT.getString("teleDept"),
				rsT.getString("startDate"),
				rsT.getString("endDate")));
		teleTable.setItems(teleList);
	}
	}
}
@FXML
private void gopaymentInfo() {
	if(Integer.parseInt(lb_pAmount.getText())>0) {
	tp.getSelectionModel().select(paymentinfo);
	}else {
		paymentMessage.setText("Your utility cost is 0.00Birr");
	}
}


//

@FXML
private void initialize() {
	Gender.setValue("Male");
	Gender.setItems(genderList);
	
	rowId.setCellValueFactory(new PropertyValueFactory<>("id"));
	rowFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	rowLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	rowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	rowStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
	rowEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
	rowAmmount.setCellValueFactory(new PropertyValueFactory<>("elctdept"));
	
    Callback<TableColumn<Electricity,String>,TableCell<Electricity,String>> cellEFoctory = (param) -> {
		
	 final TableCell<Electricity, String> cell=new TableCell<Electricity,String>(){
		 @Override
		 public void updateItem(String item,boolean empty) {
			 super.updateItem(item,empty);
			 if(empty) {
				 setGraphic(null);
				 setText(null);
			 }else {
				 final Button paybtn=new Button("See Detail");
				 paybtn.setStyle(
						"-fx-background-color:green;"
						 );
				 paybtn.setOnAction(event ->{
					 try {
						displayElecData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					 tp.getSelectionModel().select(payement);
				 });
				 setGraphic(paybtn);
			 }
		 };
		 
	 };
	 return cell;
};

rowPayment.setCellFactory(cellEFoctory);
	
	///water
	wrowId.setCellValueFactory(new PropertyValueFactory<>("id"));
	wrowFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	wrowLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	wrowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	wrowStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
	wrowEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
	wrowAmmount.setCellValueFactory(new PropertyValueFactory<>("waterDept"));
	     Callback<TableColumn<Water,String>,TableCell<Water,String>> cellFoctory = (param) -> {
		
		 final TableCell<Water, String> cell=new TableCell<Water,String>(){
			 @Override
			 public void updateItem(String item,boolean empty) {
				 super.updateItem(item,empty);
				 if(empty) {
					 setGraphic(null);
					 setText(null);
				 }else {
					 final Button paybtn=new Button("See Detail");
					 paybtn.setStyle(
							"-fx-background-color:green;"
							 );
					 paybtn.setOnAction(event ->{
						 try {
							displayWaterData();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						 tp.getSelectionModel().select(payement);
					 });
					 setGraphic(paybtn);
				 }
			 };
			 
		 };
		 return cell;
	 };

	 wowPayment.setCellFactory(cellFoctory);
	//telecom
	trowId.setCellValueFactory(new PropertyValueFactory<>("id"));
	trowFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	trowLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	trowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	trowStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
	trowEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
	trowAmmount.setCellValueFactory(new PropertyValueFactory<>("teleDept"));
	//telecom	
    Callback<TableColumn<Telecom,String>,TableCell<Telecom,String>> cellTFoctory = (param) -> {
		
	 final TableCell<Telecom, String> cell=new TableCell<Telecom,String>(){
		 @Override
		 public void updateItem(String item,boolean empty) {
			 super.updateItem(item,empty);
			 if(empty) {
				 setGraphic(null);
				 setText(null);
			 }else {
				 final Button paybtn=new Button("See Detail");
				 paybtn.setStyle(
						"-fx-background-color:green;"
						 );
				 paybtn.setOnAction(event ->{
					 try {
						dispalyTeleData();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					 tp.getSelectionModel().select(payement);
				 });
				 setGraphic(paybtn);
			 }
		 };
		 
	 };
	 return cell;
};

trowPayment.setCellFactory(cellTFoctory);
}


}

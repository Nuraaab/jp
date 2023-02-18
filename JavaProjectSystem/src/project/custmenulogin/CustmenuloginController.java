package project.custmenulogin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import apiEntity.Account;
import apiRequest.APIClient;
import apiRequest.AccountAPI;
import common.CommonMethod;
import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
import javafx.util.Callback;
import model.Electricity;
import model.Telecom;
import model.Water;
import project.Main;
import retrofit2.Call;
import retrofit2.Response;

public class CustmenuloginController {
	private static Main main;
	//electricity
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
	//water
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
	private TableColumn<Water, String> wrowPayment;	
	//
	//telecom
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
	
	
	@FXML
	private TextField fullName;
	@FXML
	private ComboBox<String> bankName;
	@FXML
	private TextField pinNumber;
	@FXML
	private TextField accountNumber;
	@FXML
	private TextField amount;
	@FXML
	private DatePicker date;
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
	@FXML
	private Label service;
	@FXML
	public ComboBox chEmailE;
	@FXML
	public ComboBox chEmailW;
	@FXML
	public ComboBox chEmailT;
	
	//payment information
//static value
	@FXML
	private Label txt_piEmail;	
	@FXML
	private Label txt_piTransaction_date;
	@FXML
	private Label txt_piTransaction_id;
	@FXML
	private Label txt_piPayed_ammount;
	@FXML
	private Label txt_piRemaining_ammount;
	@FXML
	private Label txt_piService_type;
	@FXML
	private Label txt_piAccount_balance;
	//dinamic value
	@FXML
	private Label lb_piEmail;	
	@FXML
	private Label lb_piTransaction_date;
	@FXML
	private Label lb_piTransaction_id;
	@FXML
	private Label lb_piPayed_ammount;
	@FXML
	private Label lb_piRemaining_ammount;
	@FXML
	private Label lb_piService_type;
	@FXML
	private Label lb_piAccount_balance;
	//payment information
	@FXML
	private Tab information;
	@FXML
	private Tab profile;
	@FXML
	private Tab paymentDetail;
	@FXML
	private TabPane tp;
	private Boolean istrue=false;
	@FXML
	private Tab payement;
	@FXML
	private Tab paymentinfo;
	@FXML
	private Label elecTabelMessage;
	@FXML
	private Label paymentMessage;
	@FXML
	private Tab home;
	@FXML
	private Tab electricity;
	@FXML
	private Tab water;
	@FXML 
	private Tab telecom;
	@FXML
	private Tab detailInfo;
	ObservableList<Electricity> elecList =FXCollections.observableArrayList();
	ObservableList<Water> waterList =FXCollections.observableArrayList();
	ObservableList<Telecom> teleList =FXCollections.observableArrayList();
	ObservableList  listEmailE= FXCollections.observableArrayList();
	ObservableList  listEmailW= FXCollections.observableArrayList();
	ObservableList  listEmailT= FXCollections.observableArrayList();
	ObservableList<String> bankList =FXCollections.observableArrayList("Commercial Bank","Dashen Bank","Buna Bank","Abysinia Bank");
	@FXML
	private void payment() throws SQLException {
		CommonMethod pay=new CommonMethod();
		pay.payment(accountNumber.getText(), amount.getText(), service.getText(), lb_pAmount.getText(), chEmailE.getValue(), chEmailW.getValue(), chEmailT.getValue(), fullName.getText(), bankName.getValue(), pinNumber.getText());
		
		tp.getSelectionModel().select(paymentDetail);
	}
public void displayPaymentInfo() throws SQLException {

	ResultSet rs=null;
	if(service.getText().equals("electric")) {
		 rs = Connector.getData("SELECT * FROM elechistory WHERE email='"+chEmailE.getValue()+"' ORDER BY id DESC LIMIT 1");
		}else if(service.getText().equals("water")) {
			 rs = Connector.getData("SELECT * FROM waterhistory WHERE email='"+chEmailE.getValue()+"' ORDER BY id DESC LIMIT 1");
		}else if(service.getText().equals("telecom")) {
			 rs = Connector.getData("SELECT * FROM waterhistory WHERE email='"+chEmailE.getValue()+"' ORDER BY id DESC LIMIT 1");
		}else {
			System.out.println("error in selection");
		}
		while(rs.next()) {
			txt_piEmail.setText("Email");
			lb_piEmail.setText(rs.getString("email"));
			txt_piTransaction_date.setText("Transaction Date");
			lb_piTransaction_date.setText(rs.getString("transaction_date"));
			txt_piTransaction_id.setText("Transaction Id");
			lb_piTransaction_id.setText(rs.getString("transaction_id"));
			txt_piPayed_ammount.setText("Payed Utility Cost");
			lb_piPayed_ammount.setText(rs.getString("payed_ammount"));
			txt_piRemaining_ammount.setText("Remaining Cost");
			lb_piRemaining_ammount.setText(rs.getString("remaining_ammount"));
			txt_piService_type.setText("Service Type");
			lb_piService_type.setText(rs.getString("service_type"));
			txt_piAccount_balance.setText("Account Balance");
			lb_piAccount_balance.setText(rs.getString("account_balance"));
		}
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
	private void gopaymentInfo() {
		if(Integer.parseInt(lb_pAmount.getText())>0) {
		tp.getSelectionModel().select(paymentinfo);
		}else {
			paymentMessage.setText("Your utility cost is 0.00Birr");
		}
	}
	@FXML
	private void getPage() throws IOException, SQLException {
		home.setDisable(false);
		electricity.setDisable(false);
		water.setDisable(false);
		telecom.setDisable(false);
		detailInfo.setDisable(false);
		elecList.clear();
		ResultSet elecresultSet=Connector.getData("select id,firstName,lastName,email,elctdept,startDate,endDate  from eleccust where email='"+chEmailE.getValue()+"'");
		while(elecresultSet.next()) {
			elecList.add(new Electricity(elecresultSet.getInt("id"),
					elecresultSet.getString("firstName"),
					elecresultSet.getString("lastName"),
					elecresultSet.getString("email"),
					elecresultSet.getString("elctdept"),
					elecresultSet.getString("startDate"),
					elecresultSet.getString("endDate")
					
					));
		
			elecTable.setItems(elecList);
		}
		//water service
         waterList.clear();
         ResultSet waterRs=Connector.getData("select id,firstName,lastName,email,waterDept,startDate,endDate from watercust where email='"+chEmailW.getValue()+"'");
         while(waterRs.next()) {
        	 waterList.add(new Water(waterRs.getInt("id"),
        			 waterRs.getString("firstName"),
        			 waterRs.getString("lastName"),
        			 waterRs.getString("email"),
        			 waterRs.getString("waterDept"),
        			 waterRs.getString("startDate"),
        			 waterRs.getString("endDate")
        			 
        			 ));
        	 waterTable.setItems(waterList);
         }
       //telecom
         teleList.clear();
         ResultSet teleRs=Connector.getData("select id,firstName,lastName,email,teleDept,startDate,endDate from telecust where email='"+chEmailT.getValue()+"'");
	     while(teleRs.next()) {
	    	 teleList.add(new Telecom(teleRs.getInt("id"),
	    			 teleRs.getString("firstName"),
	    			 teleRs.getString("lastName"),
	    			 teleRs.getString("email"),
	    			 teleRs.getString("teleDept"),
	    			 teleRs.getString("startDate"),
	    			 teleRs.getString("endDate")
	    			 ));
	    	 teleTable.setItems(teleList);
	     }
	}

	@FXML
	private void initialize() throws SQLException {
		bankName.setValue("Commercial Bank");
		bankName.setItems(bankList);
		ResultSet rs = Connector.getData("select * from eleccust");
		while(rs.next()) {
			listEmailE.add(new String(rs.getString(4)));
		}
		chEmailE.setItems(listEmailE);
		
		ResultSet rsW = Connector.getData("select * from watercust");
		while(rsW.next()) {
			listEmailW.add(new String(rsW.getString(4)));
		}
		chEmailW.setItems(listEmailW);
		
		ResultSet rsT = Connector.getData("select * from telecust");
		while(rsT.next()) {
			listEmailT.add(new String(rsT.getString(4)));
		}
		chEmailT.setItems(listEmailT);		
        //electricity service
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
								 Electricity	elecData=elecTable.getSelectionModel().getSelectedItem();
								 if(elecData==null) {
								 	elecTabelMessage.setText("please select the row!");
								 }else { 
								displayElecData();
								tp.getSelectionModel().select(detailInfo);
								 }
							} catch (SQLException e) {
								e.printStackTrace();
							}
							 
						 });
						 setGraphic(paybtn);
					 }
				 };
				 
			 };
			 return cell;
		};

		rowPayment.setCellFactory(cellEFoctory);
		
		//water service
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
							 tp.getSelectionModel().select(detailInfo);
						 });
						 setGraphic(paybtn);
					 }
				 };
				 
			 };
			 return cell;
		 };

		 wrowPayment.setCellFactory(cellFoctory);
		//telecom service
		trowId.setCellValueFactory(new PropertyValueFactory<>("id"));
		trowFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		trowLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		trowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		trowStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		trowEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		trowAmmount.setCellValueFactory(new PropertyValueFactory<>("teleDept"));
		
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
							 tp.getSelectionModel().select(detailInfo);
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

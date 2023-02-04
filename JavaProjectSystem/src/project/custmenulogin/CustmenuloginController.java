package project.custmenulogin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.Connector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Electricity;
import model.Telecom;
import model.Water;
import project.Main;

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
	
	//
	@FXML
	public ComboBox chEmailE;
	@FXML
	public ComboBox chEmailW;
	@FXML
	public ComboBox chEmailT;


	@FXML
	private Tab home;
	@FXML
	private Tab electricity;
	@FXML
	private Tab water;
	@FXML 
	private Tab telecom;
	@FXML
	private Tab payment;
	ObservableList<Electricity> elecList =FXCollections.observableArrayList();
	ObservableList<Water> waterList =FXCollections.observableArrayList();
	ObservableList<Telecom> teleList =FXCollections.observableArrayList();
	ObservableList  listEmailE= FXCollections.observableArrayList();
	ObservableList  listEmailW= FXCollections.observableArrayList();
	ObservableList  listEmailT= FXCollections.observableArrayList();
	@FXML
	private void getPage() throws IOException, SQLException {
		home.setDisable(false);
		electricity.setDisable(false);
		water.setDisable(false);
		telecom.setDisable(false);
		payment.setDisable(false);
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
		//water service
		wrowId.setCellValueFactory(new PropertyValueFactory<>("id"));
		wrowFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		wrowLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		wrowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		wrowStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		wrowEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		wrowAmmount.setCellValueFactory(new PropertyValueFactory<>("waterDept"));
		//telecom service
		trowId.setCellValueFactory(new PropertyValueFactory<>("id"));
		trowFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		trowLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		trowEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		trowStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
		trowEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
		trowAmmount.setCellValueFactory(new PropertyValueFactory<>("teleDept"));		
	}

}

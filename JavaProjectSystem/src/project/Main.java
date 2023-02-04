package project;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static Stage primaryStage;
	private static BorderPane mainLayout;
	@Override
	public void start(Stage primaryStage) throws IOException{
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Service Payment System");
		showHome();
		
	}
	public static void showHome() throws IOException {
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(Main.class.getResource("home/Home.fxml"));
		mainLayout=loader.load();
		Scene scene =new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.resizableProperty().setValue(false);
		primaryStage.show();
	}
	public static  void showLogin() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("login/Login.fxml"));
		BorderPane loginPage =loader.load();
		mainLayout.setCenter(loginPage);
	}
	public static void showSignup() throws IOException {
		FXMLLoader lodder =new FXMLLoader();
		lodder.setLocation(Main.class.getResource("signup/Signup.fxml"));
		BorderPane signupPage=lodder.load();
		mainLayout.setCenter(signupPage);
	}
	public static void showProfile() throws IOException {
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("profile/Profile.fxml"));
		BorderPane profilePage=loader.load();
		mainLayout.setCenter(profilePage);
	}
	public static void showElectricityPage() throws IOException {
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(Main.class.getResource("electricity/Electricity.fxml"));
		BorderPane electricityPage =loader.load();
		mainLayout.setCenter(electricityPage);
		
	}
	public static void showWaterPage() throws IOException {
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(Main.class.getResource("water/Water.fxml"));
		BorderPane waterPage=loader.load();
		mainLayout.setCenter(waterPage);
	}
	public static void showTelecomPage() throws IOException {
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(Main.class.getResource("telecom/Telecom.fxml"));
		BorderPane telecomPage =loader.load();
		mainLayout.setCenter(telecomPage);
	}
	public static void showCustmenuPage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("custmenu/Custmenu.fxml"));
		BorderPane custmenuPage =loader.load();
		mainLayout.setCenter(custmenuPage);
	}

	public static void showCustmenuloginPage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("custmenulogin/Custmenulogin.fxml"));
		BorderPane custmenuloginPage =loader.load();
		mainLayout.setCenter(custmenuloginPage);
	}
	public static void showAddcustInfoPage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("addcustomer/Addcustomer.fxml"));
		BorderPane custInfoPage =loader.load();
		mainLayout.setCenter(custInfoPage);
	}
	public static void showManageElecCustPage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("manageelecCust/Manageeleccust.fxml"));
		BorderPane manageCustomer =loader.load();
		mainLayout.setCenter(manageCustomer);
	}
	public static void showPayementElecPage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("eleccusthistory/Eleccusthistory.fxml"));
		BorderPane elecPaymentPage =loader.load();
		mainLayout.setCenter(elecPaymentPage);
	}
	public static void showDataAnalysisElecPage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("elecdataanalysis/ElecDataAnalysis.fxml"));
		BorderPane elecDataAnalysisPage =loader.load();
		mainLayout.setCenter(elecDataAnalysisPage);
	}
	public static void main(String[] args) {
		launch(args);
	}
}

package project.electricity;

import java.io.IOException;

import javafx.fxml.FXML;
import project.Main;
public class electricityController {
	private static Main main;
@FXML
private void addInformation() throws IOException {
	main.showAddcustInfoPage();
}
@FXML
private void manageCustomer() throws IOException {
	main.showManageElecCustPage();
}
@FXML
private void paymentHistory() throws IOException {
	main.showPayementElecPage();
}
@FXML
private void dataanalysis() throws IOException {
	main.showDataAnalysisElecPage();
}
}

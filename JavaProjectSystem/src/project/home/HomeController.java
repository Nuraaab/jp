package project.home;

import java.io.IOException;

import javafx.fxml.FXML;
import project.Main;

public class HomeController {
	
private Main main;

@FXML
private void getLogin() throws IOException {
	main.showLogin();
}
@FXML
private void getSignup() throws IOException {
	main.showSignup();
}
}

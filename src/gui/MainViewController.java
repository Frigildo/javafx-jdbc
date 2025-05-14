package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.service.DepartmentService;


public class MainViewController implements Initializable{
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("MenuItemSeller");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}

	public synchronized void loadView(String pathView) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pathView));
			VBox newVbox = loader.load();
			
			Scene mainView = Main.getMainScene();
			VBox mainVbox = ((VBox) ((ScrollPane) mainView.getRoot()).getContent());
			
			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newVbox);
		} catch (IOException e) {
			Alerts.showAlerts("IOException", "Error Loading View", e.getMessage(), AlertType.ERROR);
		}
	}
	
	public synchronized void loadView2(String pathView) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pathView));
			VBox newVbox = loader.load();
			
			Scene mainView = Main.getMainScene();
			VBox mainVbox = (VBox) ((ScrollPane) mainView.getRoot()).getContent();
			
			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().addAll(Arrays.asList(mainMenu, newVbox));
			
			DepartmentListController controller = loader.getController();
			controller.setService(new DepartmentService());
			controller.updatyTable();
		} catch (IOException e) {
			Alerts.showAlerts("IOException", "Error Loading View", e.getMessage(), AlertType.ERROR);
		}
	}
}

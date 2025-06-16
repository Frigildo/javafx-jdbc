package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setService(new DepartmentService());
			controller.updateTable();
		});
	}

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("MenuItemSeller");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
	}

	public synchronized <T> void loadView(String pathView, Consumer<T> initializerAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(pathView));
			VBox newVbox = loader.load();
			
			Scene mainView = Main.getMainScene();
			VBox mainVbox = ((VBox) ((ScrollPane) mainView.getRoot()).getContent());
			
			Node mainMenu = mainVbox.getChildren().get(0);
			mainVbox.getChildren().clear();
			mainVbox.getChildren().add(mainMenu);
			mainVbox.getChildren().addAll(newVbox);
			
			T controller = loader.getController();
			initializerAction.accept(controller);
		} catch (IOException e) {
			Alerts.showAlerts("IOException", "Error Loading View", e.getMessage(), AlertType.ERROR);
		}
	}
	
}

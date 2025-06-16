package gui;

import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.service.DepartmentService;

public class DepartmentDialog implements Initializable{

	@FXML
	private Label id;
	
	@FXML
	private Label name;
	
	@FXML
	private Label idError;
	
	@FXML
	private Label nameError;
	
	@FXML
	private TextField idInput;
	
	@FXML
	private TextField nameInput;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	private Department department;
	
	private DepartmentService service;
	
	public void onBtSaveAction(ActionEvent event) {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		if(department == null) {
			throw new IllegalStateException("Department was null");
		}
		try {
			department = getDepartmentData();
			service.updateOrSave(department);
			Utils.currentStage(event);
		}
		catch (DbException e) {
			Alerts.showAlerts("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	public void onBtCancelAction() {
		System.out.println("Cancel");
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	public void setService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldInteger(idInput);
		Constraints.setTextFieldMaxLength(nameInput, 30);
	}
	
	public void updateDepartment() {
		idInput.setText(String.valueOf(department.getId()));
		nameInput.setText(department.getName());
	}
	
	public Department getDepartmentData() {
		Department dep = new Department();
		dep.setId(Integer.parseInt(id.getText()));
		dep.setName(name.getText());
		return dep;
	}
}

package model.service;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {

	public List<Department> findAll(){
		List<Department> list = new ArrayList<Department>();
		list.add(new Department(1, "Electronics"));
		list.add(new Department(1, "Food"));
		list.add(new Department(1, "Acessories"));
		return list;
	}
}

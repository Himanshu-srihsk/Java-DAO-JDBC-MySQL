package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {

	public static void main(String[] args) {

		SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
//		Department d = new Department();
//		d.setName("Account");
//		departmentDao.insert(d);
	// 	departmentDao.insert(new Department(null, "IT Department"));
	//	departmentDao.update(new Department(1, "Enginneers"));
		
	// System.out.println("find department by Id"+ departmentDao.findById(1));
	// 	departmentDao.deleteById(1);
		
		
		
//		List<Department> departments = departmentDao.findAll();
//		departments.forEach(d-> System.out.println(d));
		
		
		//////////////////Seller tests///////////
		
//		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, new Department(3, "IT Department"));
//		sellerDao.insert(newSeller);
//		System.out.println("\r\nNew seller, id: " + newSeller.getId());
		
//		Seller newSeller2 = new Seller(null, "himanshu", "himanshu@gmail.com", new Date(), 40000.0, new Department(2, "Finance"));
//		sellerDao.insert(newSeller2);
//		System.out.println("\r\nNew seller, id: " + newSeller2.getId());
		
//		Seller newSeller3 = new Seller(null, "sristi", "sristi@gmail.com", new Date(), 30000.0, new Department(2, "Finance"));
//		sellerDao.insert(newSeller3);
//		System.out.println("\r\nNew seller, id: " + newSeller3.getId());
		


//		Seller seller = sellerDao.findById(2);
//		System.out.println("Find by id: " + seller);
		

		
		
//
		Department department = new Department(2, null);
		List<Seller> sellers = sellerDao.findByDepartment(department);
		System.out.println("\r\nFind by Department:");
		for (Seller sellerByDep : sellers) {
			System.out.println(sellerByDep);
		}	
		
		
//		
		sellers = sellerDao.findAll();
		System.out.println("\r\nFind all:");
		for (Seller sellerByDep : sellers) {
			System.out.println(sellerByDep);
		}
//		
//		Seller newSeller = new Seller(null, "gorgia", "greofod@gmail.com", new Date(), 4000.0, department);
//		sellerDao.insert(newSeller);
//		System.out.println("\r\nNew seller, id: " + newSeller.getId());
		
		Seller  seller = sellerDao.findById(2);
		seller.setName("João");
		sellerDao.update(seller);
		System.out.println("\r\nUpdate seller, id: " + seller);
		
		Integer id = 2;
		System.out.println("\r\nDelete seller, id: "+id);
		sellerDao.deleteById(id);
		
		
//		Department d=  new Department(2, "Finance");
//		Department d1=  new Department(3, "IT Department");
//		
//		Seller s = new Seller();
//		s.setBaseSalary(30000.0);
//		s.setDepartment(d1);
	}

}

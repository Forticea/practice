package manager.icia.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class Employee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private int managerId;
	private String managerFirstName;
	private String managerLastName;
	private String email;
	private String phoneNumber;
	private Date HireDate;
	private int salary;
	private double commissionPct;
	private String jobId;
	private String jobTitle;
	private int departmentId;
	private String departmentName;
	private String city;
	
}

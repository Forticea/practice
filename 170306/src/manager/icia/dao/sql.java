package manager.icia.dao;

public interface sql {
	public String dept ="select department_id, department_name from departments";
	public String job ="select job_id, job_title from jobs";
	public String manager ="select distinct mgr.employee_id, mgr.first_name, mgr.last_name from employees emp, employees mgr where emp.manager_id=mgr.employee_id order by mgr.employee_id";
	public String empList ="select * from (select rownum rnum, t1.* from (select employee_id, first_name, last_name, department_name from employees emp, departments d where emp.DEPARTMENT_ID=d.DEPARTMENT_ID order by employee_id) t1) t2 where rnum between ? and ?";
	public String employee ="select emp.employee_id, emp.first_name, emp.last_name, mgr.manager_id, mgr.first_name, mgr.last_name, emp.email, emp.phone_number,emp.hire_date, emp.salary, nvl(emp.commission_pct,0) commission_pct, emp.job_id, j.job_title, d.department_id, d.department_name, l.city from employees emp, employees mgr, departments d, locations l, jobs j where emp.DEPARTMENT_ID=d.DEPARTMENT_ID and d.LOCATION_ID=l.LOCATION_ID and emp.JOB_ID=j.JOB_ID and emp.MANAGER_ID=mgr.EMPLOYEE_ID and emp.EMPLOYEE_ID=?";
	public String max ="select MAX(emp.EMPLOYEE_ID+1) from  employees emp";
	public String count ="select COUNT(*) employee_id from employees emp";
}

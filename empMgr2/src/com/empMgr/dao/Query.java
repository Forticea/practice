package com.empMgr.dao;

public interface Query {
	public String department = "select department_id, department_name from departments";
	public String job = "select job_id, job_title from jobs";
	public String manager = "select distinct m.employee_id, m.first_name, m.last_name from employees e, employees m where e.manager_id=m.employee_id order by m.employee_id";
	public String empList = "select * from (select * from (select rownum rnum, t.* from (select  employee_id, first_name, last_name, department_name from employees e, departments d where e.department_id=d.department_id order by employee_id) t ) t2 where t2.rnum<=?) t3 where t3.rnum>=?";
	public String employee = "select e.employee_id employee_id, e.first_name first_name, e.last_name last_name, e.MANAGER_ID manager_id, m.first_name manager_first, m.last_name manager_last, e.email email, e.phone_number phone_number, e.hire_date hire_date, j.job_title job_title, e.salary salary, nvl(e.COMMISSION_PCT, 0) commission_pct, d.department_name department_name, l.city city from employees e, departments d, jobs j, locations l, employees m where e.department_id=d.department_id and e.job_id=j.job_id and d.location_id=l.location_id and e.manager_id=m.employee_id(+) and e.employee_id=?";
	public String max="select max(employee_id)+1 from employees";
	public String insert = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
	// 사번, 성, 이름, 메일, 전화, 입사일, 잡, 급여, 커미션 , 매니저, 부서번호
	public String update="update employees set email=?, phone_number=?, job_id=?, salary=?, COMMISSION_PCT=?, manager_id=?, department_id=? where employee_id=?";
	// 성, 이름, 메일, 전화번호, 잡, 샐러리, 커미션, 매니저, 부서번호, 사원번호
	public String count = "select count(*) from employees";
}

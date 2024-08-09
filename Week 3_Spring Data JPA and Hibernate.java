1.
Exercise 1: Employee Management System - Overview and Setup

# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# H2 Console Configuration
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console


2.
Exercise 2: Employee Management System - Creating Entities

package com.example.employeemanagementsystem.entity;

import javax.persistence.*;
import lombok.Data;
public class Employee {
    private Long id;

    private String name;

    private String email;

    private Department department;
}

package com.example.employeemanagementsystem.entity;

import javax.persistence.*;
import java.util.List;
import lombok.Data;
public class Department 
    private Long id;

    private String name;

    private List<Employee> employees;
}
one-to-many relationship with the Employee entity. The mappedBy attribute indicates that the Department entity is not the owner of the relationship,
      and the ownership is defined in the Employee entity.

      

3.
      Exercise 3: Employee Management System - Creating Repositories


       Overview of Spring Data Repositories
Spring Data JPA simplifies the implementation of data access layers by reducing the need for boilerplate code.
Automatic CRUD Operations: The basic Create, Read, Update, and Delete (CRUD) operations are automatically implemented by extending Spring Data JPA's JpaRepository.
Query Methods: You can define custom query methods directly in the repository interface, which Spring Data JPA will automatically implement based on the method names.
Pagination and Sorting: Spring Data repositories provide built-in support for pagination and sorting of query results.
Type Safety: Strongly typed repository interfaces reduce the likelihood of runtime errors.

      package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByNameContainingIgnoreCase(String name);
}

      package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);
}

  
Derived Query Methods:
findByName(String name): Finds a department by its name.



  4.
  Exercise 4: Employee Management System - Implementing CRUD Operations



  package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            emp.setName(updatedEmployee.getName());
            emp.setEmail(updatedEmployee.getEmail());
            emp.setDepartment(updatedEmployee.getDepartment());
            return ResponseEntity.ok(employeeRepository.save(emp));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


      package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.entity.Department;
import com.example.employeemanagementsystem.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department createDepartment(@RequestBody Department department) {
        return departmentRepository.save(department);
    }
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return ResponseEntity.ok(department.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            Department dep = department.get();
            dep.setName(updatedDepartment.getName());
            return ResponseEntity.ok(departmentRepository.save(dep));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


      5.
      Exercise 5: Employee Management System - Defining Query Methods


      package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
   Employee findByEmail(String email);

  @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    @Query(value = "SELECT * FROM employees e WHERE e.email LIKE %:domain", nativeQuery = true)
    List<Employee> findByEmailDomain(@Param("domain") String domain);
}

package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EntityManager entityManager;

    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByDepartmentId", Employee.class);
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }

    public List<Employee> getEmployeesByEmailPattern(String emailPattern) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmailPattern", Employee.class);
        query.setParameter("emailPattern", emailPattern);
        return query.getResultList();
    }
}



  6.
    Exercise 6: Employee Management System - Implementing Pagination and



    package com.example.employeemanagementsystem.controller;

import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @GetMapping("/department/{departmentId}")
    public Page<Employee> getEmployeesByDepartmentId(@PathVariable Long departmentId, Pageable pageable) {
        return employeeRepository.findByDepartmentId(departmentId, pageable);
    }

    @GetMapping("/search")
    public Page<Employee> searchEmployees(@RequestParam String keyword, Pageable pageable) {
        return employeeRepository.findByNameContainingIgnoreCase(keyword, pageable);
    }
}




    7.
      Exercise 7: Employee Management System - Enabling Entity Auditing



    package com.example.employeemanagementsystem.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
}



      8.
      Exercise 8: Employee Management System - Creating Projections



      @Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
public interface EmployeeProjection {
    String getName();
    String getPosition();
}
public interface DepartmentProjection {
    String getName();
}
public interface EmployeeWithDepartmentProjection {
    String getName();
    String getPosition();
    String getDepartmentName();
}
public class EmployeeProjection {
    private String name;
    private String position;

    public EmployeeProjection(String name, String position) {
        this.name = name;
        this.position = position;
    }

}
      public class EmployeeWithDepartmentProjection {
    private String name;
    private String position;
    private String departmentName;

    public EmployeeWithDepartmentProjection(String name, String position, String departmentName) {
        this.name = name;
        this.position = position;
        this.departmentName = departmentName;
    }
}
      public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    @Query("SELECT e.name AS name, e.position AS position FROM Employee e")
    List<EmployeeProjection> findEmployeeProjections();

    @Query("SELECT new com.example.EmployeeWithDepartmentProjection(e.name, e.position, e.department.name) FROM Employee e")
    List<EmployeeWithDepartmentProjection> findEmployeeWithDepartmentProjections();
}
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    @Query("SELECT d.name AS name FROM Department d")
    List<DepartmentProjection> findDepartmentProjections();
}
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeProjection> getEmployeeProjections() {
        return employeeRepository.findEmployeeProjections();
    }

    public List<EmployeeWithDepartmentProjection> getEmployeeWithDepartmentProjections() {
        return employeeRepository.findEmployeeWithDepartmentProjections();
    }
}


    9.
      Exercise 9: Employee Management System - Customizing Data Source Configuration



    # Primary Data Source
spring.datasource.primary.url=jdbc:mysql://localhost:3306/primarydb
spring.datasource.primary.username=root
spring.datasource.primary.password=secret
spring.datasource.primary.driver-class-name=com.mysql.cj.jdbc.Driver

    # Secondary Data Source
spring.datasource.secondary.url=jdbc:mysql://localhost:3306/secondarydb
spring.datasource.secondary.username=root
spring.datasource.secondary.password=secret
spring.datasource.secondary.driver-class-name=com.mysql.cj.jdbc.Driver


      @Repository
public interface PrimaryRepository extends JpaRepository<PrimaryEntity, Long> {
}
@Repository
public interface SecondaryRepository extends JpaRepository<SecondaryEntity, Long> {
}
@Service
public class ExampleService {

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    public void performOperations() {
        PrimaryEntity entity = primaryRepository.findById(1L).orElse(null);
        SecondaryEntity secondaryEntity = secondaryRepository.findById(1L).orElse(null);
    }
}



  10.
  Exercise 10: Employee Management System - Hibernate-Specific Features



   @Entity
@DynamicUpdate
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String position;
    private String department;
}
  @Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "department_id")
    private Department department;
}

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true

spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
spring.jpa.properties.hibernate.cache.use_query_cache=true
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

@Service
public class EmployeeService {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void batchInsert(List<Employee> employees) {
        Session session = entityManager.unwrap(Session.class);
        int batchSize = 50; // Number of inserts per batch

        for (int i = 0; i < employees.size(); i++) {
            session.save(employees.get(i));
            if (i % batchSize == 0 && i > 0) {
                session.flush();
                session.clear();
            }
        }
        session.flush();
        session.clear();
    }
}


package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Account")
public class Account {
    @Column(name="Account_ID")
    @Id
    private String id;
    @Column(name="User_name")
    private String username;
    @Column(name="Password")
    private String password;
    @Column(name="Permission")
    private boolean Permission;
    @Column(name="Emp_id")
    private String empId;
    @Column(name="Acc_Status")
    private boolean Status;

    public Account() {
    }

    public Account(String id, String username, String password, boolean permission, String empId, boolean status) {
        this.id = id;
        this.username = username;
        this.password = password;
        Permission = permission;
        this.empId = empId;
        Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPermission() {
        return Permission;
    }

    public void setPermission(boolean permission) {
        Permission = permission;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}

package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

import java.util.Date;

@Table(name = "bill")
public class Bill {
    @Column(name = "id")
    private long id;
    @Column(name = "code")
    private String code;
    @Column(name = "type")
    private boolean type;
    @Column(name = "emp_id_created")
    private String empIdCreated;
    @Column(name = "created")
    private Date created;
    @Column(name = "emp_id_auth")
    private String empIdAuth;
    @Column(name = "auth_date")
    private Date authDate;
    @Column(name = "status")
    private int status;

    public Bill() {
    }

    public Bill(long id, String code, boolean type, String empIdCreated, Date created, String empIdAuth, Date authDate, int status) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.empIdCreated = empIdCreated;
        this.created = created;
        this.empIdAuth = empIdAuth;
        this.authDate = authDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getEmpIdCreated() {
        return empIdCreated;
    }

    public void setEmpIdCreated(String empIdCreated) {
        this.empIdCreated = empIdCreated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmpIdAuth() {
        return empIdAuth;
    }

    public void setEmpIdAuth(String empIdAuth) {
        this.empIdAuth = empIdAuth;
    }

    public Date getAuthDate() {
        return authDate;
    }

    public void setAuthDate(Date authDate) {
        this.authDate = authDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

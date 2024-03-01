package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Table(name = "Product")
public class Product {
    @Column(name = "Product_ID")
    @Id
    private String id;
    @Column(name = "Product_Name")
    private String name;
    @Column(name = "Manufacturer")
    private String Manufacturer;
    @Column(name = "Created")
    private Date Created;
    @Column(name = "Batch")
    private int Batch;
    @Column(name = "Quantity")
    private int Quantity;
    @Column(name = "Product_Status")
    private boolean Status;

    public Product() {
    }

    public Product(String id, String name, String manufacturer, Date created, int batch, int quantity, boolean status) {
        this.id = id;
        this.name = name;
        Manufacturer = manufacturer;
        Created = created;
        Batch = batch;
        Quantity = quantity;
        Status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    public int getBatch() {
        return Batch;
    }

    public void setBatch(int batch) {
        Batch = batch;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}

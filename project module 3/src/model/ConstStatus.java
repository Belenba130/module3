package model;

public interface ConstStatus {
    interface ProductStatus {
        boolean ACTIVE = true;
        boolean INACTIVE = false;
    }
    interface BillStatus {
        byte CREATED = 0;
        byte CANCELLED = 1;
        byte APPROVED = 2;
    }
    interface AccountStatus {
        boolean ACTIVE = true;
        boolean BLOCK = false;
    }
    interface EmployeeStatus {
        byte ACTIVE = 0;
        byte INACTIVE = 1;
        byte QUIT = 2;
    }

}

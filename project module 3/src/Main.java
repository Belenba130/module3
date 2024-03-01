import Service.AccountLoginImp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Manager.ProductManager productManager = new Manager.ProductManager();
        Manager.AccountManager accountManager = new Manager.AccountManager();
        Manager.ReceiptManager receiptManager = new Manager.ReceiptManager();
        Manager.EmployeeManager employeeManager = new Manager.EmployeeManager();
        AccountLoginImp accountLoginImp = new AccountLoginImp();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Canteen Management System");
        System.out.println("input user");
        String user = sc.next();
        System.out.println("input password");
        String password = sc.next();




    }
}
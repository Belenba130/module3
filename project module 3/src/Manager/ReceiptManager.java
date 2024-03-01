package Manager;

import Repository.RepositoryImp;
import entity.Bill;
import entity.Bill_Details;
import entity.Product;
import model.BillType;
import model.ConstStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReceiptManager extends RepositoryImp<Bill, String> {
    private Scanner scanner;
    private RepositoryImp<Bill_Details, String> billDetailsRepository = new RepositoryImp<Bill_Details, String>();
    private RepositoryImp<Product, String> productRepository = new RepositoryImp<Product, String>();

    public ReceiptManager() {
        this.scanner = new Scanner(System.in);
    }
    public void ReceiptMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("******************RECEIPT MANAGEMENT****************");
            System.out.println("1. Show all receipts");
            System.out.println("2. Add receipt");
            System.out.println("3. Update receipt");
            System.out.println("4. Infor receipt");
            System.out.println("5. Approve receipt");
            System.out.println("6. Find receipt");
            System.out.println("7. Exit");
            System.out.print("Choose your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllBill(BillType.Import);
                    break;
                case 2:
                    addBill(BillType.Import);
                    break;
                case 3:
                    updateBill(BillType.Import);
                    break;
                case 4:
                    inforBill();
                    break;
                case 5:
                    approveBill();
                    break;
                case 6:
                    findBill();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public void BillMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("******************BILL MANAGEMENT****************");
            System.out.println("1. Show all Bills");
            System.out.println("2. Add Bill");
            System.out.println("3. Update Bill");
            System.out.println("4. Infor Bill");
            System.out.println("5. Approve Bill");
            System.out.println("6. Find Bill");
            System.out.println("7. Exit");
            System.out.print("Choose your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllBill(BillType.Export);
                    break;
                case 2:
                    addBill(BillType.Export);
                    break;
                case 3:
                    updateBill(BillType.Export);
                    break;
                case 4:
                    inforBill();
                    break;
                case 5:
                    approveBill();
                    break;
                case 6:
                    findBill();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }


    public void showAllBill(boolean billType) {
        List<Bill> bills = getAll(Bill.class);
        for (Bill bill : bills) {
            if (bill.isType() == billType) {
                System.out.println(bill);
            }
        }
    }


    public void addBill(Boolean billType) {
        boolean ans = false;
        while (!ans) {
            List<Bill_Details> billDetailsList = new ArrayList<>();
            System.out.println("Enter the Product ID: ");
            String key = scanner.next();
            Product product = productRepository.getById(Product.class, key);
            if (product != null && product.isStatus() == ConstStatus.ProductStatus.ACTIVE) {
                Bill_Details billDetails = findBillDetailsByProductId(key, billDetailsList);
                if (billDetails != null) {
                    System.out.println("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    if (billType) {
                        billDetails.setQuantity(billDetails.getQuantity() + quantity);
                    } else {
                        billDetails.setQuantity(billDetails.getQuantity() - quantity);
                    }
                } else {
                    Bill_Details newBillDetails = new Bill_Details();
                    System.out.println("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    newBillDetails.setQuantity(quantity);
                    newBillDetails.setProductId(key);
                    billDetailsList.add(newBillDetails);
                }
                System.out.println("Do you want to add another product? (y/n)");
                String choice = scanner.next();
                if (choice.equals("n")) {
                    ans = true;
                }
            } else {
                System.out.println("Invalid product ID or product is inactive.");
                System.out.println("Do you want to add another product? (y/n)");
                String choice = scanner.next();
                if (choice.equals("n")) {
                    ans = true;
                }
            }
            if (!billDetailsList.isEmpty()) {
                Bill bill = new Bill();
                System.out.println("Enter bill details: ");
                inputBillDetails(bill);
                bill.setType(billType);
                addBillDetailsToBill(bill, billDetailsList);
            }
        }
    }

    public void updateBill(Boolean billType) {
        List<Bill> billList = getAll(Bill.class);
        System.out.println("Enter bill ID to update: ");
        String key = scanner.next();
        Bill billToUpdate = findBillByKey(key, billList);
        if (billToUpdate != null) {
            System.out.println("Enter new details for bill: ");
            inputBillDetails(billToUpdate);
            billToUpdate.setType(billType);
            update(billToUpdate);
            System.out.println("Bill updated successfully");
        }
    }

    public void inforBill() {
        System.out.println("Enter bill ID: ");
        String key = scanner.next();
        Bill bill = findBillByKey(key, getAll(Bill.class));
        if (bill != null) {
            System.out.println(bill);
        }
    }

    public void approveBill() {
        System.out.println("Enter bill ID: ");
        String key = scanner.next();
        Bill bill = findBillByKey(key, getAll(Bill.class));
        if (bill != null) {
            bill.setStatus(ConstStatus.BillStatus.APPROVED);
            update(bill);
            System.out.println("Bill approved successfully");
        }
    }

    public void findBill() {
        System.out.println("Enter bill ID: ");
        String key = scanner.next();
        Bill bill = findBillByKey(key, getAll(Bill.class));
        if (bill != null) {
            System.out.println(bill);
        }
    }


    private Bill_Details findBillDetailsByProductId(String productId, List<Bill_Details> billDetailsList) {
        for (Bill_Details d : billDetailsList) {
            if (d.getProductId().equals(productId)) {
                return d;
            }
        }
        return null;
    }

    private void inputBillDetails(Bill bill) {
        System.out.print("Bill Id: ");
        bill.setId(scanner.nextLong());
        System.out.print("Bill Code: ");
        bill.setCode(scanner.next());
        System.out.print("Emp Id Created: ");
        bill.setEmpIdCreated(scanner.next());
        bill.setCreated(new Date());
        System.out.print("Emp Id Auth: ");
        bill.setEmpIdAuth(scanner.next());
        bill.setAuthDate(new Date());
        System.out.print("Bill Status: ");
        bill.setStatus(ConstStatus.BillStatus.CREATED);
    }

    private void addBillDetailsToBill(Bill bill, List<Bill_Details> billDetailsList) {
        add(bill);
        for (Bill_Details d : billDetailsList) {
            d.setBillId(bill.getId());
            billDetailsRepository.add(d);
        }
    }

    private Bill findBillByKey(String key, List<Bill> billList) {
        return billList.stream()
                .filter(b -> String.valueOf(b.getId()).contains(key) || b.getCode().equals(key))
                .findFirst().orElse(null);
    }
}

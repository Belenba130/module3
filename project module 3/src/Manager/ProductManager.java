package Manager;

import Repository.RepositoryImp;
import entity.Product;
import model.ConstStatus;

import java.util.List;
import java.util.Scanner;

public class ProductManager extends RepositoryImp<Product, String> {

    private final Scanner scanner;

    public ProductManager() {
        this.scanner = new Scanner(System.in);
    }
    public ProductManager(String[] args) {
        this();
    }

    public void ProductManagerMenu() {
        System.out.println("******************PRODUCT MANAGEMENT****************");
        System.out.println("1. Show all products");
        System.out.println("2. Add product");
        System.out.println("3. Update product");
        System.out.println("4. Find product");
        System.out.println("5. Update product status");
        System.out.println("6. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();
        boolean exit = false;
        while (!exit) {
            switch (choice) {
                case 1:
                    showAllProduct();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    searchProductByName();
                    break;
                case 5:
                    updateProductStatus();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void showAllProduct() {
        List<Product> productList = getAll(Product.class);
        int count = 0;
        for (Product product : productList) {
            System.out.println(product);
            count++;
            if (count % 10 == 0) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    public void addProduct() {
        Product product = new Product();
        System.out.println("Enter product details:");
        System.out.print("Product Id: ");
        product.setId(scanner.nextLine());
        System.out.print("Product Name: ");
        product.setName(scanner.nextLine());
        System.out.print("Manufacturer: ");
        product.setManufacturer(scanner.nextLine());
        System.out.print("Batch: ");
        product.setBatch(scanner.nextShort());
        System.out.print("Quantity: ");
        product.setQuantity(scanner.nextInt());
        product.setStatus(true);
        add(product);
        System.out.println("Product added successfully.");
    }

    public void updateProduct() {
        System.out.print("Enter product ID to update: ");
        String productId = scanner.nextLine();
        Product product = getById(Product.class, productId);
        if (product != null) {
            System.out.println("Enter new details for product:");
            System.out.print("Product Name: ");
            product.setName(scanner.nextLine());
            System.out.print("Manufacturer: ");
            product.setManufacturer(scanner.nextLine());
            System.out.print("Batch: ");
            product.setBatch(scanner.nextShort());
            update(product);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void searchProductById() {
        System.out.print("Enter product ID to search: ");
        String productId = scanner.nextLine();

    }

    public void searchProductByName() {
        System.out.print("Enter product Name to search: ");
        String productName = scanner.nextLine();
        List<Product> products = getAll(Product.class);
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                System.out.println(product);
            }
        }
    }

    public void updateProductStatus() {
        System.out.print("Enter product ID to update status: ");
        String productId = scanner.nextLine();
        Product product = getById(Product.class, productId);
        if (product != null) {
            System.out.print("Enter new status (1 for Active, 0 for Inactive): ");
            int statusInput = scanner.nextInt();
            boolean status;
            if (statusInput == 1) {
                status = ConstStatus.ProductStatus.ACTIVE;
            } else if (statusInput == 0) {
                status = ConstStatus.ProductStatus.INACTIVE;
            } else {
                System.out.println("Invalid status input.");
                return;
            }
            product.setStatus(status);
            update(product);
            System.out.println("Product status updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }
}


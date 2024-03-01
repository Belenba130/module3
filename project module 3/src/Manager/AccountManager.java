package Manager;
import Repository.RepositoryImp;
import entity.Account;
import java.util.List;
import java.util.Scanner;

public class AccountManager extends RepositoryImp<Account, String> {
    private final Scanner scanner;

    public AccountManager() {
        this.scanner = new Scanner(System.in);
    }

    public AccountManager(String[] args) {
        this();
    }

    public void AccountManagerMenu() {
        System.out.println("******************ACCOUNT MANAGEMENT****************");
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Show all account");
            System.out.println("2. Add account");
            System.out.println("3. Update account status");
            System.out.println("4. Find and update account");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayAccountList();
                    break;
                case 2:
                    addAccount();
                    break;
                case 3:
                    findAndUpdateAccount();
                    break;
                case 4:
                    findAndUpdateAccount();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }


    public void displayAccountList() {
        List<Account> accountList = getAll(Account.class);
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

    public void addAccount() {
        Account account = new Account();
        System.out.println("Enter account details: ");
        System.out.print("Account Id: ");
        account.setId(scanner.nextLine());
        System.out.print("Account Name: ");
        account.setUsername(scanner.nextLine());
        System.out.print("Account Status: ");
        account.setStatus(scanner.nextBoolean());
        scanner.nextLine();
        add(account);
        System.out.println("Account added successfully!");
    }

    public void findAndUpdateAccount() {
        List<Account> accountList = getAll(Account.class);
        System.out.print("Enter account ID or username: ");
        String key = scanner.next();
        Account foundAccount = accountList.stream()
                .filter(account -> account.getId().equals(key) || account.getUsername().equals(key))
                .findFirst()
                .orElse(null);
        if (foundAccount != null) {
            System.out.println(foundAccount);
            System.out.println("If you want to update status, press 1; otherwise, press 0: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                updateAccountStatus(foundAccount);
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    private void updateAccountStatus(Account accountToUpdate) {
        System.out.print("Enter new status (true/false): ");
        boolean newStatus = scanner.nextBoolean();
        scanner.nextLine();
        accountToUpdate.setStatus(newStatus);
        update(accountToUpdate);
        System.out.println("Account status updated successfully!");
    }
}

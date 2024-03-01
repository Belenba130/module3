package Manager;
import Repository.RepositoryImp;
import entity.Employee;
import model.ConstStatus;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager extends RepositoryImp<Employee, String> {
    private final Scanner scanner;

    public EmployeeManager() {
        this.scanner = new Scanner(System.in);
    }

    public EmployeeManager(String[] args) {
        this();
    }
    public void EmployeeManagerMenu() {
        System.out.println("******************EMPLOYEE MANAGEMENT****************");
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Show all employees");
            System.out.println("2. Add employee");
            System.out.println("3. Find employee");
            System.out.println("4. Update employee");
            System.out.println("5. Update employee status");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    displayEmployeeList();
                    break;
                case 2:
                    addEmployee();
                    break;
                case 3:
                    FindEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    updateEmployeeStatus();
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


    public void displayEmployeeList() {
        List<Employee> employeeList = getAll(Employee.class);
        employeeList.sort(Comparator.comparing(Employee::getName));
        int count = 0;
        for (Employee employee : employeeList) {
            System.out.println(employee);
            count++;
            if (count % 10 == 0) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    public void addEmployee() {
        Employee employee = new Employee();
        System.out.println("Enter employee details: ");
        System.out.print("Employee Id: ");
        employee.setId(scanner.nextLine());
        System.out.print("Name: ");
        employee.setName(scanner.nextLine());
        System.out.print("Email: ");
        employee.setEmail(scanner.nextLine());
        System.out.print("Phone: ");
        employee.setPhone(scanner.nextLine());
        System.out.print("Address: ");
        employee.setAddress(scanner.nextLine());
        System.out.print("Status: ");
        employee.setStatus(scanner.nextInt());
        scanner.nextLine();
        add(employee);
        System.out.println("Employee added successfully!");
    }

    public void FindEmployee() {
        List<Employee> employeeList = getAll(Employee.class);
        System.out.print("Enter employee ID or name: ");
        String key = scanner.nextLine();
        employeeList.stream().filter(employee -> employee.getId().equals(key) || employee.getName().equals(key))
                .forEach(System.out::println);
    }
    public void updateEmployee() {
        System.out.println("Enter employee ID to update: ");
        String id = scanner.nextLine();
        Employee employee = getById(Employee.class, id);
        if (employee != null) {
            System.out.println("Enter new details for employee: ");
            System.out.print("Name: ");
            employee.setName(scanner.nextLine());
            System.out.print("Email: ");
            employee.setEmail(scanner.nextLine());
            System.out.print("Phone: ");
            employee.setPhone(scanner.nextLine());
            System.out.print("Address: ");
            employee.setAddress(scanner.nextLine());
            update(employee);
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("Invalid employee ID!");
        }
    }
    public void updateEmployeeStatus() {
        System.out.println("Enter employee ID to update: ");
        String id = scanner.nextLine();
        Employee employee = getById(Employee.class, id);
        if (employee != null) {
            System.out.println("Enter new status for employee: ");
            int status = scanner.nextInt();
            scanner.nextLine();
            if (status == 0) {
                status = ConstStatus.EmployeeStatus.ACTIVE;
            } else if (status == 1) {
                status = ConstStatus.EmployeeStatus.INACTIVE;
            } else if (status == 2) {
                status = ConstStatus.EmployeeStatus.QUIT;
            }
        } else {
            System.out.println("Invalid status input!");
            return;
        }
        update(employee);
        System.out.println("Employee status updated successfully!");
    }

}


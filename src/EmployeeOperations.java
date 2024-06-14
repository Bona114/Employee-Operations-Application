import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeOperations {
    static List<Employee> employees = importEmployeeDataset("Employees.csv");
    public static void main(String[] args) {
        List<String> employeeInfoList = getEmployeeInfoList();

        employeeInfoList.forEach(System.out::println);

        double averageSalary = getAverageSalary();

        System.out.printf("\nAverage Salary: $%.2f\n", averageSalary);

        int ageThreshold = 30;
        List<Employee> employeesAbove30 = getEmployeesAboveAge(30);
        System.out.println("\nEmployees above 30:");
        employeesAbove30.forEach(employee -> System.out.println(employee.getName()));
    }

    private static List<Employee> importEmployeeDataset(String fileName) {
        String line;
        String[] row;
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                row = line.split(",");
                employees.add(new Employee(row[0], Integer.parseInt(row[1]), row[2], Double.parseDouble(row[3])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static List<Employee> getEmployeeList() {
        return employees;
    }

    private static List<String> getEmployeeInfoList() {
        EmployeeToInfo employeeToInfo = new EmployeeToInfo();
        List<String> employeeInfoList = new ArrayList<>();

        for (Employee employee : employees) {
            String employeeInfo = employeeToInfo.apply(employee);
            employeeInfoList.add(employeeInfo);
        }

        return employeeInfoList;
    }

    private static Double getAverageSalary() {
        return(employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .getAsDouble());
    }

    private static List<Employee> getEmployeesAboveAge(int ageThreshold) {
        return(employees.stream()
                .filter(e -> e.getAge() > ageThreshold)
                .toList());
    }
}

import java.util.List;
import java.util.function.Function;

public class EmployeeToInfo implements Function<Employee, String> {
    @Override
    public String apply(Employee targetEmployee) {
        List<Employee> employees = EmployeeOperations.getEmployeeList();
        for (Employee employee : employees) {
            if (employee.getName().equals(targetEmployee.getName())) {
                return employee.getName() + " (" + employee.getDepartment() + ")";
            }
        }
        return null;
    }
}

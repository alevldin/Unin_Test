import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import ru.netology.Employee;
import ru.netology.Main;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainTest {
    @Test
    void ParseXMLTest() throws ParserConfigurationException, IOException, SAXException {
        String fileTest = "src/test/resources/test.xml";
        Employee EmployeeTest = new Employee(1, "test", "test", "test", 1);
        List<Employee> employeeList = Main.parseXML(fileTest);
        List<Employee> EmployeeListTest = new ArrayList<>();
        EmployeeListTest.add(EmployeeTest);

        String expected = EmployeeListTest.toString();
        String actual = employeeList.toString();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void ParseCSVTest() {
        String[] ColumnMappingTest = {"id", "firstName", "lastName", "country", "age"};
        String fileTest = "src/test/resources/test.csv";
        Employee EmployeeTest = new Employee(1, "test", "test", "test", 1);
        List<Employee> employeeList = Main.parseCSV(ColumnMappingTest, fileTest);
        List<Employee> EmployeeListTest = new ArrayList<>();
        EmployeeListTest.add(EmployeeTest);

        String expected = EmployeeListTest.toString();
        String actual = Objects.requireNonNull(employeeList).toString();

        Assertions.assertEquals(expected, actual);
    }

    public static class EmployeeTest {
        List<Employee> listTest = new ArrayList<>();

        @BeforeEach
        void addEmployeeTest() {
            Employee employee = new Employee(1, "John", "Smith", "USA", 23);
            listTest.add(employee);
            employee = new Employee(2, "Inav", "Petrov", "RU", 25);
            listTest.add(employee);
        }

        @Test
        void getFirstNameTest() {
            MatcherAssert.assertThat("John", CoreMatchers.equalTo(listTest.get(0).firstName));

        }

        @Test
        void containsLastnameTest() {

            MatcherAssert.assertThat(listTest.get(1).lastName, CoreMatchers.containsString("P"));
        }

        @Test
        void endLastnameTest() {

            MatcherAssert.assertThat(listTest.get(0).lastName, CoreMatchers.endsWith("th"));
        }
    }
}
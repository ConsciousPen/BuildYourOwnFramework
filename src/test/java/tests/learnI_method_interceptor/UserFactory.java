package tests.learnI_method_interceptor;

import com.opencsv.CSVReader;
import org.testng.annotations.Factory;
import org.testng.annotations.Listeners;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Listeners(OrderByPriority.class)
public class UserFactory {
    /*CreateUser :admin:admin
    Delete user :admin
    CreateUser :user:user
    Delete user :user
    CreateUser :guest:guest
    Delete user :guest*/

    @Factory
    public Object[] testFactory() throws IOException {
        List<Object> tests = new ArrayList<Object>();
        List<String[]> data = getCSVData();
        int i = 0;
        for (String[] dataItem : data) {
            CreateUser createUser = new CreateUser(dataItem[0], dataItem[1]);
            createUser.setPriority(i++);
            tests.add(createUser);
            DeleteUser deleteUser = new DeleteUser(dataItem[0]);
            deleteUser.setPriority(i++);
            tests.add(deleteUser);
        }
        return (Object[]) tests.toArray(new Object[tests.size()]);
    }

    public List<String[]> getCSVData() {
        String csvFile = "src/test/resources/files/users.csv";
        List<String[]> result = new ArrayList<String[]>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}


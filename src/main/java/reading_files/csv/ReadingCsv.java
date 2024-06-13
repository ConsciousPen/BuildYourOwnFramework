package reading_files.csv;

import reading_files.ContactData;
import reading_files.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingCsv {
    public static List<ContactData> loadContactsFromCsvFile(String file) throws IOException {
        List<ContactData> list = new ArrayList<ContactData>();
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(",");
            ContactData contact = new ContactData()
                    .withFirstName(part[0])
                    .withLastName(part[1]);
            list.add(contact);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        return list;
    }

    public static List<GroupData> loadGroupsFromCsvFile(String file) throws IOException {
        List<GroupData> list = new ArrayList<GroupData>();
        FileReader reader = new FileReader(file);
        // wrap file reader to get extra methods
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String[] part = line.split(",");
            GroupData group = new GroupData()
                    .withName(part[0])
                    .withHeader(part[1])
                    .withFooter(part[2]);
            list.add(group);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return list;
    }

}

package reading_files.xml;

import com.thoughtworks.xstream.XStream;
import reading_files.ContactData;
import reading_files.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SavingXml {

    private static void saveContactToXmlFile(List<ContactData> contactData, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("contact",ContactData.class);
        String xml = xStream.toXML(contactData);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        System.out.println("XML data saved to " + file.getAbsolutePath());
        writer.close();
    }

    private static void saveGroupsToXmlFile(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.alias("group",GroupData.class);
        String xml = xStream.toXML(groups);
        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        System.out.println("XML data saved to " + file.getAbsolutePath());
        writer.close();
    }

}

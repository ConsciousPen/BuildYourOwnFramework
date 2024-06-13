package reading_files.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import reading_files.ContactData;
import reading_files.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadingXml {

    public static List<ContactData> loadContactsFromXMLFile(File file) throws FileNotFoundException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("contact",ContactData.class);
        FileReader reader = new FileReader(file);
        // parse xml
        return (List<ContactData>) xStream.fromXML(reader);
    }

    public static List<GroupData> loadGroupsFromXMLFile(File file) throws FileNotFoundException {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias("contact",GroupData.class);
        FileReader reader = new FileReader(file);
        // parse xml
        return (List<GroupData>) xStream.fromXML(reader);
    }


}

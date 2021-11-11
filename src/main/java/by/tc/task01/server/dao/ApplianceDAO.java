package by.tc.task01.server.dao;

import by.tc.task01.server.entity.StudentInfo;
import by.tc.task01.server.entity.criteria.ClientCriteria;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ApplianceDAO {
	List<StudentInfo> find(ClientCriteria clientCriteria) throws FileNotFoundException;
	List<StudentInfo> getAll();
	void addClient(List<String[]> parameters) throws ParserConfigurationException, IOException, SAXException, TransformerException;
}

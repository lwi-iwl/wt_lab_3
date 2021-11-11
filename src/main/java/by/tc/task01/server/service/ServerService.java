package by.tc.task01.server.service;

import by.tc.task01.server.entity.StudentInfo;
import by.tc.task01.server.entity.criteria.ClientCriteria;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ServerService {

	List<StudentInfo> find(ClientCriteria clientCriteria) throws FileNotFoundException;
	List<StudentInfo> getAll();

	void addClient(String name, String password, String allowance) throws IOException, SAXException, ParserConfigurationException, TransformerException;
}

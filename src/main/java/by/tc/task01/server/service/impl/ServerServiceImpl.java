package by.tc.task01.server.service.impl;

import by.tc.task01.server.dao.ApplianceDAO;
import by.tc.task01.server.dao.DAOFactory;
import by.tc.task01.server.entity.ClientInfo;
import by.tc.task01.server.entity.Info;
import by.tc.task01.server.entity.StudentInfo;
import by.tc.task01.server.entity.criteria.Criteria;
import by.tc.task01.server.entity.criteria.SearchCriteria;
import by.tc.task01.server.service.ServerService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerServiceImpl implements ServerService {


	public List<Info> getAll(){
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<Info> appliances = applianceDAO.getAll("src/main/resources/students_db.xml", "Student");
		return appliances;
	}

	public boolean addClient(String name, String password, String allowance) throws IOException, SAXException, ParserConfigurationException, TransformerException {
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<String[]> parameters = new ArrayList<>();
		parameters.add(new String[]{SearchCriteria.Client.name.getEnumName(), name});
		parameters.add(new String[]{SearchCriteria.Client.password.getEnumName(), password});
		parameters.add(new String[]{"allowance", allowance});
		return applianceDAO.add(parameters, "src/main/resources/client_db.xml", "Client");
	}

	public Info getClient(Criteria criteria) {
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<Info> clientInfoList = applianceDAO.get(criteria, "src/main/resources/client_db.xml", "Client");
		if (!clientInfoList.isEmpty()) {
			return clientInfoList.get(0);
		}
		else {
			return null;
		}
	}

	public Info getStudent(Criteria criteria){
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<Info> clientInfoList = applianceDAO.get(criteria, "src/main/resources/students_db.xml", "Student");
		if (!clientInfoList.isEmpty()) {
			return clientInfoList.get(0);
		}
		else {
			return null;
		}
	}

	public boolean regStudent(String name, String newName, String averageScore){
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<String[]> parameters = new ArrayList<>();
		parameters.add(new String[]{SearchCriteria.Student.name.getEnumName(), newName});
		parameters.add(new String[]{SearchCriteria.Student.averageScore.getEnumName(), averageScore});
		return applianceDAO.edit(name, parameters, "src/main/resources/students_db.xml");
	}

	public boolean addStudent(String name, String averageScore) throws ParserConfigurationException, TransformerException, SAXException, IOException {
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<String[]> parameters = new ArrayList<>();
		parameters.add(new String[]{SearchCriteria.Student.name.getEnumName(), name});
		parameters.add(new String[]{SearchCriteria.Student.averageScore.getEnumName(), averageScore});
		return applianceDAO.add(parameters, "src/main/resources/students_db.xml", "Student");
	}

}
package by.tc.task01.server.service.impl;

import by.tc.task01.server.dao.ApplianceDAO;
import by.tc.task01.server.dao.DAOFactory;
import by.tc.task01.server.entity.StudentInfo;
import by.tc.task01.server.entity.criteria.ClientCriteria;
import by.tc.task01.server.entity.criteria.SearchCriteria;
import by.tc.task01.server.service.ServerService;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerServiceImpl implements ServerService {

	@Override
	public List<StudentInfo> find(ClientCriteria clientCriteria) throws FileNotFoundException {
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<StudentInfo> appliances = applianceDAO.find(clientCriteria);
		return appliances;
	}

	public List<StudentInfo> getAll(){
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<StudentInfo> appliances = applianceDAO.getAll();
		return appliances;
	}

	public void addClient(String name, String password, String allowance) throws IOException, SAXException, ParserConfigurationException, TransformerException {
		DAOFactory factory = DAOFactory.getInstance();
		ApplianceDAO applianceDAO = factory.getApplianceDAO();
		List<String[]> parameters = new ArrayList<>();
		parameters.add(new String[]{SearchCriteria.Client.name.getEnumName(), name});
		parameters.add(new String[]{SearchCriteria.Client.password.getEnumName(), password});
		parameters.add(new String[]{SearchCriteria.Client.allowance.getEnumName(), allowance});
		applianceDAO.addClient(parameters);
	}

}
package by.tc.task01.server.dao.impl;

import by.tc.task01.server.dao.ApplianceDAO;
import by.tc.task01.server.entity.StudentInfo;
import by.tc.task01.server.entity.criteria.ClientCriteria;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplianceDAOImpl implements ApplianceDAO{

	@Override
	public List<StudentInfo> find(ClientCriteria clientCriteria) throws FileNotFoundException {
		String groupName = clientCriteria.getGroupSearchName();
		File xmlFile = new File("src/main/resources/appliances_db.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName(groupName);
			List<StudentInfo> students = new ArrayList<StudentInfo>();
			return students;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	public List<StudentInfo> getAll(){
		File xmlFile = new File("src/main/resources/appliances_db.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();
			Node node = document.getDocumentElement();
			List<StudentInfo> students = new ArrayList<StudentInfo>();
			node = node.getChildNodes().item(1);

			return students;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public void addClient(List<String[]> parameters) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = newDocumentBuilder.parse("src/main/resources/client_db.xml");
		Element clientElement = doc.createElement("Client");
		Element root = doc.getDocumentElement();
		for (String[] parameter: parameters){
			System.out.println(parameter[0]);
			Element element = doc.createElement(parameter[0]);
			element.setTextContent(parameter[1]);
			clientElement.appendChild(element);
		}
		root.appendChild(clientElement);

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		Source source = new DOMSource(doc);
		Result result = new StreamResult("src/main/resources/client_db.xml");
		transformer.transform (source, result);
	}
}

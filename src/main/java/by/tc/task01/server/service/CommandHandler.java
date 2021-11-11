package by.tc.task01.server.service;

import by.tc.task01.server.entity.criteria.ClientCriteria;
import by.tc.task01.server.entity.criteria.SearchCriteria;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class CommandHandler extends Thread{
    private String fullCommand;
    private ServerLogic serverLogic;
    private ClientCriteria clientCriteria;
    public CommandHandler(String command, ServerLogic serverLogic, ClientCriteria clientCriteria){
        this.fullCommand = command;
        this.serverLogic = serverLogic;
        this.clientCriteria = clientCriteria;
    }

    public void run(){
        String command;
        System.out.println(fullCommand);
        int index = 0;
        if (fullCommand.contains("[")){
            index = fullCommand.indexOf("[");
            command = fullCommand.substring(0, index);
        }
        else {
            command = fullCommand;
        }
        switch (command) {
            case "EXIT" -> {
                try {
                    serverLogic.stopConnection();
                    serverLogic.startConnection();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
            case "REG" -> {
                int newIndex = fullCommand.indexOf("]", index);
                String name = fullCommand.substring(index+1, newIndex);
                index = fullCommand.indexOf("[", newIndex);
                newIndex = fullCommand.indexOf("]", index);
                String password = fullCommand.substring(index+1, newIndex);
                index = fullCommand.indexOf("[", newIndex);
                newIndex = fullCommand.indexOf("]", index);
                String allowance = fullCommand.substring(index+1, newIndex);
                ServiceFactory factory = ServiceFactory.getInstance();
                ServerService service = factory.getApplianceService();
                try {
                    service.addClient(name, password, allowance);
                } catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
                    e.printStackTrace();
                }
                try {
                    serverLogic.sendData("REG Name: " + name + ", Password: " + password + ", allowance: " + allowance);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
            case "GET" -> {
                ServiceFactory factory = ServiceFactory.getInstance();
                ServerService service = factory.getApplianceService();
                service.getAll();
            }
        };
    }
}

package by.tc.task01.server.service;

import by.tc.task01.server.entity.ClientInfo;
import by.tc.task01.server.entity.StudentInfo;
import by.tc.task01.server.entity.criteria.SearchCriteria;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public class CommandHandler extends Thread{
    private String fullCommand;
    private ServerLogic serverLogic;
    public CommandHandler(String command, ServerLogic serverLogic){
        this.fullCommand = command;
        this.serverLogic = serverLogic;
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
                boolean isAdded = false;
                try {
                    isAdded = service.addClient(name, password, allowance);
                } catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
                    e.printStackTrace();
                }
                if (isAdded) {
                    try {
                        serverLogic.sendData("REG Name: " + name + ", Password: " + password + ", allowance: " + allowance+"\n");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    serverLogic.getClientInfo().setAllowance(allowance);
                    serverLogic.getClientInfo().setName(name);
                }
                else{
                    try {
                        serverLogic.sendData("TRY AGAIN\n");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            case "LOGIN" -> {
                int newIndex = fullCommand.indexOf("]", index);
                String name = fullCommand.substring(index+1, newIndex);
                index = fullCommand.indexOf("[", newIndex);
                newIndex = fullCommand.indexOf("]", index);
                String password = fullCommand.substring(index+1, newIndex);
                serverLogic.getClientCriteria().add(SearchCriteria.Client.name.getEnumName(), name);
                serverLogic.getClientCriteria().add(SearchCriteria.Client.password.getEnumName(), password);
                ServiceFactory factory = ServiceFactory.getInstance();
                ServerService service = factory.getApplianceService();
                ClientInfo clientInfo = service.getClient(serverLogic.getClientCriteria());
                if (clientInfo!=null) {
                    serverLogic.getClientInfo().setName(clientInfo.getName());
                    serverLogic.getClientInfo().setAllowance(clientInfo.getAllowance());
                    try {
                        serverLogic.sendData("LOGIN Name: " + serverLogic.getClientInfo().getName() + ", allowance: " + serverLogic.getClientInfo().getAllowance() + "\n");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    try {
                        serverLogic.sendData("TRY AGAIN\n");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            case "GET" -> {
                if (!serverLogic.getClientInfo().getAllowance().equals("")) {
                    ServiceFactory factory = ServiceFactory.getInstance();
                    ServerService service = factory.getApplianceService();
                    List<StudentInfo> studentInfoList = service.getAll(serverLogic.getClientCriteria());
                    String data = "";
                    if (!studentInfoList.isEmpty()) {
                        for (StudentInfo studentInfo : studentInfoList) {
                            data = data + "Name: " + studentInfo.getName() + ", AverageScore: " + studentInfo.getAverageScore() + "\n";
                        }
                        try {
                            serverLogic.sendData(data);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        try {
                            serverLogic.sendData("TRY AGAIN\n");
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    try {
                        serverLogic.sendData("Not enough rights\n");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            case "EDIT" ->{
                 if (serverLogic.getClientInfo().getAllowance().equals("EDIT") || serverLogic.getClientInfo().getAllowance().equals("ADD")){
                     int newIndex = fullCommand.indexOf("]", index);
                     String name = fullCommand.substring(index+1, newIndex);
                     index = fullCommand.indexOf("[", newIndex);
                     newIndex = fullCommand.indexOf("]", index);
                     String newName = fullCommand.substring(index+1, newIndex);
                     index = fullCommand.indexOf("[", newIndex);
                     newIndex = fullCommand.indexOf("]", index);
                     String averageScore = fullCommand.substring(index+1, newIndex);
                     ServiceFactory factory = ServiceFactory.getInstance();
                     ServerService service = factory.getApplianceService();
                     boolean isEdit = service.regStudent(name, newName, averageScore);
                     if (isEdit){
                         try {
                             serverLogic.sendData("EDIT Name: " + name + ", newName: " + newName + ", averageScore: " + averageScore + "\n");
                         } catch (IOException | InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                     else{
                         try {
                             serverLogic.sendData("TRY AGAIN\n");
                         } catch (IOException | InterruptedException e) {
                             e.printStackTrace();
                         }
                     }
                 }
                 else{
                     try {
                         serverLogic.sendData("Not enough rights\n");
                     } catch (IOException | InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
            }
            case "ADD" -> {
                if (serverLogic.getClientInfo().getAllowance().equals("ADD")) {
                    int newIndex = fullCommand.indexOf("]", index);
                    String name = fullCommand.substring(index + 1, newIndex);
                    index = fullCommand.indexOf("[", newIndex);
                    newIndex = fullCommand.indexOf("]", index);
                    String averageScore = fullCommand.substring(index + 1, newIndex);
                    ServiceFactory factory = ServiceFactory.getInstance();
                    ServerService service = factory.getApplianceService();
                    boolean isAdded = false;
                    try {
                        isAdded = service.addStudent(name, averageScore);
                    } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
                        e.printStackTrace();
                    }
                    if (isAdded) {
                        try {
                            serverLogic.sendData("ADD Name: " + name + ", AverageScore: " + averageScore + "\n");
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            serverLogic.sendData("TRY AGAIN\n");
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    try {
                        serverLogic.sendData("Not enough rights\n");
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}

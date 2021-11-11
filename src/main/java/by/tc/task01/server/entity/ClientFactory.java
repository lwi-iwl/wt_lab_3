package by.tc.task01.server.entity;

import java.util.List;

public class ClientFactory {
    public ClientInfo getClientInfo(List<String> parameters){
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setName(parameters.get(0));
        clientInfo.setAllowance(parameters.get(2));
        return clientInfo;
    }
}

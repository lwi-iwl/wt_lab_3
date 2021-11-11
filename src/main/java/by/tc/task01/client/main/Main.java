package by.tc.task01.client.main;

import by.tc.task01.client.clientconsole.CommandReader;
import by.tc.task01.client.service.ClientLogic;
import by.tc.task01.server.service.ServerLogic;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ClientLogic clientLogic = new ClientLogic();
        clientLogic.startClient();
    }
}

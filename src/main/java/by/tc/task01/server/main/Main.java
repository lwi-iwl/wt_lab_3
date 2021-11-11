package by.tc.task01.server.main;

import by.tc.task01.server.service.ServerLogic;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		ServerLogic serverLogic = new ServerLogic();
		serverLogic.startConnection();
	}

}

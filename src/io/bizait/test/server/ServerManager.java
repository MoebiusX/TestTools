package io.bizait.test.server;

public class ServerManager {
	
	public static void main (String [] args) {
		Server server = new Server(20000);
		new Thread(server).start();
		
		try {
		    Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop();
	}
}
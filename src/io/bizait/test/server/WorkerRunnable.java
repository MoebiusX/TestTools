package io.bizait.test.server;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

/**

 */
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;
    public static int receivedReq = 0;
    
    private synchronized int count() {
    	return ++receivedReq;
    }

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
        	int reqID = count();
        	System.out.println("Received request: " + reqID);
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + this.serverText + " - " + reqID + "").getBytes());
            Thread.sleep(10 * 1000);
            long time2 = System.currentTimeMillis();
            output.close();
            input.close();
            System.out.println("Request " + reqID + " processed in " + (time2-time) + " ms ");
        } catch (Exception e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}
package ru.job4j.io;

import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import org.slf4j.Logger;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String inText;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        inText = in.readLine().split(" ")[1];
                        switch (inText) {
                            case ("/?msg=Hello"):
                                out.write("Hello".getBytes());
                                break;
                            case ("/?msg=Exit"):
                                server.close();
                                break;
                            default:
                                out.write("What".getBytes());
                        }
                    out.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("IOException in log example", e);
        }
    }
}

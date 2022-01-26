package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        String regex = "^-[^=]+=Bye[^=]+";
        Predicate<String> filter = line -> Pattern.matches(regex, line);
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {

                        if ("/?msg=Bye".equals((str.split(" ")[1]))) {
                            server.close();
                            break;
                        }
                        System.out.println(str);
                    }
                    out.flush();
                }
            }
        }
    }
}

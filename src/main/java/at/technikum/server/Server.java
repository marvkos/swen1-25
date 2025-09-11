package at.technikum.server;

import at.technikum.application.common.Application;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

    private HttpServer httpServer;
    private final int port;
    private final Application application;

    public Server(int port, Application application) {
        this.port = port;
        this.application = application;
    }

    public void start() {
        try {
            this.httpServer = HttpServer.
                    create(
                            new InetSocketAddress(
                                    "localhost",
                                    this.port
                            ),
                            0
                    );
            this.httpServer.createContext(
                    "/",
                    new Handler(this.application)
            );
            this.httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package at.technikum.server;

import at.technikum.application.common.Application;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class Handler implements HttpHandler {

    private final Application application;

    public Handler(Application application) {
        this.application = application;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // create Request object
        // give Request to Application
        // receive Response object
        // send Response to client
    }
}

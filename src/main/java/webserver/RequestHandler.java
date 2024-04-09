package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import webserver.handler.Handler;
import webserver.request.HttpRequest;
import webserver.request.HttpRequestParser;
import webserver.response.FileView;
import webserver.response.HttpResponse;
import webserver.response.HttpResponseRender;
import webserver.response.HttpStatus;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private final HttpRequestParser parser;
    private final HttpResponseRender render;
    private final Socket connection;
    private final Handler handler;

    public RequestHandler(Socket connectionSocket, Handler handler) {
        this.parser = new HttpRequestParser();
        this.render = new HttpResponseRender();
        this.connection = connectionSocket;
        this.handler = handler;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                     connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            HttpRequest request = parser.parseRequest(in);
            DataOutputStream dos = new DataOutputStream(out);
            HttpResponse response = handler.handle(request);
            render.render(dos, response);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

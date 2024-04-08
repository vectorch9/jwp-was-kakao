package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import webserver.request.HttpRequest;
import webserver.request.HttpRequestParser;
import webserver.response.FileSelector;
import webserver.response.FileView;
import webserver.response.HttpResponse;
import webserver.response.HttpResponseRender;
import webserver.response.HttpStatus;

public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private final HttpRequestParser parser;
    private final HttpResponseRender render;
    private final FileView templateView;
    private final FileView staticView;
    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.parser = new HttpRequestParser();
        this.render = new HttpResponseRender();
        this.staticView = new FileView("./templates");
        this.templateView = new FileView("./static");
        this.connection = connectionSocket;
    }

    public void run() {
        logger.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // TODO 사용자 요청에 대한 처리는 이 곳에 구현하면 된다.
            HttpRequest request = parser.parseRequest(in);
            DataOutputStream dos = new DataOutputStream(out);
            HttpResponse response = getResponse(request);
            render.render(dos, response);
        } catch (IOException | URISyntaxException e) {
            logger.error(e.getMessage());
        }
    }

    private HttpResponse getResponse(HttpRequest request) throws IOException, URISyntaxException {
        String path = request.getPath();
        // TODO 객체지향적으로, View에 exists를 먼저?
        HttpResponse response = templateView.readFromPath(path);
        if (response.getStatus() == HttpStatus.NOT_FOUND) {
            response = staticView.readFromPath(path);
        }
        return response;
    }

    private void response200Header(DataOutputStream dos, int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void responseBody(DataOutputStream dos, byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

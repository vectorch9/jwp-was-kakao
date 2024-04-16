package webserver;

import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.ListUserHandler;
import api.LoginHandler;
import api.ProfileHandler;
import api.RootHandler;
import api.CreateUserHandler;
import db.DataBase;
import model.User;
import webserver.handler.Handler;
import webserver.handler.HandlerComposite;
import webserver.handler.HttpRequestConverter;
import webserver.parser.HttpRequestBodyParser;
import webserver.request.HttpMethod;
import webserver.response.HttpResponseRenderer;

public class WebApplicationServer {
    private static final Logger logger = LoggerFactory.getLogger(WebApplicationServer.class);
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        int port;
        if (args == null || args.length == 0) {
            port = DEFAULT_PORT;
        } else {
            port = Integer.parseInt(args[0]);
        }

        Handler handler = handlerComposite();
        HttpRequestConverter parser = new HttpRequestConverter();
        HttpResponseRenderer renderer = new HttpResponseRenderer();

        mockData();

        // 서버소켓을 생성한다. 웹서버는 기본적으로 8080번 포트를 사용한다.
        try (ServerSocket listenSocket = new ServerSocket(port)) {
            logger.info("Web Application Server started {} port.", port);

            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
                Thread thread = new Thread(new RequestHandler(parser, renderer, connection, handler));
                thread.start();
            }
        }
    }

    private static HandlerComposite handlerComposite() {
        HandlerComposite composite = new HandlerComposite();
        HttpRequestBodyParser parser = new HttpRequestBodyParser();

        composite.addHandler(HttpMethod.POST, "/user/create", new CreateUserHandler(parser));
        composite.addHandler(HttpMethod.POST, "/user/login", new LoginHandler(parser));
        composite.addHandler(HttpMethod.GET, "/user/list.html", new ListUserHandler());
        composite.addHandler(HttpMethod.GET, "/user/profile.html", new ProfileHandler());
        composite.addHandler(HttpMethod.GET, "/", new RootHandler());
        return composite;
    }

    private static void mockData() {
        DataBase.addUser(new User("admin", "admin", "관리자", "admin@email.com"));
        DataBase.addUser(new User("qwe", "qwe", "qwe", "qwe@email.com"));
    }
}

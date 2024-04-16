package webserver.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class SessionStore {

    private static final Map<String, Session> sessions = new HashMap<>();

    private SessionStore() {
    }

    public static Session createSession() {
        Session session = new Session(UUID.randomUUID().toString());
        sessions.put(session.getId(), session);
        return session;
    }

    public static Session getSession(String id) {
        return sessions.get(id);
    }
}

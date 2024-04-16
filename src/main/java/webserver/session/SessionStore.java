package webserver.session;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    public static Optional<Session> getSession(String id) {
        if (id == null || !sessions.containsKey(id)) {
            return Optional.empty();
        }
        return Optional.of(sessions.get(id));
    }
}

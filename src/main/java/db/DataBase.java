package db;

import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

import model.User;

public class DataBase {
    private static Map<String, User> users = Maps.newHashMap();

    public static void addUser(User user) {
        users.put(user.getUserId(), user);
    }

    public static User findUserById(String userId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        return users.get(userId);
    }

    public static Collection<User> findAll() {
        return users.values();
    }
}

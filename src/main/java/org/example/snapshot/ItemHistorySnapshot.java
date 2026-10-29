package org.example.snapshot;

import org.example.user.User;

import java.util.ArrayDeque;
import java.util.Optional;

public class ItemHistorySnapshot {

    private ItemHistorySnapshot() {
        throw new AssertionError("Класс-сервис не должен инстанцироваться!");
    }

    public static Snapshot createSnapshot(String string, Integer userId) {
        User user = findById(userId);
        ArrayDeque<Snapshot> userHistory = user.getSnapshots();
        if (userHistory.size() >= 10) {
            removeHistory(userHistory);
        }
        char[] chars = string.toCharArray();
        Snapshot snapshot = Snapshot.of(chars, string.length());
        addHistory(userHistory, snapshot);
        return snapshot;
    }

    public static Snapshot undo(Integer userId) {
        User user = findById(userId);
        ArrayDeque<Snapshot> userHistory = user.getSnapshots();
        if (userHistory.isEmpty()) {
            return null;
        }
        return userHistory.pop();
    }

    public static void clearHistory (Integer userId) {
        User user = findById(userId);
        ArrayDeque<Snapshot> snapshots = new ArrayDeque<>();
        user.setSnapshots(snapshots);
    }

    private static User findById (Integer id) {
        return Optional.ofNullable(User.userDb.get(id))
                .orElseThrow(()-> new IllegalArgumentException("User " + id + " not found"));

    }

    private static void removeHistory(ArrayDeque<Snapshot> snapshots) {
        snapshots.removeLast();
    }

    private static void addHistory(ArrayDeque<Snapshot> snapshots, Snapshot snapshot) {
        snapshots.push(snapshot);
    }
}

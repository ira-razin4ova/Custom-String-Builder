package org.example.string_builder.user;

import org.example.string_builder.snapshot.Snapshot;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {

    public static Map<Integer, User> userDb = new HashMap<>();

    private Integer id;
    private String name;

    private ArrayDeque<Snapshot> snapshots;

    private User(Integer id, String name) {
        if (id == null || name == null) {
            throw new IllegalArgumentException("Invalid data");
        }
        this.id = id;
        this.name = name;
        this.snapshots = new ArrayDeque<>();
    }

    public static User of (Integer id, String name){
        User user = new User(id, name);
        userDb.put(id, user);
        return  user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayDeque<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(ArrayDeque<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", snapshots=" + snapshots +
                '}';
    }
}

package org.example;

import org.example.snapshot.ItemHistorySnapshot;
import org.example.snapshot.Snapshot;
import org.example.user.User;
public class Main {
    public static void main(String[] args) {
        System.out.println("=== СТАРТ ЛИНЕЙНОГО ТЕСТА ===");

        User user = User.of(1, "Ирина");
        System.out.println("Создан пользователь: " + user);

        System.out.println("\n--- Изменение 1: Меняем состояние на 'Привет' ---");
        Snapshot res1 = ItemHistorySnapshot.createSnapshot("Привет", 1);
        String cleanString1 = new String(res1.getString());
        System.out.println("Результат метода: " + cleanString1);

        System.out.println("\n--- Изменение 2: Меняем состояние на 'Привет Мир' ---");
        Snapshot res2 = ItemHistorySnapshot.createSnapshot("Привет Мир", 1);
        String cleanString2 = new String(res2.getString());
        System.out.println("Результат метода: " + cleanString2);

        System.out.println("\n--- Изменение 3: Меняем состояние на 'Доброе утро!' ---");
        Snapshot res3 = ItemHistorySnapshot.createSnapshot("Доброе утро!", 1);
        String cleanString3 = new String(res3.getString());
        System.out.println("Результат метода: " + cleanString3);

        System.out.println("\n--- Проверяем историю внутри User ---");
        int historySize = user.getSnapshots().size();
        System.out.println("Количество снимков в истории: " + historySize);

        System.out.println("Содержимое истории (от свежих к старым):");
        for (Snapshot snap : user.getSnapshots()) {
            String savedText = new String(snap.getString());
            System.out.println(" -> [Снимок]: " + savedText);
        }

        System.out.println("\n--- Нажимаем Ctrl + Z (вызываем undo) ---");
        Snapshot undoneSnapshot = ItemHistorySnapshot.undo(1);

        if (undoneSnapshot != null) {
            System.out.println("⏪ Мы успешно откатились назад!");
            System.out.println("Содержимое отмененного снимка: " + new String(undoneSnapshot.getString()));
        }

        System.out.println("\nРазмер истории после отмены: " + user.getSnapshots().size());
        System.out.println("Содержимое истории (от свежих к старым):");
        for (Snapshot snap : user.getSnapshots()) {
            String savedText = new String(snap.getString());
            System.out.println(" -> [Снимок]: " + savedText);
        }

        System.out.println("\n--- Чистим историю ---");
        ItemHistorySnapshot.clearHistory(1);
        int history = user.getSnapshots().size();
        System.out.println("Количество снимков в истории: " + history);

        System.out.println("\n=== ТЕСТ ЗАВЕРШЕН УСПЕШНО ===");
    }
}

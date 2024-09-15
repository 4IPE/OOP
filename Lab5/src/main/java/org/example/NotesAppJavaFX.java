package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NotesAppJavaFX extends Application {

    private ObservableList<String> notes;  // Список для хранения заметок
    private ListView<String> notesListView; // Элемент для отображения списка заметок
    private TextField noteInput;            // Поле ввода для новой заметки
    private static final String FILE_NAME = "notes.txt"; // Имя файла для сохранения заметок

    @Override
    public void start(Stage primaryStage) {
        // Инициализация списка заметок
        notes = FXCollections.observableArrayList();
        loadNotesFromFile(); // Загрузка заметок из файла

        // Инициализация элементов интерфейса
        notesListView = new ListView<>(notes);  // Создаем ListView для отображения заметок
        noteInput = new TextField();            // Поле для ввода новой заметки
        noteInput.setPromptText("Введите заметку...");

        // Кнопка для добавления новой заметки
        Button addButton = new Button("Сохранить");
        addButton.setOnAction(e -> addNote());

        // Кнопка для удаления выбранной заметки
        Button deleteButton = new Button("Удалить");
        deleteButton.setOnAction(e -> deleteNote());

        // Кнопка для изменения выбранной заметки
        Button editButton = new Button("Изменить");
        editButton.setOnAction(e -> editNote());

        // Компоновка элементов интерфейса
        HBox inputBox = new HBox(10, noteInput, addButton, deleteButton, editButton);  // Горизонтальный контейнер для ввода и кнопок
        VBox root = new VBox(10, notesListView, inputBox);  // Основной вертикальный контейнер

        // Настройка сцены
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Заметки");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Добавление обработчика события для сохранения заметок при закрытии приложения
        primaryStage.setOnCloseRequest(event -> saveNotesToFile());
    }

    // Метод для добавления новой заметки
    private void addNote() {
        String note = noteInput.getText();  // Получаем текст заметки
        if (!note.trim().isEmpty()) {       // Если поле не пустое
            notes.add(note);                // Добавляем заметку в список
            noteInput.clear();              // Очищаем поле ввода
        }
    }

    // Метод для удаления выбранной заметки
    private void deleteNote() {
        String selectedNote = notesListView.getSelectionModel().getSelectedItem();  // Получаем выбранную заметку
        if (selectedNote != null) {
            notes.remove(selectedNote);  // Удаляем выбранную заметку
        }
    }

    // Метод для изменения выбранной заметки
    private void editNote() {
        String selectedNote = notesListView.getSelectionModel().getSelectedItem();  // Получаем выбранную заметку
        if (selectedNote != null) {
            noteInput.setText(selectedNote);  // Загружаем текст заметки в поле ввода
            deleteNote();  // Удаляем заметку из списка для последующего обновления
        }
    }

    // Метод для сохранения заметок в файл
    private void saveNotesToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (String note : notes) {
                writer.write(note + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Обработка исключения
        }
    }

    // Метод для загрузки заметок из файла
    private void loadNotesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line); // Добавление загруженной заметки в список
            }
        } catch (IOException e) {
            // Если файл не найден, ничего не делаем
        }
    }

    public static void main(String[] args) {
        launch(args);  // Запуск приложения
    }
}

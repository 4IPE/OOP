package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestAppSwing extends JFrame {

    private final List<Question> questions; // Список вопросов
    private int currentQuestionIndex = 0; // Индекс текущего вопроса
    private int score = 0; // Количество правильных ответов

    private JLabel questionLabel; // Метка для отображения вопроса
    private JRadioButton[] answerButtons; // Радиокнопки для выбора вариантов ответов
    private ButtonGroup buttonGroup; // Группа для объединения радиокнопок
    private JButton nextButton; // Кнопка "Далее"

    public TestAppSwing() {
        setTitle("Тестирование");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questions = loadQuestionsFromFile("test_questions.txt");

        if (questions.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Не удалось загрузить тестовые вопросы.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        initUI();
        displayQuestion(currentQuestionIndex);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Вопрос");
        add(questionLabel, BorderLayout.NORTH);

        JPanel answerPanel = new JPanel();
        answerPanel.setLayout(new GridLayout(3, 1));
        answerButtons = new JRadioButton[3];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new JRadioButton();
            buttonGroup.add(answerButtons[i]);
            answerPanel.add(answerButtons[i]);
        }
        add(answerPanel, BorderLayout.CENTER);

        nextButton = new JButton("Далее");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });
        add(nextButton, BorderLayout.SOUTH);
    }

    private void displayQuestion(int questionIndex) {
        if (questionIndex >= questions.size()) {
            showResult();
            return;
        }

        Question currentQuestion = questions.get(questionIndex);
        questionLabel.setText(currentQuestion.question());
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(currentQuestion.answers()[i]);
        }
        buttonGroup.clearSelection(); // Сбрасываем выбор
    }

    private void checkAnswer() {
        int selectedAnswerIndex = -1;
        for (int i = 0; i < answerButtons.length; i++) {
            if (answerButtons[i].isSelected()) {
                selectedAnswerIndex = i;
                break;
            }
        }

        if (selectedAnswerIndex == -1) {
            JOptionPane.showMessageDialog(this, "Выберите ответ.", "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Question currentQuestion = questions.get(currentQuestionIndex);
        if (currentQuestion.correctAnswer().equals(currentQuestion.answers()[selectedAnswerIndex])) {
            score++;
        }

        currentQuestionIndex++;
        displayQuestion(currentQuestionIndex);
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Тест завершён! Правильных ответов: " + score + " из " + questions.size());
        System.exit(0);
    }

    private List<Question> loadQuestionsFromFile(String fileName) {
        List<Question> questionsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String questionText = parts[0];
                    String[] answers = {parts[1], parts[2], parts[3]};
                    String correctAnswer = parts[4];
                    questionsList.add(new Question(questionText, answers, correctAnswer));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionsList;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestAppSwing().setVisible(true);
            }
        });
    }
}

// Класс для хранения информации о вопросе
record Question(String question, String[] answers, String correctAnswer) {
}

package Main;

import dao.QuizDAO;
import dao.QuizAttemptDAO;
import Model.Question;
import Model.Quiz;
import Model.QuizAttempt;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class QuizTaking {
    public static void main(String[] args) {
        QuizDAO quizDAO = new QuizDAO();
        QuizAttemptDAO attemptDAO = new QuizAttemptDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();

        List<Quiz> quizzes = quizDAO.getAllQuizzes();
        System.out.println("Available quizzes:");
        for (Quiz q : quizzes) {
            System.out.println(q.getQuizId() + ") " + q.getTitle());
        }

        System.out.print("Select quiz ID to take: ");
        int quizId = scanner.nextInt();
        scanner.nextLine(); // consume newline

        String sql = "SELECT * FROM questions WHERE quiz_id = ?";
        int score = 0;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, quizId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("\n" + rs.getString("question_text"));
                System.out.println("A) " + rs.getString("option_a"));
                System.out.println("B) " + rs.getString("option_b"));
                System.out.println("C) " + rs.getString("option_c"));
                System.out.println("D) " + rs.getString("option_d"));

                System.out.print("Your answer (A/B/C/D): ");
                String answer = scanner.nextLine().trim().toUpperCase();

                if (answer.equals(rs.getString("correct_option"))) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect! Correct answer: " + rs.getString("correct_option"));
                }
            }

            System.out.println("\nYour final score: " + score);

            // Save attempt
            QuizAttempt attempt = new QuizAttempt(userId, quizId, score);
            boolean saved = attemptDAO.saveAttempt(attempt);
            if (saved) {
                System.out.println(" Attempt saved successfully!");
            } else {
                System.out.println(" Failed to save attempt.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


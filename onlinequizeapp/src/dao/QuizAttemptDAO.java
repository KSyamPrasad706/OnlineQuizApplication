package dao;

import Model.QuizAttempt;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuizAttemptDAO {

    public boolean saveAttempt(QuizAttempt attempt) {
        String sql = "INSERT INTO quiz_attempts (user_id, quiz_id, score) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, attempt.getUserId());
            pstmt.setInt(2, attempt.getQuizId());
            pstmt.setInt(3, attempt.getScore());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println(" Error saving quiz attempt: " + e.getMessage());
            return false;
        }
    }
}

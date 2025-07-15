package Main;

import dao.QuizDAO;
import Model.Quiz;
import Model.Question;

import java.util.List;
import java.util.Scanner;

public class TestQuizAdmin {
    public static void main(String[] args) {
        QuizDAO quizDAO = new QuizDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Admin Quiz Management ===");
        System.out.println("1) Create Quiz");
        System.out.println("2) Add Question to Quiz");
        System.out.println("3) View Quizzes");
        System.out.print("Choose option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Quiz title: ");
                String title = scanner.nextLine();
                System.out.print("Description: ");
                String desc = scanner.nextLine();

                Quiz quiz = new Quiz(title, desc);
                boolean quizCreated = quizDAO.createQuiz(quiz);
                if (quizCreated) {
                    System.out.println(" Quiz created successfully!");
                } else {
                    System.out.println(" Failed to create quiz.");
                }
                break;

            case 2:
                System.out.print("Quiz ID to add question to: ");
                int quizId = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Question text: ");
                String qText = scanner.nextLine();

                System.out.print("Option A: ");
                String a = scanner.nextLine();
                System.out.print("Option B: ");
                String b = scanner.nextLine();
                System.out.print("Option C: ");
                String c = scanner.nextLine();
                System.out.print("Option D: ");
                String d = scanner.nextLine();

                System.out.print("Correct Option (A/B/C/D): ");
                String correct = scanner.nextLine();

                Question question = new Question(quizId, quizId, qText, a, b, c, d, correct);
                boolean questionAdded = quizDAO.addQuestion(question);
                if (questionAdded) {
                    System.out.println(" Question added successfully!");
                } else {
                    System.out.println(" Failed to add question.");
                }
                break;

            case 3:
                List<Quiz> quizzes = quizDAO.getAllQuizzes();
                System.out.println("=== Quizzes ===");
                for (Quiz q : quizzes) {
                    System.out.println("ID: " + q.getQuizId() + ", Title: " + q.getTitle() + ", Description: " + q.getDescription());
                }
                break;

            default:
                System.out.println("Invalid option.");
        }
    }
}

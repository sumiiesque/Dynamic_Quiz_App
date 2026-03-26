package quiz.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import quiz.SceneManager;
import quiz.manager.QuestionManager;
import quiz.model.Question;
import quiz.model.QuizSession;

import java.util.List;

// Validates name input, seeds the question bank, and starts the quiz.

public class StartController {

    @FXML
    private TextField nameField;
    @FXML
    private Label errorLabel;

    /** Called when user clicks "Start Quiz" */
    @FXML
    private void handleStart() {
        String name = nameField.getText().trim();

        // Validate — name must not be empty
        if (name.isEmpty()) {
            showError("Please enter your name!");
            shakeNode(nameField);
            return;
        }

        // Build the question bank with sample Java questions
        QuestionManager qm = new QuestionManager();
        seedQuestions(qm);

        // Pick 10 random questions for this session
        List<Question> questions = qm.getRandom(10);
        QuizSession session = new QuizSession(1, 1, questions);

        // Store shared state and switch to quiz screen
        SceneManager.setPlayerName(name);
        SceneManager.setCurrentSession(session);
        SceneManager.switchScene("quiz.fxml");
    }

    /** Show an error message below the input */
    private void showError(String msg) {
        errorLabel.setText(msg);
        errorLabel.setVisible(true);
        errorLabel.setManaged(true);
    }

    /** Shake animation for invalid input feedback */
    private void shakeNode(Node node) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(50), node);
        tt.setFromX(0);
        tt.setByX(10);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.setOnFinished(e -> node.setTranslateX(0));
        tt.play();
    }

    // 15 questions data

    private void seedQuestions(QuestionManager qm) {
        qm.addQues(new Question(1,
                "What does JVM stand for?",
                new String[] { "Java Virtual Machine", "Java Variable Method",
                        "Java Visual Machine", "Java Verified Module" },
                0, Question.Difficulty.EASY, Question.Category.JAVA_BASICS,
                "JVM stands for Java Virtual Machine, which runs Java bytecode."));

        qm.addQues(new Question(2,
                "Which keyword is used to create a class in Java?",
                new String[] { "struct", "class", "define", "object" },
                1, Question.Difficulty.EASY, Question.Category.JAVA_BASICS,
                "The 'class' keyword is used to define a class in Java."));

        qm.addQues(new Question(3,
                "What is the default value of an int variable in Java?",
                new String[] { "null", "1", "0", "undefined" },
                2, Question.Difficulty.EASY, Question.Category.JAVA_BASICS,
                "The default value of int in Java is 0."));

        qm.addQues(new Question(4,
                "Which OOP concept binds data and methods together?",
                new String[] { "Polymorphism", "Inheritance",
                        "Encapsulation", "Abstraction" },
                2, Question.Difficulty.EASY, Question.Category.OOP,
                "Encapsulation binds data and methods within a single unit."));

        qm.addQues(new Question(5,
                "What is method overloading an example of?",
                new String[] { "Runtime Polymorphism", "Compile-time Polymorphism",
                        "Encapsulation", "Abstraction" },
                1, Question.Difficulty.MEDIUM, Question.Category.OOP,
                "Method overloading is resolved at compile time."));

        qm.addQues(new Question(6,
                "Which keyword prevents a method from being overridden?",
                new String[] { "static", "abstract", "final", "private" },
                2, Question.Difficulty.MEDIUM, Question.Category.OOP,
                "The 'final' keyword prevents method overriding in a subclass."));

        qm.addQues(new Question(7,
                "Can an abstract class have a constructor in Java?",
                new String[] { "Yes", "No",
                        "Only static constructors", "Only with no abstract methods" },
                0, Question.Difficulty.MEDIUM, Question.Category.OOP,
                "Yes, abstract classes can have constructors called during subclass instantiation."));

        qm.addQues(new Question(8,
                "Which interface does ArrayList implement?",
                new String[] { "Set", "Map", "List", "Queue" },
                2, Question.Difficulty.MEDIUM, Question.Category.COLLECTIONS,
                "ArrayList implements the List interface."));

        qm.addQues(new Question(9,
                "What is the default initial capacity of an ArrayList?",
                new String[] { "5", "8", "16", "10" },
                3, Question.Difficulty.MEDIUM, Question.Category.COLLECTIONS,
                "The default initial capacity of ArrayList is 10."));

        qm.addQues(new Question(10,
                "What is the average time complexity of HashMap.get()?",
                new String[] { "O(n)", "O(log n)", "O(1)", "O(n log n)" },
                2, Question.Difficulty.HARD, Question.Category.COLLECTIONS,
                "HashMap.get() has O(1) average time due to hashing."));

        qm.addQues(new Question(11,
                "Which block always executes regardless of exceptions?",
                new String[] { "try", "catch", "finally", "throw" },
                2, Question.Difficulty.MEDIUM, Question.Category.EXCEPTION_HANDLING,
                "The 'finally' block always executes whether or not an exception occurs."));

        qm.addQues(new Question(12,
                "Which of these is a checked exception?",
                new String[] { "NullPointerException", "ArrayIndexOutOfBoundsException",
                        "IOException", "ArithmeticException" },
                2, Question.Difficulty.HARD, Question.Category.EXCEPTION_HANDLING,
                "IOException is a checked exception that must be handled at compile time."));

        qm.addQues(new Question(13,
                "Which method is used to start execution of a thread?",
                new String[] { "run()", "start()", "execute()", "init()" },
                1, Question.Difficulty.HARD, Question.Category.MULTITHREADING,
                "start() creates a new thread and internally calls run()."));

        qm.addQues(new Question(14,
                "What happens when two threads wait for each other to release locks?",
                new String[] { "Starvation", "Race Condition", "Deadlock", "Livelock" },
                2, Question.Difficulty.MEDIUM, Question.Category.MULTITHREADING,
                "Deadlock occurs when threads are permanently blocked waiting for each other."));

        qm.addQues(new Question(15,
                "Which of the following is NOT a valid access modifier in Java?",
                new String[] { "public", "private", "friend", "protected" },
                2, Question.Difficulty.EASY, Question.Category.JAVA_BASICS,
                "'friend' is not an access modifier in Java — it exists in C++."));
    }
}

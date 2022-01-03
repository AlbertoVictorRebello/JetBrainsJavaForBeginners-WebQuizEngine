package engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebQuizEngine {
    private Question question = new Question("The Java Logo", "What is depicted on the Java logo?", new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"}, 2);

    public static void main(String[] args) {
        SpringApplication.run(WebQuizEngine.class, args);
    }

    @PostMapping("/api/quiz")
    public Feedback getQuiz(@RequestParam int answer) {

        return question.getFeedback(answer);
    }

    @GetMapping("/api/quiz")
    public Question getQuiz() {

        return question;
    }
}


class Question {
    private String title;
    private String text;
    private String[] options;
    private int correctAnswer;

    public Question() {}

    public Question(String title, String text, String [] options, int correctAnswer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Feedback getFeedback(int chosenOption) {
        Feedback feedback = new Feedback();
        if (this.correctAnswer == chosenOption) {
            feedback.setSuccess(true);
            feedback.setFeedback("Congratulations, you're right!");

        } else {
            feedback.setSuccess(false);
            feedback.setFeedback("Wrong answer! Please, try again.");
        }
        return feedback;
    }
}

class Feedback {
    private boolean success;
    private String feedback;

    public Feedback() {/* */ }

    public Feedback(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}


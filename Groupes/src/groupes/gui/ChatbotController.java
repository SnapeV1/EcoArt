package groupes.gui;

import groupes.services.ChatbotFunctions;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ChatbotController {

    @FXML
    private BorderPane borderPane;
    @FXML
    private TextArea mainTextArea;
    @FXML
    private TextField messageArea;
    @FXML
    private Button sendButtonbtn;

    private boolean convoStarted = false;
ChatbotFunctions CBF=new ChatbotFunctions();
    public void initialize() {
        mainTextArea.getStyleClass().add("text-area");
        messageArea.getStyleClass().add("text-field");
        sendButtonbtn.getStyleClass().add("button");
        messageArea.getStyleClass().add("message-text");

        addToTextArea('b', "Welcome to the Art and Communication Application Chatbot. Do you need any help?");
    }

    public void addToTextArea(char who, String addText) {
        if (who == 'b') {
            mainTextArea.appendText("\n[Bot] -> " + addText);
        } else {
            mainTextArea.appendText("\n[You] -> " + addText);
        }
    }

    @FXML
    public void sendButton() {
        String userMessage = messageArea.getText();
        addToTextArea('y', userMessage);

        if (!convoStarted) {
            if (CBF.isAffirmative(userMessage)) {
                convoStarted = true;
                addToTextArea('b', "Great! Let's explore the Art and Communication Application. Maybe start with asking for help!");
            } else {
                addToTextArea('b', "I didn't understand. Please say 'yes' to start.");
            }
        } else {
            // Handle user questions related to the application
            String botResponse = CBF.respondToUserInput(userMessage);
            addToTextArea('b', botResponse);
        }

        messageArea.clear();
    }

   

    
}
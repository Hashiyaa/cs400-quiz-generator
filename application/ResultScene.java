package application;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents the window to show the result of the quiz
 * 
 * @author Haochen Shi
 *
 */
public class ResultScene {
	
	private Stage stage; // to get access to the current stage
	
	/**
	 * This constructor passes the primary stage into the scene
	 * 
	 * @param primaryStage is the primary stage
	 */
	public ResultScene(Stage primaryStage) {
		stage = primaryStage;
	}
	
	/**
	 * This method returns the result scene
	 * 
	 * @return the result scene
	 */
	public Scene getScene() {
		BorderPane root = setBorderPane();	
		Scene scene = new Scene(root,400,200);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		return scene;
	}
	
	/**
	 * This helper method sets the elements and the layout in the border pane
	 * 
	 * @return the border pane
	 */
	private BorderPane setBorderPane() {
		// texts indicating the number of answered and correct questions 
		Text answeredQText = new Text("Number of Correct Questions: " + 
				QuestionScene.correctQuestionCount + " / " + QuestionScene.questionCount);
		Text correctQText = new Text("Number of Answered Questions: " +
				QuestionScene.finishedQuestionCount + " / " + QuestionScene.questionCount);
		
		// buttons
		Button takeNewQuiz = new Button("Take New Quiz");
		// create a new instance of MainMenuScene and set the button's action
		MainMenuScene mainMenu = new MainMenuScene(stage);
		takeNewQuiz.setOnAction(e -> {stage.setScene(mainMenu.getScene()); stage.show();});
		
		Button saveAns = new Button("Save Answers to a JSON file");
		
		Button exit = new Button("Exit without Saving");
		// set the button's action to show a warning message
		exit.setOnAction(e -> {
			// a new stage aside from the primary stage
			Stage popUpStage = new Stage();
			
			// a border pane to organize the elements
            BorderPane root = new BorderPane();
            root.setMaxSize(200, 100);
            
            // text of the warning message
            Text warningMessage = new Text("Are you sure you want to quit?");
            
            // buttons
            Button no = new Button("NO");
            // close the warning window
            no.setOnAction(e2 -> popUpStage.close());
            
            // terminate the program
            Button yes = new Button("YES");
            yes.setOnAction(e2 -> Platform.exit());
            
            // HBoxs
            HBox buttons = new HBox();
            buttons.getChildren().addAll(no, yes);
            buttons.setAlignment(Pos.CENTER); 
            buttons.setSpacing(20);
            
            // VBox
            VBox list = new VBox();
            list.getChildren().addAll(warningMessage, buttons);
            list.setAlignment(Pos.CENTER); 
            list.setSpacing(20);
            
            root.setCenter(list);
            // padding of the border pane
            root.setPadding(new Insets(15, 20, 10, 20));

            Scene warning = new Scene(root, 250, 200);
            popUpStage.setScene(warning);
            popUpStage.show();
		});
		
		// VBoxes
		VBox buttons = new VBox();
		buttons.getChildren().addAll(takeNewQuiz, saveAns, exit);
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);
		
		VBox list = new VBox();
		list.getChildren().addAll(answeredQText, correctQText, buttons);
		list.setAlignment(Pos.CENTER); 
		list.setSpacing(20);
		
		// the border pane
		BorderPane root = new BorderPane();
		root.setCenter(list);
		root.setPadding(new Insets(15, 20, 10, 20));
		
		return root;
	}
	
}

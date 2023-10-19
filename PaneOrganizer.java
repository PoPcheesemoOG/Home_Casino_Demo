/* Name: Paul Helske
 * Date: 10/19/2023
 */

import javafx.scene.layout.*;

public class PaneOrganizer {
	private BorderPane root;
	private VBox centerPane;
	
	public PaneOrganizer() {
		root = new BorderPane();
	}
	public PaneOrganizer(VBox vBox) {
		root = new BorderPane();
		centerPane = vBox;
		setBackground();
	}
	private void setBackground() {
		Pane topPane = new Pane();
		topPane.setStyle("-fx-background-color: darkred");
		
		Pane rightPane = new Pane();
		rightPane.setStyle("-fx-background-color: red");
		
	//	centerPane = new VBox();
		centerPane.setStyle("-fx-background-color: crimson");
		
		root.setTop(topPane);
		root.setRight(rightPane);
		root.setCenter(centerPane);
	}
	public Pane getRoot() {
		return root;
	}
}
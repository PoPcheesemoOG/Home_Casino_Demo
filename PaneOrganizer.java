/* Name: Paul Helske
 * Date: 10/19/2023
 */

import javafx.geometry.*;
import java.util.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class PaneOrganizer {
	private BorderPane root;
	private AnchorPane anchor;
	private VBox centerPane;
	private Pane rightPane;
	private Pane topPane;
	
	public PaneOrganizer() {
		anchor = new AnchorPane();
		root = new BorderPane();
		anchor.getChildren().add(root);
		anchor.setTopAnchor(root, 0.0);
		anchor.setBottomAnchor(root, 0.0);
		anchor.setRightAnchor(root, 0.0);
		anchor.setLeftAnchor(root, 0.0);
	}
	public PaneOrganizer(VBox vBox) {
		root = new BorderPane();
		anchor = new AnchorPane();
		anchor.getChildren().add(root);
		anchor.setTopAnchor(root, 0.0);
		anchor.setBottomAnchor(root, 0.0);
		anchor.setRightAnchor(root, 0.0);
		anchor.setLeftAnchor(root, 0.0);
		centerPane = vBox;
		setBackground();
	}
	private void setBackground() {
		topPane = new Pane();
		HBox hbox = new HBox(10);
		topPane.getChildren().add(hbox);
		topPane.setStyle("-fx-background-color: darkred");
		topPane.setPrefSize(1200, 100);
		
		rightPane = new Pane();
		rightPane.setStyle("-fx-background-color: red");
		rightPane.setPrefSize(150, 600);
		
		centerPane.setStyle("-fx-background-color: crimson");
		
		root.setTop(topPane);
		root.setRight(rightPane);
		root.setCenter(centerPane);
		
	}
	public AnchorPane getAnchor() {
		return anchor;
	}
	public void setAnchor(AnchorPane anchor) {
		this.anchor = anchor;
	}
	public Pane getRoot() {
		return root;
	}
	public VBox getCenterPane() {
		return centerPane;
	}
	public void setCenterPane(VBox centerPane) {
		this.centerPane = centerPane;
	}
	public Pane getRightPane() {
		return rightPane;
	}
	public void setRightPane(Pane rightPane) {
		this.rightPane = rightPane;
	}
	public Pane getTopPane() {
		return topPane;
	}
	public void setTopPane(Pane topPane) {
		this.topPane = topPane;
	}
	public void setRoot(BorderPane root) {
		this.root = root;
	}
}
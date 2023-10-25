/* Name: Paul Helske
 * Date: 10/17/2023
 */

import java.util.*;

import javax.swing.text.View;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.effect.*;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.application.*;


public class GUI extends Application {
	static double cardX;
	static double cardY;
	static double sceneX;
	static double sceneY;

	@Override

	public void start(Stage primaryStage) throws Exception {
		VBox vBox1 = new VBox();
		vBox1.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.setAlignment(Pos.TOP_LEFT);

		MenuBar menuBar = new MenuBar();		
		Menu menuGames = new Menu("Games");		
		menuBar.getMenus().add(menuGames);

		MenuItem menuItemPlayPoker = new MenuItem("Play Poker");
		MenuItem menuItemPlayKeno = new MenuItem("Play Keno");
		MenuItem menuItemExit = new MenuItem("Exit");

		menuGames.getItems().addAll(menuItemPlayPoker, menuItemPlayKeno, menuItemExit);

		menuItemPlayPoker.setOnAction(e -> {
			try {
				newPoker();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		menuItemPlayKeno.setOnAction(e -> {
			try {
				newKeno();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		menuItemExit.setOnAction(e -> System.exit(0));

		StackPane pane1 = new StackPane();
		vBox1.getChildren().add(menuBar);
		pane1.getChildren().addAll(vBox1);

		Scene menuScene = new Scene(pane1, 600, 300);

		primaryStage.setResizable(false);
		primaryStage.setTitle("Home Casino Main Menu");
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}

	private void playKeno() {
	
	}
	private void playPoker() {
		
	}
	private void buttonSetup(Button bt, Color color) {
		bt.setPrefSize(130, 50);
		bt.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 16.0));
		bt.setBackground(new Background(new BackgroundFill(color,
				CornerRadii.EMPTY, Insets.EMPTY)));
		bt.setMaxSize(130, 50);
		bt.setMinSize(130, 50);
	}
	public static void main(String[] args) {
		launch (args);
	}
	private void newPoker() throws Exception {
		MenuBar menuBarPoker = new MenuBar();		
		Menu menuExit1 = new Menu("Exit");
		MenuItem menuItemExit1 = new MenuItem("Exit");
		menuBarPoker.getMenus().add(menuExit1);
		menuExit1.getItems().add(menuItemExit1);

		VBox vBox1 = new VBox(20);
		VBox vBox2 = new VBox(10);
		vBox1.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.getChildren().addAll(menuBarPoker);
		
		List<GameCard> deck = new ArrayList<GameCard>();
		List<GameCard> playerChoice = new ArrayList<GameCard>();

		vBox1.setBackground(new Background(new BackgroundFill(Color.GOLD,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.setAlignment(Pos.CENTER);
		
		Button btPlayPoker = new Button("PLAY");
		buttonSetup(btPlayPoker, Color.LIMEGREEN);
		
//		btPlayPoker.setPrefSize(130, 50);
//		btPlayPoker.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 16.0));
//		btPlayPoker.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN,
//				CornerRadii.EMPTY, Insets.EMPTY)));
//		btPlayPoker.setMaxSize(130, 50);
//		btPlayPoker.setMinSize(130, 50);
		
		btPlayPoker.setOnAction(e -> {
			System.out.println("BINGO");
			playPoker();
		});
		
		Button btReset = new Button("RESET");
		btReset.setPrefSize(130, 50);
		btReset.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 16.0));
		btReset.setBackground(new Background(new BackgroundFill(Color.DARKGOLDENROD,
				CornerRadii.EMPTY, Insets.EMPTY)));
		btReset.setMaxSize(130, 50);
		btReset.setMinSize(130, 50);
		btReset.setOnAction(e -> {
			for (int i = 0; i < 52; i++) {
				deck.get(i).view.setEffect(null);
			}
			
		});
		vBox2.setPadding(new Insets(10));
		vBox2.setAlignment(Pos.CENTER);
		vBox2.getChildren().addAll(btPlayPoker, btReset);
		
		PaneOrganizer pane = new PaneOrganizer(vBox1);
		pane.getTopPane().getChildren().add(menuBarPoker);
		pane.getRightPane().getChildren().add(vBox2);
		
		pane.getTopPane().setStyle("-fx-background-color: coral");
		pane.getCenterPane().setStyle("-fx-background-color: darkolivegreen");
		pane.getRightPane().setStyle("-fx-background-color: chocolate");
		
		double vBoxW = (pane.getAnchor().getWidth() - pane.getRightPane().getPrefWidth());
		double vBoxH = (pane.getAnchor().getHeight() - pane.getTopPane().getPrefHeight());
		cardX = 1000 / 17;
		cardY = 400 / 4;
		
		for (int i = 0; i < 52; i++) {
			deck.add(new GameCard());
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				int cardNumber;
				switch (i) {
				case 0: 
					cardNumber = j - 1; 
					deck.get(cardNumber).setName("Spade" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/spade" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getRoot().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					
					});
					break;
				case 1: 
					cardNumber = j + 12;
					deck.get(cardNumber).setName("Club" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/club" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getAnchor().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getAnchor().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					
					});
					break;
				case 2: 
					cardNumber = j + 25;
					deck.get(cardNumber).setName("Heart" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/heart" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getRoot().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					
					});
					break;
				case 3: 
					cardNumber = j + 38;
					deck.get(cardNumber).setName("Diamond" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/diamond" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getRoot().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					
					});
					break;
				}
			}
		}
		Group cards = new Group();
		for (int i = 0; i < 52; i++) {
			if (i < 13) {
				deck.get(i).view.setX(20 + (i * cardX));
			}
			else if (12 < i && i < 26) {
				deck.get(i).view.setX(0 + ((i - 13) * cardX));
				deck.get(i).view.setY(cardY);
			}
			else if (25 < i && i < 39) {
				deck.get(i).view.setX(20 + ((i - 26) * cardX));
				deck.get(i).view.setY(cardY * 2);
			}
			else if (38 < i) {
				deck.get(i).view.setX(0 + ((i - 39) * cardX));
				deck.get(i).view.setY(cardY * 3);
			}
			cards.getChildren().add(deck.get(i).view);
			System.out.println(deck.get(i).getName());
		}
		vBox1.getChildren().addAll(cards);
		
		btPlayPoker.prefWidthProperty().bind(vBox2.widthProperty());
		btPlayPoker.prefHeightProperty().bind(vBox2.heightProperty());

		Scene pokerScene = new Scene(pane.getAnchor(), 1000, 500);
		Stage pokerStage = new Stage();
		pokerStage.setResizable(false);
		pokerStage.setTitle("Video Poker");
		pokerStage.setScene(pokerScene);
		pokerStage.show();

		menuItemExit1.setOnAction(e1 -> pokerStage.close());
	}
	
	private void newKeno() throws Exception {
		
		MenuBar menuBarKeno = new MenuBar();		
		Menu menuExit1 = new Menu("Exit");
		MenuItem menuItemExit1 = new MenuItem("Exit");
		menuBarKeno.getMenus().add(menuExit1);
		menuExit1.getItems().add(menuItemExit1);

		List<GameCard> deck = new ArrayList<GameCard>();
		List<GameCard> playerChoice = new ArrayList<GameCard>();
		
		VBox vBox1 = new VBox(20);
		VBox vBox2 = new VBox(10);
		vBox1.setBackground(new Background(new BackgroundFill(Color.GOLD,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.setAlignment(Pos.CENTER);
		
		Button btPlayKeno = new Button("PLAY");
		btPlayKeno.setPrefSize(130, 50);
		btPlayKeno.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 16.0));
		btPlayKeno.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		btPlayKeno.setMaxSize(130, 50);
		btPlayKeno.setMinSize(130, 50);
		btPlayKeno.setOnAction(e -> {
			System.out.println("BINGO");
			playKeno();
		});
	
		vBox2.setPadding(new Insets(10));
		vBox2.setAlignment(Pos.CENTER);
		vBox2.getChildren().add(btPlayKeno);
		
		PaneOrganizer pane = new PaneOrganizer(vBox1);
		pane.getTopPane().getChildren().add(menuBarKeno);
		pane.getRightPane().getChildren().add(vBox2);
		
		double vBoxW = (pane.getAnchor().getWidth() - pane.getRightPane().getPrefWidth());
		double vBoxH = (pane.getAnchor().getHeight() - pane.getTopPane().getPrefHeight());
		cardX = 1000 / 17;
		cardY = 400 / 4;
		
		for (int i = 0; i < 52; i++) {
			deck.add(new GameCard());
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				int cardNumber;
				switch (i) {
				case 0: 
					cardNumber = j - 1; 
					deck.get(cardNumber).setName("Spade" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/spade" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getRoot().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					
					});
					break;
				case 1: 
					cardNumber = j + 12;
					deck.get(cardNumber).setName("Club" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/club" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getAnchor().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getAnchor().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					});
					break;
				case 2: 
					cardNumber = j + 25;
					deck.get(cardNumber).setName("Heart" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/heart" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getRoot().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					
					});
					break;
				case 3: 
					cardNumber = j + 38;
					deck.get(cardNumber).setName("Diamond" + j);
					deck.get(cardNumber).setNumber(j);
					deck.get(cardNumber).setImage("Cards/diamond" + j + ".png");
					deck.get(cardNumber).view.fitWidthProperty().bind(pane.getRoot().widthProperty().
							subtract(pane.getRightPane().getWidth()).divide(20));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(6));
					
					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));
					
					});
					break;
				}
			}
		}
		Group cards = new Group();
		for (int i = 0; i < 52; i++) {
			if (i < 13) {
				deck.get(i).view.setX(20 + (i * cardX));
			}
			else if (12 < i && i < 26) {
				deck.get(i).view.setX(0 + ((i - 13) * cardX));
				deck.get(i).view.setY(cardY);
			}
			else if (25 < i && i < 39) {
				deck.get(i).view.setX(20 + ((i - 26) * cardX));
				deck.get(i).view.setY(cardY * 2);
			}
			else if (38 < i) {
				deck.get(i).view.setX(0 + ((i - 39) * cardX));
				deck.get(i).view.setY(cardY * 3);
			}
			cards.getChildren().add(deck.get(i).view);
			System.out.println(deck.get(i).getName());
		}
		vBox1.getChildren().addAll(cards);
		
		btPlayKeno.prefWidthProperty().bind(vBox2.widthProperty());
		btPlayKeno.prefHeightProperty().bind(vBox2.heightProperty());

		Scene kenoScene = new Scene(pane.getAnchor(), 1000, 500);
		Stage kenoStage = new Stage();
		kenoStage.setResizable(false);
		kenoStage.setTitle("Card Keno");
		kenoStage.setScene(kenoScene);
		kenoStage.show();

		menuItemExit1.setOnAction(e1 -> kenoStage.close());
	}
}
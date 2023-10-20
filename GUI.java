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
import javafx.geometry.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.scene.*;

public class GUI extends Application {
	static double cardX;
	static double cardY;
	static double sceneX;
	static double sceneY;

	@Override

	public void start(Stage primaryStage) throws Exception {
		VBox vBoxCards = new VBox(20);
		vBoxCards.setBackground(new Background(new BackgroundFill(Color.BLUE,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBoxCards.setAlignment(Pos.TOP_CENTER);
		
		

		VBox vBoxGame = new VBox(10);
		
		Button btPlayKeno = new Button("PLAY");
		btPlayKeno.setPrefSize(150, 50);
		
		btPlayKeno.setFont(new Font(40).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30.0));
		btPlayKeno.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		btPlayKeno.setMaxSize(225, 75);
		btPlayKeno.setMinSize(100, 33);
		btPlayKeno.setOnAction(e -> {
			System.out.println("BINGO");
			playKeno();
		});
		vBoxGame.setPadding(new Insets(25));
		vBoxGame.setAlignment(Pos.CENTER);
		vBoxGame.getChildren().add(btPlayKeno);
	//	vBoxGame.setVgrow(btPlayKeno, Priority.ALWAYS);

		HBox hBoxCards = new HBox(10);

		MenuBar menuBar = new MenuBar();		
		Menu menuGames = new Menu("Games");		
		menuBar.getMenus().add(menuGames);

		MenuItem menuItemPlayPoker = new MenuItem("Play Poker");
		MenuItem menuItemPlayKeno = new MenuItem("Play Keno");
		MenuItem menuItemExit = new MenuItem("Exit");

		menuGames.getItems().addAll(menuItemPlayPoker, menuItemPlayKeno, menuItemExit);

		menuItemPlayPoker.setOnAction(e -> newPoker());
		menuItemPlayKeno.setOnAction(e -> newKeno());
		menuItemExit.setOnAction(e -> System.exit(0));
		
		PaneOrganizer pane = new PaneOrganizer(vBoxCards);
		pane.getTopPane().getChildren().add(menuBar);
		pane.getRightPane().getChildren().add(vBoxGame);
		
		Scene menuScene = new Scene(pane.getAnchor(), 1000, 500);
		
		List<GameCard> deck = new ArrayList<GameCard>();
		List<GameCard> playerChoice = new ArrayList<GameCard>();
		
		btPlayKeno.prefWidthProperty().bind(vBoxGame.widthProperty());
		btPlayKeno.prefHeightProperty().bind(vBoxGame.heightProperty());
		
		double vBoxW = (pane.getAnchor().getWidth() - pane.getRightPane().getPrefWidth());
		double vBoxH = (pane.getAnchor().getHeight() - pane.getTopPane().getPrefHeight());
		cardX = vBoxW / 13.5;
		cardY = vBoxH / 4.5;

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
						subtract(pane.getRightPane().getHeight()).divide(4.5));
			//	deck.get(cardNumber).view.
				deck.get(cardNumber).view.setOnMousePressed(e -> {
					playerChoice.add(deck.get(cardNumber));
					System.out.println(cardNumber);
				});
				break;
				case 1: 
				cardNumber = j + 12;
				deck.get(cardNumber).setName("Club" + j);
				deck.get(cardNumber).setNumber(j);
				deck.get(cardNumber).setImage("Cards/club" + j + ".png");
				deck.get(cardNumber).view.fitWidthProperty().bind(pane.getRoot().widthProperty().
						subtract(pane.getRightPane().getWidth()).divide(20));
				deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
						subtract(pane.getRightPane().getHeight()).divide(4.5));
				deck.get(cardNumber).view.setOnMousePressed(e -> {
					playerChoice.add(deck.get(cardNumber));
					System.out.println(cardNumber);
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
						subtract(pane.getRightPane().getHeight()).divide(4.5));
				deck.get(cardNumber).view.setOnMousePressed(e -> {
					playerChoice.add(deck.get(cardNumber));
					System.out.println(cardNumber);
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
						subtract(pane.getRightPane().getHeight()).divide(4.5));
				deck.get(cardNumber).view.setOnMousePressed(e -> {
					playerChoice.add(deck.get(cardNumber));
					System.out.println(cardNumber);
				});
				break;
				}
			}
		}
		
		Group cards = new Group();
		for (int i = 0; i < 52; i++) {
			int cardIterator;
			int multiplier;
			if (i < 13) {
				cardIterator = i;
				multiplier = 0;
				cardSize(deck, cardIterator, i, multiplier, pane);
			}
			else if (12 < i && i < 26) {
				cardIterator = i - 13;
				multiplier = 1;
				cardSize(deck, cardIterator, i, multiplier, pane);
			}
			else if (25 < i && i < 39) {
				cardIterator = i - 26;
				multiplier = 2;
				cardSize(deck, cardIterator, i, multiplier, pane);
			}
			else if (38 < i) {
				cardIterator = i - 39;
				multiplier = 3;
				cardSize(deck, cardIterator, i, multiplier, pane);
			}
			cards.getChildren().add(deck.get(i).view);
			System.out.println(deck.get(i).getName());
		}
		vBoxCards.getChildren().addAll(cards);
		
		primaryStage.setTitle("Home Casino Main Menu");
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}
	private void cardSize(List<GameCard> deck, int cardIterator, int i, int multiplier, PaneOrganizer pane) {
		deck.get(cardIterator).view.xProperty().bind((pane.getRoot().widthProperty().
				subtract(pane.getRightPane().getWidth()).divide(17).multiply(cardIterator)));
		deck.get(cardIterator).view.yProperty().bind(pane.getRoot().heightProperty().
				subtract(pane.getTopPane().getHeight()).divide(5).multiply(multiplier));
		deck.get(cardIterator).view.setPreserveRatio(true);
	}
	private void playKeno() {
		
	}
	public static void main(String[] args) {
		launch (args);
	}
	private void newPoker() {
		MenuBar menuBarPoker = new MenuBar();		
		Menu menuExit1 = new Menu("Exit");
		MenuItem menuItemExit1 = new MenuItem("Exit");
		menuBarPoker.getMenus().add(menuExit1);
		menuExit1.getItems().add(menuItemExit1);

		VBox vBox1 = new VBox(10);
		vBox1.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.getChildren().addAll(menuBarPoker);

		Scene pokerScene = new Scene(vBox1, 600, 400);
		Stage pokerStage = new Stage();
		pokerStage.setTitle("Video Poker");
		pokerStage.setScene(pokerScene);
		pokerStage.show();

		menuItemExit1.setOnAction(e1 -> pokerStage.close());
	}
	private void newKeno() {
		MenuBar menuBarKeno = new MenuBar();		
		Menu menuExit1 = new Menu("Exit");
		MenuItem menuItemExit1 = new MenuItem("Exit");
		menuBarKeno.getMenus().add(menuExit1);
		menuExit1.getItems().add(menuItemExit1);

		VBox vBox1 = new VBox(10);
		vBox1.setBackground(new Background(new BackgroundFill(Color.GOLD,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.getChildren().addAll(menuBarKeno);

		Scene kenoScene = new Scene(vBox1, 600, 400);
		Stage kenoStage = new Stage();
		kenoStage.setTitle("Card Keno");
		kenoStage.setScene(kenoScene);
		kenoStage.show();

		menuItemExit1.setOnAction(e1 -> kenoStage.close());
	}
}
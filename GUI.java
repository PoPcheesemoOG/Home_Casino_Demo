/* Name: Paul Helske
 * Date: 10/17/2023
 */

import java.util.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.geometry.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.*;

public class GUI extends Application {
	static double cardX;
	static double cardY;

	@Override

	public void start(Stage primaryStage) throws Exception {
		VBox vBoxCards = new VBox(20);
		vBoxCards.setBackground(new Background(new BackgroundFill(Color.CRIMSON,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBoxCards.setAlignment(Pos.TOP_CENTER);
		Scene menuScene = new Scene(vBoxCards, 1200, 600);
	//	double sceneW = menuScene.getWidth();
		double vBoxW = vBoxCards.getWidth();
		double vBoxH = vBoxCards.getHeight();
		cardX = vBoxW / 13.5;
		cardY = vBoxH / 4.5;

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

		List<GameCard> deck = new ArrayList<GameCard>();

		for (int i = 0; i < 52; i++) {
			deck.add(new GameCard());
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 14; j++) {
				switch (i) {
				case 0: deck.get(j - 1).setName("Spade" + j);
				deck.get(j - 1).setNumber(j);
				deck.get(j - 1).setImage("Cards/spade" + j + ".png");
				break;
				case 1: deck.get(j + 12).setName("Club" + j);
				deck.get(j + 12).setNumber(j);
				deck.get(j + 12).setImage("Cards/club" + j + ".png");
				break;
				case 2: deck.get(j + 25).setName("Heart" + j);
				deck.get(j + 25).setNumber(j);
				deck.get(j + 25).setImage("Cards/heart" + j + ".png");
				break;
				case 3: deck.get(j + 38).setName("Diamond" + j);
				deck.get(j + 38).setNumber(j);
				deck.get(j + 38).setImage("Cards/diamond" + j + ".png");
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
		vBoxCards.getChildren().addAll(menuBar, cards);
		primaryStage.setTitle("Home Casino Main Menu");
		primaryStage.setScene(menuScene);
		primaryStage.show();
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

	public double getCardX() {
		System.out.println(cardX);
		return cardX;
	}
	public double getCardY() {
		System.out.println(cardY);
		return cardY;
	}
}
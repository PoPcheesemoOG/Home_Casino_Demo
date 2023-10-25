/* Name: Paul Helske
 * Date: 10/17/2023
 */

import java.text.*;
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
	static double bank;
	static DecimalFormat df = new DecimalFormat("0.00");
	double betSize;
	Stage primaryStage;

	@Override

	public void start(Stage primaryStage) throws Exception {
		VBox vBox1 = new VBox(30);
		vBox1.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.setAlignment(Pos.TOP_CENTER);

		Text bankTf;
		Text betAmount;

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

		bank = 100;
		betAmount = new Text("Bet Amount: " + df.format(betSize));
		bankTf = new Text("Money: " + df.format(bank));
		bankTf.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30.0));
		betAmount.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30.0));

		Button btPlayPoker = new Button("PLAY POKER");
		Button btPlayKeno = new Button("PLAY KENO");
		btPlayPoker.setLayoutX(600);
		btPlayKeno.setAlignment(Pos.CENTER);
		buttonSetup(btPlayPoker, Color.MEDIUMTURQUOISE);
		buttonSetup(btPlayKeno, Color.PALEVIOLETRED);
		btPlayPoker.setOnAction(e -> {
			try {
				newPoker();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		btPlayKeno.setOnAction(e -> {
			try {
				newKeno();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		Pane pane1 = new StackPane();
		pane1.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.getChildren().addAll(menuBar, btPlayPoker, btPlayKeno);
		pane1.getChildren().addAll(vBox1);

		Scene menuScene = new Scene(pane1, 600, 300);

		primaryStage.setResizable(false);
		primaryStage.setTitle("Home Casino Main Menu");
		primaryStage.setScene(menuScene);
		primaryStage.show();
	}

	private void playKeno(List<GameCard> playerChoice, List<GameCard> deck, double betSize, Text bankTf, Text display) {
		ColorAdjust colorAdjust = new ColorAdjust();
		colorAdjust.setContrast(0.0);
		colorAdjust.setHue(-0.2);
		colorAdjust.setBrightness(0.5);
		colorAdjust.setSaturation(0.8);
		int random;
		int correct = 0;
		for (int i = 0; i < 10; i++) {
			random = (int) (Math.random() * 520) / 10;
			deck.get(random).view.setEffect(colorAdjust);
			if (playerChoice.contains(deck.get(random)) == true) {
				correct++;
			}

			System.out.println(random);
		}
		System.out.println("Correct: " + correct);
		switch (correct) {
		case 0: bank += betSize * 0.0; display.setText("You win: " + df.format(betSize * 0.5) + "\nPlease press reset to play again"); break;
		case 1: bank += betSize * 0.5; display.setText("You win: " + df.format(betSize * 0.5) + "\nPlease press reset to play again"); break;
		case 2: bank += betSize * 1; display.setText("You win: " + df.format(betSize * 1) + "\nPlease press reset to play again"); break;
		case 3: bank += betSize * 2; display.setText("You win: " + df.format(betSize * 2) + "\nPlease press reset to play again"); break;
		case 4: bank += betSize * 3; display.setText("You win: " + df.format(betSize * 3) + "\nPlease press reset to play again"); break;
		case 5: bank += betSize * 5; display.setText("You win: " + df.format(betSize * 5) + "\nPlease press reset to play again"); break;
		case 6: bank += betSize * 8; display.setText("You win: " + df.format(betSize * 8) + "\nPlease press reset to play again"); break;
		case 7: bank += betSize * 12; display.setText("You win: " + df.format(betSize * 12) + "\nPlease press reset to play again"); break;
		case 8: bank += betSize * 20; display.setText("You win: " + df.format(betSize * 20) + "\nPlease press reset to play again"); break;
		case 9: bank += betSize * 50; display.setText("You win: " + df.format(betSize * 50) + "\nPlease press reset to play again"); break;
		}
		correct = 0;
		updateBank(bankTf, bank);
	}
	private void playPoker(List<GameCard> playerChoice, List<GameCard> deck, double betSize, Text bankTf, Text display) {
		
	}

	private void buttonSetup(Button bt, Color color) {
		bt.setPrefSize(130, 50);
		bt.setFont(new Font(14).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 14.0));
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

		Text bankTf;
		Text betAmount;
		betAmount = new Text("Bet Amount: " + df.format(betSize));
		bankTf = new Text("Money: " + df.format(bank));
		bankTf.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30.0));
		betAmount.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30.0));
		
		Text display;
		display = new Text("Choose 10 cards, a bet and press play");
		display.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0));

		VBox vBox1 = new VBox(20);
		VBox vBox2 = new VBox(10);
		HBox hBox1 = new HBox(50);
		hBox1.setPadding(new Insets(35));

		vBox1.setBackground(new Background(new BackgroundFill(Color.DARKOLIVEGREEN,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.getChildren().addAll(menuBarPoker);

		List<GameCard> deck = new ArrayList<GameCard>();
		List<GameCard> playerChoice = new ArrayList<GameCard>();

		vBox1.setBackground(new Background(new BackgroundFill(Color.GOLD,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.setAlignment(Pos.CENTER);
		
		Group cards = new Group();
		List randoms = new ArrayList();

		Button btPlayPoker = new Button("PLAY");
		buttonSetup(btPlayPoker, Color.LIMEGREEN);
		btPlayPoker.setOnAction(e -> {
			System.out.println("BINGO");
			playPoker(playerChoice, deck, betSize, bankTf, display);
		});

		Button btReset = new Button("RESET");
		buttonSetup(btReset, Color.DARKGOLDENROD);
		btReset.setOnAction(e -> {
			for (int i = 0; i < 52; i++) {
				deck.get(i).view.setEffect(null);
			}
			for (int i = 0; i < 5; i++) {
				int random = (int) ((Math.random() * 480) / 10);
				
				while (randoms.contains(random) == true) {
					random = (int) ((Math.random() * 480) / 10);
				}
				randoms.add(random);
				System.out.println(random);
				deck.get(random).view.setX(150 * i);
				deck.get(random).view.setY(30);
				cards.getChildren().add(deck.get(random).view);
			}

		});

		Button btBetUp = new Button("BET UP");
		buttonSetup(btBetUp, Color.LAWNGREEN);
		btBetUp.setOnAction(e -> {
			if (betSize < bank) {
				betSize += 10;
			}
			else return;
			System.out.println(betSize);
			updateBet(betAmount, betSize);
		});
		Button btBetDown = new Button("BET DOWN");
		buttonSetup(btBetDown, Color.PINK);
		btBetDown.setOnAction(e -> {
			if (betSize > 0) {
				betSize -= 10;
			}
			else return;
			System.out.println(betSize);
			updateBet(betAmount, betSize);
		});


		vBox2.setPadding(new Insets(10));
		vBox2.setAlignment(Pos.CENTER);
		vBox2.getChildren().addAll(btPlayPoker, btReset, btBetUp, btBetDown);
		hBox1.getChildren().addAll(bankTf, betAmount);

		PaneOrganizer pane = new PaneOrganizer(vBox1);
		pane.getTopPane().getChildren().addAll(hBox1, menuBarPoker);
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
							subtract(pane.getRightPane().getWidth()).divide(8));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(2.5));

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
							subtract(pane.getRightPane().getWidth()).divide(8));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getAnchor().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(2.5));

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
							subtract(pane.getRightPane().getWidth()).divide(8));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(2.5));

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
							subtract(pane.getRightPane().getWidth()).divide(8));
					deck.get(cardNumber).view.fitHeightProperty().bind(pane.getRoot().heightProperty().
							subtract(pane.getRightPane().getHeight()).divide(2.5));

					deck.get(cardNumber).view.setOnMousePressed(e -> {
						playerChoice.add(deck.get(cardNumber));
						System.out.println(cardNumber);
						deck.get(cardNumber).view.setEffect(new Glow(0.8));

					});
					break;
				}
			}
		}
		
		for (int i = 0; i < 5; i++) {
			int random = (int) ((Math.random() * 480) / 10);
			
			while (randoms.contains(random) == true) {
				random = (int) ((Math.random() * 480) / 10);
			}
			randoms.add(random);
			System.out.println(random);
			deck.get(random).view.setX(150 * i);
			deck.get(random).view.setY(30);
			cards.getChildren().add(deck.get(random).view);
		}
		
//		for (int i = 0; i < 52; i++) {
//			if (i < 13) {
//				deck.get(i).view.setX(20 + (i * cardX));
//			}
//			else if (12 < i && i < 26) {
//				deck.get(i).view.setX(0 + ((i - 13) * cardX));
//				deck.get(i).view.setY(cardY);
//			}
//			else if (25 < i && i < 39) {
//				deck.get(i).view.setX(20 + ((i - 26) * cardX));
//				deck.get(i).view.setY(cardY * 2);
//			}
//			else if (38 < i) {
//				deck.get(i).view.setX(0 + ((i - 39) * cardX));
//				deck.get(i).view.setY(cardY * 3);
//			}
//			cards.getChildren().add(deck.get(i).view);
//			System.out.println(deck.get(i).getName());
		
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

		Text bankTf;
		Text betAmount;
		
		betAmount = new Text("Bet Amount: " + df.format(betSize));
		bankTf = new Text("Money: " + df.format(bank));
		
		bankTf.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 26.0));
		betAmount.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 26.0));
		
		Text display;
		display = new Text("Choose 10 cards, a bet and press play");
		display.setFont(new Font(16).font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20.0));

		List<GameCard> deck = new ArrayList<GameCard>();
		List<GameCard> playerChoice = new ArrayList<GameCard>();

		VBox vBox1 = new VBox(20);
		VBox vBox2 = new VBox(10);
		HBox hBox1 = new HBox(30);
		hBox1.setPadding(new Insets(35));

		vBox1.setBackground(new Background(new BackgroundFill(Color.GOLD,
				CornerRadii.EMPTY, Insets.EMPTY)));
		vBox1.setAlignment(Pos.CENTER);

		Button btPlayKeno = new Button("PLAY");
		buttonSetup(btPlayKeno, Color.LIMEGREEN);
		btPlayKeno.setOnAction(e -> {
			System.out.println("BINGO");
			if (playerChoice.size() < 10) {
				display.setText("Please select 10 cards to play");
				return;
			}
			if (betSize == 0) {
				display.setText("Please choose a bet amount");
				return;
			}
			bank -= betSize;
			updateBank(bankTf, bank);
			playKeno(playerChoice, deck, betSize, bankTf, display);
			betSize = 0;
			updateBet(betAmount, betSize);
		});
		Button btReset = new Button("RESET");
		buttonSetup(btReset, Color.DARKGOLDENROD);
		btReset.setOnAction(e -> {
			for (int i = 0; i < 52; i++) {
				deck.get(i).view.setEffect(null);
			}
			playerChoice.clear();
			display.setText("Choose 10 cards, a bet and press play");

		});

		Button btBetUp = new Button("BET UP 10");
		buttonSetup(btBetUp, Color.LAWNGREEN);
		btBetUp.setOnAction(e -> {
			if (betSize < bank) {
				betSize += 10;
			}
			else return;
			System.out.println(betSize);
			updateBet(betAmount, betSize);
		});
		Button btBetDown = new Button("BET DOWN 10");
		buttonSetup(btBetDown, Color.PINK);
		btBetDown.setOnAction(e -> {
			if (betSize > 0) {
				betSize -= 10;
			}
			else return;
			System.out.println(betSize);
			updateBet(betAmount, betSize);
		});

		vBox2.setPadding(new Insets(10));
		vBox2.setAlignment(Pos.CENTER);
		vBox2.getChildren().addAll(btPlayKeno, btReset, btBetUp, btBetDown);
		hBox1.getChildren().addAll(bankTf, betAmount, display);

		PaneOrganizer pane = new PaneOrganizer(vBox1);
		pane.getTopPane().getChildren().addAll(hBox1, menuBarKeno);
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
						if (playerChoice.size() < 10) {
							playerChoice.add(deck.get(cardNumber));
							deck.get(cardNumber).view.setEffect(new Glow(0.8));
						} else return;
						
						System.out.println(cardNumber);
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
						if (playerChoice.size() < 10) {
							playerChoice.add(deck.get(cardNumber));
							deck.get(cardNumber).view.setEffect(new Glow(0.8));
						} else return;
						
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
							subtract(pane.getRightPane().getHeight()).divide(6));

					deck.get(cardNumber).view.setOnMousePressed(e -> {
						if (playerChoice.size() < 10) {
							playerChoice.add(deck.get(cardNumber));
							deck.get(cardNumber).view.setEffect(new Glow(0.8));
						} else return;
						
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
							subtract(pane.getRightPane().getHeight()).divide(6));

					deck.get(cardNumber).view.setOnMousePressed(e -> {
						if (playerChoice.size() < 10) {
							playerChoice.add(deck.get(cardNumber));
							deck.get(cardNumber).view.setEffect(new Glow(0.8));
						} else return;
						
						System.out.println(cardNumber);
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

		menuItemExit1.setOnAction(e1 -> {
			kenoStage.close();
		});
	}
	private void updateBet(Text betAmount, double bet) {
		betAmount.setText("Bet Amount: " + df.format(betSize));
	}
	private void updateBank(Text bankTf, double bank) {
		bankTf.setText("Money: " + df.format(bank));
	}
}
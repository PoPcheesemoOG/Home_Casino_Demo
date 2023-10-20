/* Name: Paul Helske
 * Date: 10/17/2023
 */

import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import java.io.*;

public class GameCard {
	public Image img;
	public ImageView view = new ImageView(img);
	public enum Suit {Spade, Clubs, Heart, Diamond};
	public int Number;
	public String name;
	DoubleProperty cardW = new SimpleDoubleProperty(GUI.cardX);
	DoubleProperty cardH = new SimpleDoubleProperty(GUI.cardY);
	
//	DoubleBinding cardWbind = new DoubleBinding() {
//		{ super.bind(cardW, GUI.cardX);
//		}
//
//		@Override
//		protected double computeValue() {
//			// TODO Auto-generated method stub
//			return 0;
//		}};

	public GameCard() {

	}
	public GameCard(String name1) {
		name = name1;
	}

	public void setName(String name1) {
		name = name1;
	}
	public String getName() {
		return name;
	}
	public void setNumber(int Number1) {
		Number = Number1;
	}
	public int getNumber() {
		return Number;
	}
	public void setImage(String location) throws FileNotFoundException {
		
		img = new Image(new FileInputStream(location));
		view = new ImageView(img);
		view.setFitHeight(cardH.get() - 10);
		view.setFitWidth(cardW.get() - 10);
	}
}
/* Name: Paul Helske
 * Date: 10/17/2023
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameCard {
//	public Image img;
	public String img;
//	public ImageView view = new ImageView(img);
	public String view = new String();
	public enum Suit {Spade, Clubs, Heart, Diamond};
	public int Number;
	public String name;

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
	public void setImage(String location) {
		img = location;
	}
}
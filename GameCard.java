/* Name: Paul Helske
 * Date: 10/17/2023
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameCard {
	public Image img;
	public ImageView view = new ImageView(img);
	public enum Suit {Spade, Clubs, Heart, Diamond};
	public int Number;
}

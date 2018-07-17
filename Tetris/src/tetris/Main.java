package tetris;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		KeysControl kc = new KeysControl();
		Thread t= new Thread(kc);
        t.start ();

		JFrame frame = new JFrame("Tetris");
		frame.setSize(500, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		DrawSquare drawSquare1 = new DrawSquare();
		frame.add(drawSquare1);
	    
		drawSquare1.setInitialPositionAndTimer();

		frame.setVisible(true);
	}
}

package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawSquare extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	static int timerStep = 200;

	int distanceChangeY = 20;
	int score = 0;
	static int positionX, positionY;
	static int positionXLeft;
	static int positionXRight;
	int colorNumber;
	int shapeNumber;
	static Element elementType;
	static Timer timer;
	int timerTime;
	int randomNumber;
	int positionYcount;
	int randomX;
	
	private static List<Color> color = new ArrayList<Color>();

	@SuppressWarnings("rawtypes")
	static List<ArrayList> elements = new ArrayList<ArrayList>();
	private static List<Element> shapes = new ArrayList<Element>();
	private static List<Object> elementsXYData = new ArrayList<Object>();
	private static List<Integer> squareXYData = new ArrayList<Integer>();
	private static List<Integer> bigSquareXYData = new ArrayList<Integer>();
	private static List<Integer> stickXYData = new ArrayList<Integer>();
	private static List<Integer> stickVerticalXYData = new ArrayList<Integer>();
	private static List<Integer> tletterXYData = new ArrayList<Integer>();
	private static List<Integer> tletterSecondXYData = new ArrayList<Integer>();
	private static List<Integer> tletterThirdXYData = new ArrayList<Integer>();
	private static List<Integer> tletterFourthXYData = new ArrayList<Integer>();
	private static List<Integer> sletterXYData = new ArrayList<Integer>();
	private static List<Integer> sletterSecondXYData = new ArrayList<Integer>();
	private static List<Integer> lletterXYData = new ArrayList<Integer>();
	private static List<Integer> lletterSecondXYData = new ArrayList<Integer>();
	private static List<Integer> lletterThirdXYData = new ArrayList<Integer>();
	private static List<Integer> lletterFourthXYData = new ArrayList<Integer>();
	private static List<Integer> lletterReverseXYData = new ArrayList<Integer>();
	private static List<Integer> lletterReverseSecondXYData = new ArrayList<Integer>();
	private static List<Integer> lletterReverseThirdXYData = new ArrayList<Integer>();
	private static List<Integer> lletterReverseFourthXYData = new ArrayList<Integer>();
	
	private static Map<String, Integer> rowYCoordCountMap = new LinkedHashMap<>();
	
	static {
		
		shapes.add(Element.SQUARE);
		shapes.add(Element.BIGSQUARE);
		shapes.add(Element.STICK);
		shapes.add(Element.LLETTER);
		shapes.add(Element.TLETTER);
		shapes.add(Element.SLETTER);
		shapes.add(Element.LLETTER_REVERSE);
		
		color.add(Color.WHITE);
		color.add(Color.BLUE);
		color.add(Color.GREEN);
		color.add(Color.YELLOW);
		color.add(Color.ORANGE);
		color.add(Color.GRAY);
		color.add(Color.RED);
		
		List<Integer> rectangle1 = new ArrayList<Integer>();
		rectangle1.addAll(Arrays.asList(100, 500, 0));
		elements.add((ArrayList<Integer>) rectangle1);
		List<Integer> rectangle2 = new ArrayList<Integer>();
		rectangle2.addAll(Arrays.asList(120, 500, 0));
		elements.add((ArrayList<Integer>) rectangle2);
		List<Integer> rectangle3 = new ArrayList<Integer>();
		rectangle3.addAll(Arrays.asList(140, 500, 0));
		elements.add((ArrayList<Integer>) rectangle3);
		List<Integer> rectangle4 = new ArrayList<Integer>();
		rectangle4.addAll(Arrays.asList(160, 500, 0));
		elements.add((ArrayList<Integer>) rectangle4);
		List<Integer> rectangle5 = new ArrayList<Integer>();
		rectangle5.addAll(Arrays.asList(180, 500, 0));
		elements.add((ArrayList<Integer>) rectangle5);
		List<Integer> rectangle6 = new ArrayList<Integer>();
		rectangle6.addAll(Arrays.asList(200, 500, 0));
		elements.add((ArrayList<Integer>) rectangle6);
		List<Integer> rectangle7 = new ArrayList<Integer>();
		rectangle7.addAll(Arrays.asList(220, 500, 0));
		elements.add((ArrayList<Integer>) rectangle7);
		List<Integer> rectangle8 = new ArrayList<Integer>();
		rectangle8.addAll(Arrays.asList(240, 500, 0));
		elements.add((ArrayList<Integer>) rectangle8);
		List<Integer> rectangle9 = new ArrayList<Integer>();
		rectangle9.addAll(Arrays.asList(260, 500, 0));
		elements.add((ArrayList<Integer>) rectangle9);
		List<Integer> rectangle10 = new ArrayList<Integer>();
		rectangle10.addAll(Arrays.asList(280, 500, 0));
		elements.add((ArrayList<Integer>) rectangle10);
		
		squareXYData.addAll(Arrays.asList(10, 10, 0, 0));
		bigSquareXYData.addAll(Arrays.asList(10, 10,   0, 0,     0, -20,     20, -20,    20, 0));
		
		stickXYData.addAll(Arrays.asList(10, 10,       0, 0,          20, 0,         40, 0,        60, 0));
		stickVerticalXYData.addAll(Arrays.asList(10, 10,      20, 0,      20, 40,    20, 20,      20, -20));
		
		sletterXYData.addAll(Arrays.asList(10, 10,   0, 0,   20, 0,      20, -20,      40, -20));
		sletterSecondXYData.addAll(Arrays.asList(10, 10,     0, 0,    0, -20,    20, 20,   20, 0));
		
		lletterXYData.addAll(Arrays.asList(10, 10,    0, 0,    0, -20,    0, -40,   20, 0));
		lletterSecondXYData.addAll(Arrays.asList(10, 10,     0, 0,      -20, 20,      -20, 0,      20, 0));
		lletterThirdXYData.addAll(Arrays.asList(10, 10,     0, 0,         -20, -20,     0, 20,     0, -20));
		lletterFourthXYData.addAll(Arrays.asList(10, 10,    0, 0,         -20, 0,      20, 0,     20, -20));
		
		tletterXYData.addAll(Arrays.asList(10, 10,    0, 0,    20, 0,     20, -20,   40, 0));
		tletterSecondXYData.addAll(Arrays.asList(10, 10,     0, 0,      0, 20,      0, -20,      20, 0));
		tletterThirdXYData.addAll(Arrays.asList(10, 10,     0, 0,         -20, 0,     0, 20,     20, 0));
		tletterFourthXYData.addAll(Arrays.asList(10, 10,    0, 0,         20, 20,      20, 0,     20, -20));
		
		lletterReverseXYData.addAll(Arrays.asList(10, 10,     0, 0,      20, 0,      20, -20,      20, -40));
		lletterReverseSecondXYData.addAll(Arrays.asList(10, 10,     0, 0,      0, -20,      20, 0,      40, 0));
		lletterReverseThirdXYData.addAll(Arrays.asList(10, 10,     0, 0,         0, 20,     0, -20,     20, -20));
		lletterReverseFourthXYData.addAll(Arrays.asList(10, 10,    0, 0,         20, 0,      40, 20,     40, 0));
		
		elementsXYData.add(squareXYData);
		elementsXYData.add(bigSquareXYData);
		elementsXYData.add(stickXYData);
		elementsXYData.add(lletterXYData);
		elementsXYData.add(tletterXYData);
		elementsXYData.add(sletterXYData);
		
		elementsXYData.add(stickVerticalXYData);
		elementsXYData.add(lletterSecondXYData);
		elementsXYData.add(lletterThirdXYData);
		elementsXYData.add(lletterFourthXYData);
		elementsXYData.add(tletterSecondXYData);
		elementsXYData.add(tletterThirdXYData);
		elementsXYData.add(tletterFourthXYData);
		elementsXYData.add(sletterSecondXYData);
		elementsXYData.add(lletterReverseXYData);
		elementsXYData.add(lletterReverseSecondXYData);
		elementsXYData.add(lletterReverseThirdXYData);
		elementsXYData.add(lletterReverseFourthXYData);
		elementsXYData.add(stickVerticalXYData);
		
		rowYCoordCountMap.put("row480", 0);
		rowYCoordCountMap.put("row460", 0);
		rowYCoordCountMap.put("row440", 0);
		rowYCoordCountMap.put("row420", 0);
		rowYCoordCountMap.put("row400", 0);
		rowYCoordCountMap.put("row380", 0);
		rowYCoordCountMap.put("row360", 0);
		rowYCoordCountMap.put("row340", 0);
		rowYCoordCountMap.put("row320", 0);
		rowYCoordCountMap.put("row300", 0);
		rowYCoordCountMap.put("row280", 0);
		rowYCoordCountMap.put("row260", 0);
		rowYCoordCountMap.put("row240", 0);
		rowYCoordCountMap.put("row220", 0);
		rowYCoordCountMap.put("row200", 0);
		rowYCoordCountMap.put("row180", 0);
		rowYCoordCountMap.put("row160", 0);
	}

	void setInitialPositionAndTimer() {

		randomNumber = new Random().nextInt(1);

		shapeNumber = new Random().nextInt(shapes.size());
		elementType = shapes.get(shapeNumber);

		if (elementType == Element.SQUARE)
			randomX = 10;
		if (elementType == Element.BIGSQUARE)
			randomX = 9;
		if (elementType == Element.STICK)
			randomX = 7;
		if (elementType == Element.LLETTER)
			randomX = 9;
		if (elementType == Element.TLETTER)
			randomX = 8;
		if (elementType == Element.SLETTER)
			randomX = 8;
		if (elementType == Element.LLETTER_REVERSE)
			randomX = 9;

		positionX = 100 + new Random().nextInt(randomX) * 20;
		positionY = 100;
		colorNumber = new Random().nextInt(color.size());

		timer = new Timer(timerStep, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		
//		if (timerStep == 1) {
//			timer.stop();
//			timer = new Timer(timerStep, this);
//			timer.setRepeats(false);
//			timer.start();
//		}

		positionY += distanceChangeY;
		
		if (elementType == Element.SQUARE) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX) {
						if ((int) elements.get(i).get(1) - 20 == positionY) writeRectangle(); 
					}
				}
		}
		
		if (elementType == Element.BIGSQUARE) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.STICK) {
			int numberOfSquares = 4;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			boolean fourthXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 2) thirdXCondition = true;
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 3) fourthXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true || fourthXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.STICK_VERTICAL) {
			for (int i = 0; i < elements.size(); i++) {
				if ((int) elements.get(i).get(0) == positionX + 20) {
					if ((int) elements.get(i).get(1) == positionY + 60) writeRectangle(); 
				}
			}
		}
		
		if (elementType == Element.SLETTER) {
			int numberOfSquares = 3;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) - 20 == positionY && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) == positionY && j == 2) thirdXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true) {
				writeRectangle();
			}
				
		}
		if (elementType == Element.SLETTER_SECOND) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) writeRectangle();
		}
		
		if (elementType == Element.LLETTER) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.LLETTER_SECOND) {
			int numberOfSquares = 3;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			
			int k = -20;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 2) thirdXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.LLETTER_THIRD) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = -20;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.LLETTER_FOURTH) {
			int numberOfSquares = 3;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			
			int k = -20;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 2) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true) {
				writeRectangle();
			}
		}

		
		if (elementType == Element.TLETTER) {
			int numberOfSquares = 3;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 2) thirdXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.TLETTER_SECOND) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.TLETTER_THIRD) {
			int numberOfSquares = 3;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			
			int k = -20;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 2) thirdXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.TLETTER_FOURTH) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) {
				writeRectangle();
			}
		}
		
		
		if (elementType == Element.LLETTER_REVERSE) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) {
				writeRectangle();
			}
		}
				
		if (elementType == Element.LLETTER_REVERSE_SECOND) {
			int numberOfSquares = 3;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 2) thirdXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.LLETTER_REVERSE_THIRD) {
			int numberOfSquares = 2;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY && j == 1) secondXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true) {
				writeRectangle();
			}
		}
		
		if (elementType == Element.LLETTER_REVERSE_FOURTH) {
			int numberOfSquares = 3;
			boolean firstXCondition = false;
			boolean secondXCondition = false;
			boolean thirdXCondition = false;
			
			int k = 0;
			for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
				for (int i = 0; i < elements.size(); i++) {
					if ((int) elements.get(i).get(0) == positionX + k) {
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 0) firstXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 20 && j == 1) secondXCondition = true;
						if ((int) elements.get(i).get(1) == positionY + 40 && j == 2) thirdXCondition = true;
					}
				}
			}
			if (firstXCondition == true || secondXCondition == true || thirdXCondition == true) {
				writeRectangle();
			}
		}
		repaint();
	}

	
	@SuppressWarnings("unchecked")
	void writeRectangle() {
		
		timer.stop();
		
		int i;
		int j = 0;
		List<Integer> elementXYData = null;
		if (elementType == Element.SQUARE) {
			j = 1;
			elementXYData = (List<Integer>) elementsXYData.get(0);
		}
		if (elementType == Element.BIGSQUARE) {
			elementXYData = (List<Integer>) elementsXYData.get(1);
			j = 4;
		}
		if (elementType == Element.STICK) {
			elementXYData = (List<Integer>) elementsXYData.get(2);
			j = 4;
		}
		if (elementType == Element.LLETTER) {
			elementXYData = (List<Integer>) elementsXYData.get(3);
			j = 4;
		}
		if (elementType == Element.TLETTER) {
			elementXYData = (List<Integer>) elementsXYData.get(4);
			j = 4;
		}
		if (elementType == Element.SLETTER) {
			elementXYData = (List<Integer>) elementsXYData.get(5);
			j = 4;
		}
		if (elementType == Element.LLETTER_REVERSE) {
			elementXYData = (List<Integer>) elementsXYData.get(14);
			j = 4;
		}
		
		if (elementType == Element.SLETTER_SECOND) {
			elementXYData = (List<Integer>) elementsXYData.get(13);
			j = 4;
		}
		if (elementType == Element.TLETTER_SECOND) {
			elementXYData = (List<Integer>) elementsXYData.get(10);
			j = 4;
		}
		if (elementType == Element.TLETTER_THIRD) {
			elementXYData = (List<Integer>) elementsXYData.get(11);
			j = 4;
		}
		if (elementType == Element.TLETTER_FOURTH) {
			elementXYData = (List<Integer>) elementsXYData.get(12);
			j = 4;
		}
		if (elementType == Element.LLETTER_SECOND) {
			elementXYData = (List<Integer>) elementsXYData.get(7);
			j = 4;
		}
		if (elementType == Element.LLETTER_THIRD) {
			elementXYData = (List<Integer>) elementsXYData.get(8);
			j = 4;
		}
		if (elementType == Element.LLETTER_FOURTH) {
			elementXYData = (List<Integer>) elementsXYData.get(9);
			j = 4;
		}
		if (elementType == Element.LLETTER_REVERSE_SECOND) {
			elementXYData = (List<Integer>) elementsXYData.get(15);
			j = 4;
		}
		if (elementType == Element.LLETTER_REVERSE_THIRD) {
			elementXYData = (List<Integer>) elementsXYData.get(16);
			j = 4;
		}
		if (elementType == Element.LLETTER_REVERSE_FOURTH) {
			elementXYData = (List<Integer>) elementsXYData.get(17);
			j = 4;
		}
		if (elementType == Element.STICK_VERTICAL) {
			elementXYData = (List<Integer>) elementsXYData.get(18);
			j = 4;
		}
		
		for (i = 1; i < j + 1; i++) {
			System.out.println();
			List<Integer> rectangle1 = new ArrayList<Integer>();
			rectangle1.addAll(Arrays.asList(positionX + elementXYData.get(2 * i),
					positionY + elementXYData.get((2 * i) + 1), colorNumber));
			elements.add((ArrayList<Integer>) rectangle1);
			if (rectangle1.get(1) == 480) {
				rowYCoordCountMap.put("row480", rowYCoordCountMap.get("row480") + 1);
			}
			if (rectangle1.get(1) == 460) {
				rowYCoordCountMap.put("row460", rowYCoordCountMap.get("row460") + 1);
			}
			if (rectangle1.get(1) == 440) {
				rowYCoordCountMap.put("row440", rowYCoordCountMap.get("row440") + 1);
			}
			if (rectangle1.get(1) == 420) {
				rowYCoordCountMap.put("row420", rowYCoordCountMap.get("row420") + 1);
			}
			if (rectangle1.get(1) == 400) {
				rowYCoordCountMap.put("row400", rowYCoordCountMap.get("row400") + 1);
			}
			if (rectangle1.get(1) == 380) {
				rowYCoordCountMap.put("row380", rowYCoordCountMap.get("row380") + 1);
			}
			if (rectangle1.get(1) == 360) {
				rowYCoordCountMap.put("row360", rowYCoordCountMap.get("row360") + 1);
			}
			if (rectangle1.get(1) == 340) {
				rowYCoordCountMap.put("row340", rowYCoordCountMap.get("row340") + 1);
			}
			if (rectangle1.get(1) == 320) {
				rowYCoordCountMap.put("row320", rowYCoordCountMap.get("row320") + 1);
			}
			if (rectangle1.get(1) == 300) {
				rowYCoordCountMap.put("row300", rowYCoordCountMap.get("row300") + 1);
			}
			if (rectangle1.get(1) == 280) {
				rowYCoordCountMap.put("row280", rowYCoordCountMap.get("row280") + 1);
			}
			if (rectangle1.get(1) == 260) {
				rowYCoordCountMap.put("row260", rowYCoordCountMap.get("row260") + 1);
			}
			if (rectangle1.get(1) == 240) {
				rowYCoordCountMap.put("row240", rowYCoordCountMap.get("row240") + 1);
			}
			if (rectangle1.get(1) == 220) {
				rowYCoordCountMap.put("row220", rowYCoordCountMap.get("row220") + 1);
			}
			if (rectangle1.get(1) == 200) {
				rowYCoordCountMap.put("row200", rowYCoordCountMap.get("row200") + 1);
			}
			if (rectangle1.get(1) == 180) {
				rowYCoordCountMap.put("row180", rowYCoordCountMap.get("row180") + 1);
			}
			if (rectangle1.get(1) == 160) {
				rowYCoordCountMap.put("row160", rowYCoordCountMap.get("row160") + 1);
			}
		}
		repaint();
		setInitialPositionAndTimer();
	}
	
	@SuppressWarnings("unchecked")
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.drawLine(100, 100, 100, 500);
		g.drawLine(100, 500, 300, 500);
		g.drawLine(300, 100, 300, 500);

		Graphics2D g2 = (Graphics2D) g;
		
		int fontSize = 20;
	    Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
	    g2.setFont(f);
	    g2.drawString("Score:", 350, 300);
	    g2.drawString(Integer.toString(score), 420, 300);

		int numberOfSquares = 0;
		
		int k = 0;
		List<Integer> elementXYData = null;
		if (elementType == Element.SQUARE) {
			numberOfSquares = 1;
			elementXYData = (List<Integer>) elementsXYData.get(0);
		}
		if (elementType == Element.BIGSQUARE) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(1);
		}
		if (elementType == Element.STICK) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(2);
		}
		if (elementType == Element.LLETTER) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(3);
		}
		if (elementType == Element.TLETTER) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(4);
		}
		if (elementType == Element.SLETTER) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(5);
		}
		if (elementType == Element.LLETTER_SECOND) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(7);
		}
		if (elementType == Element.LLETTER_THIRD) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(8);
		}
		if (elementType == Element.LLETTER_FOURTH) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(9);
		}
		if (elementType == Element.TLETTER_SECOND) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(10);
		}
		if (elementType == Element.TLETTER_THIRD) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(11);
		}
		if (elementType == Element.TLETTER_FOURTH) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(12);
		}
		if (elementType == Element.SLETTER_SECOND) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(13);
		}
		if (elementType == Element.LLETTER_REVERSE) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(14);
		}
		if (elementType == Element.LLETTER_REVERSE_SECOND) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(15);
		}
		if (elementType == Element.LLETTER_REVERSE_THIRD) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(16);
		}
		if (elementType == Element.LLETTER_REVERSE_FOURTH) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(17);
		}
		if (elementType == Element.STICK_VERTICAL) {
			numberOfSquares = 4;
			elementXYData = (List<Integer>) elementsXYData.get(18);
		}
		
		int l = 1;
		for (k = 0; k < numberOfSquares; k++, l++) {
			g.setColor(color.get(colorNumber));		
			g.fillRect(positionX + elementXYData.get(2 * l), positionY + elementXYData.get((2 * l) + 1) , 20, 20);
			g.setColor(Color.BLACK);
			g.drawRect(positionX + elementXYData.get(2 * l), positionY + elementXYData.get((2 * l) + 1) , 20, 20);
		}

		List<Integer> listRowNumber = new ArrayList<Integer>();
		for (Map.Entry<String, Integer> entry : rowYCoordCountMap.entrySet()) {
		    if (entry.getValue() == 10) {
		    	int rowNumber = Integer.parseInt(entry.getKey().substring(3));
		    	if (!listRowNumber.contains(rowNumber))
					listRowNumber.add(rowNumber);
		    }
		}
		
		if (listRowNumber.size() > 0) {
			score = score + 10 * listRowNumber.size();
			
			for (int j = 0; j <= listRowNumber.size(); j++) {
				
				int rowNumber = 0;
				if (j == 0) rowNumber = listRowNumber.get(j);
				if (j == 1) rowNumber = listRowNumber.get(j) + 20;
				if (j == 2) rowNumber = listRowNumber.get(j) + 40;
				if (j == 3) rowNumber = listRowNumber.get(j) + 60;
				
				for (@SuppressWarnings("rawtypes")
				Iterator<ArrayList> iterator2 = elements.iterator(); iterator2.hasNext();) {
					ArrayList<Integer> rectangle1 = iterator2.next();
					int rowNumberFromList = rectangle1.get(1);
					if (rowNumberFromList == rowNumber) iterator2.remove();
					
				}
				
		    	for (@SuppressWarnings("rawtypes")
				Iterator<ArrayList> iterator2 = elements.iterator(); iterator2.hasNext();) {
					ArrayList<Integer> rectangle1 = iterator2.next();
					int rowNumberFromList = rectangle1.get(1);
					if (rowNumberFromList < rowNumber) {
						rectangle1.set(1, rectangle1.get(1) + 20);
					}
				}
			    
				for (int i = rowNumber; i >= 180; i  = i - 20) {					
			    Integer i2 = new Integer(i);
			    Integer i3 = i2 - 20;
			    rowYCoordCountMap.put("row" + i2.toString(), rowYCoordCountMap.get("row" + i3.toString()));
			    rowYCoordCountMap.put("row160", 0);			    
				}
			}
		    
		}
		
		for (ArrayList<Integer> rectangle2 : elements) {
			System.out.println();
			if (rectangle2.get(1) < 500) {
				if (rectangle2.get(1) <= 120) timer.stop();
				g.setColor(color.get((int) rectangle2.get(2)));
				g.fillRect(rectangle2.get(0), rectangle2.get(1), 20, 20);
				g.setColor(Color.BLACK);
				g.drawRect(rectangle2.get(0), rectangle2.get(1), 20, 20);
			}
		}
	}

//	public void metoda1() {
//		if (timerStep == 1) {
//			timer.stop();
//			timerStep = 1;
//			timer = new Timer(timerStep, this);
//			timer.setRepeats(false);
//			timer.start();
//		}
//	}
}

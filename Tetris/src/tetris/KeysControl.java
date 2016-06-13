package tetris;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class KeysControl implements Runnable{

	public KeysControl() {
	}

	@Override
	public void run() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					if (e.getKeyCode() == KeyEvent.VK_UP) {
						if (DrawSquare.elementType == Element.STICK) {
							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY) minY 
									= (int) DrawSquare.elements.get(i).get(1);
								}
							}
							if (DrawSquare.positionY < minY - 60) {
								DrawSquare.elementType = Element.STICK_VERTICAL;
						}
						}
						else if (DrawSquare.elementType == Element.STICK_VERTICAL)  {
							int numberOfSquares = 4;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int fourthMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < fourthMinY && j == 3)
											fourthMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 60 
									&& DrawSquare.positionY < secondMinY - 60
									&& DrawSquare.positionY < thirdMinY - 60
									&& DrawSquare.positionY < fourthMinY - 60
									&& DrawSquare.positionX > 80
									&& DrawSquare.positionX < 240)
							{
								DrawSquare.elementType = Element.STICK;
						}
						}
						
						if (DrawSquare.elementType == Element.SLETTER) {
							int numberOfSquares = 2;
							int firstMinY = 500;
							int secondMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 60 
									&& DrawSquare.positionY < secondMinY - 40)											
							DrawSquare.elementType = Element.SLETTER_SECOND;
						}								
						else if (DrawSquare.elementType == Element.SLETTER_SECOND) {
							int numberOfSquares = 3;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);											
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 20 
									&& DrawSquare.positionY < secondMinY - 20
									&& DrawSquare.positionY < thirdMinY - 40
									&& DrawSquare.positionX < 260)
							{
								DrawSquare.elementType = Element.SLETTER;
						}
						}
						
						if (DrawSquare.elementType == Element.TLETTER) {
							int numberOfSquares = 2;
							int firstMinY = 500;
							int secondMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 40 
									&& DrawSquare.positionY < secondMinY - 60
									&& DrawSquare.positionX < secondMinY - 60)
							DrawSquare.elementType = Element.TLETTER_SECOND;
						}								
						else if (DrawSquare.elementType == Element.TLETTER_SECOND) {
							int numberOfSquares = 3;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int k = -20; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 60 
									&& DrawSquare.positionY < secondMinY - 40
									&& DrawSquare.positionY < thirdMinY - 60
									&& DrawSquare.positionX > 100)
							DrawSquare.elementType = Element.TLETTER_THIRD;
						}
						else if (DrawSquare.elementType == Element.TLETTER_THIRD) {
							int numberOfSquares = 2;
							int firstMinY = 500;
							int secondMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 60 
									&& DrawSquare.positionY < secondMinY - 60)
							DrawSquare.elementType = Element.TLETTER_FOURTH;
						}
						else if (DrawSquare.elementType == Element.TLETTER_FOURTH) {
							int numberOfSquares = 3;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 40 
									&& DrawSquare.positionY < secondMinY - 40
									&& DrawSquare.positionY < thirdMinY - 40)
							DrawSquare.elementType = Element.TLETTER;
						}
						
						if (DrawSquare.elementType == Element.LLETTER) {
							int numberOfSquares = 3;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int k = -20; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 40 
									&& DrawSquare.positionY < secondMinY - 60
									&& DrawSquare.positionY < thirdMinY - 60
									&& DrawSquare.positionX	> 100)
										DrawSquare.elementType = Element.LLETTER_SECOND;
						}								
						else if (DrawSquare.elementType == Element.LLETTER_SECOND) {
							int numberOfSquares = 2;
							int firstMinY = 500;
							int secondMinY = 500;
							int k = -20; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 80 
									&& DrawSquare.positionY < secondMinY - 60
									&& DrawSquare.positionX > 100
									&& DrawSquare.positionX < 280)
								DrawSquare.elementType = Element.LLETTER_THIRD;
						}
						else if (DrawSquare.elementType == Element.LLETTER_THIRD) {
							int numberOfSquares = 3;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int k = -20; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 40 
									&& DrawSquare.positionY < secondMinY - 40
									&& DrawSquare.positionY < thirdMinY - 40
									&& DrawSquare.positionX < 280
									&& DrawSquare.positionX > 100)
										DrawSquare.elementType = Element.LLETTER_FOURTH;
						}
						else if (DrawSquare.elementType == Element.LLETTER_FOURTH) {
							int numberOfSquares = 2;
							int firstMinY = 500;
							int secondMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 40 
									&& DrawSquare.positionY < secondMinY - 40)
							DrawSquare.elementType = Element.LLETTER;
						}
						
						if (DrawSquare.elementType == Element.LLETTER_REVERSE) {
							int numberOfSquares = 3;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 20 
									&& DrawSquare.positionY < secondMinY - 20
									&& DrawSquare.positionY < thirdMinY - 20
									&& DrawSquare.positionX < 260)
							DrawSquare.elementType = Element.LLETTER_REVERSE_SECOND;
						}								
						else if (DrawSquare.elementType == Element.LLETTER_REVERSE_SECOND) {
							int numberOfSquares = 2;
							int firstMinY = 500;
							int secondMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 20 
									&& DrawSquare.positionY < secondMinY - 40)
							DrawSquare.elementType = Element.LLETTER_REVERSE_THIRD;
						}
						else if (DrawSquare.elementType == Element.LLETTER_REVERSE_THIRD) {
							int numberOfSquares = 3;
							int firstMinY = 500;
							int secondMinY = 500;
							int thirdMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < thirdMinY && j == 2)
											thirdMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 40 
									&& DrawSquare.positionY < secondMinY - 20
									&& DrawSquare.positionY < thirdMinY - 20
									&& DrawSquare.positionX < 260)
							DrawSquare.elementType = Element.LLETTER_REVERSE_FOURTH;
						}
						else if (DrawSquare.elementType == Element.LLETTER_REVERSE_FOURTH) {
							int numberOfSquares = 2;
							int firstMinY = 500;
							int secondMinY = 500;
							int k = 0; 
							for (int j = 0; j < numberOfSquares; j++, k = k + 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + k) {
										if ((int) DrawSquare.elements.get(i).get(1) < firstMinY && j == 0)
											firstMinY = (int) DrawSquare.elements.get(i).get(1);
										if ((int) DrawSquare.elements.get(i).get(1) < secondMinY && j == 1)
											secondMinY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}	
							if (DrawSquare.positionY < firstMinY - 20 
									&& DrawSquare.positionY < secondMinY - 20)
							DrawSquare.elementType = Element.LLETTER_REVERSE;
						}
					}
					
					
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						if (DrawSquare.elementType == Element.SQUARE) {
							int numberOfSquares = 1;
							boolean firstSquareOccupied = false;
							for (int j = 0; j < numberOfSquares; j++) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + 20) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20)
											firstSquareOccupied = true;												
									}
								}
							}	
							if (firstSquareOccupied == false && DrawSquare.positionX < 280)
								DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						
						if (DrawSquare.elementType == Element.BIGSQUARE) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 20; 
							
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40
												&& j == 0) firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40
												&& j == 1) secondSquareOccupied = true;
									}
								}
							}	
							if (firstSquareOccupied == false && secondSquareOccupied == false && DrawSquare.positionX < 260)
								DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						
						
						if (DrawSquare.elementType == Element.STICK) {
							int numberOfSquares = 1;
							boolean firstSquareOccupied = false;
							
							for (int j = 0; j < numberOfSquares; j++) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + 20) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 80) firstSquareOccupied = true;
									}
								}
							}	
							if (firstSquareOccupied == false && DrawSquare.positionX < 220)
								DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.STICK_VERTICAL) {
							int numberOfSquares = 4;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							boolean fourthSquareOccupied = false;
							int k = 60;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX  + 40 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 2)
											thirdSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 3)
											fourthSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false && fourthSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						
						if (DrawSquare.elementType == Element.SLETTER) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 20; 
							
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40
												&& j == 0) firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 60
												&& j == 1) secondSquareOccupied = true;
									}
								}
							}	
							if (firstSquareOccupied == false && secondSquareOccupied == false && DrawSquare.positionX < 240)
								DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						
						if (DrawSquare.elementType == Element.SLETTER_SECOND) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
														
						if (DrawSquare.elementType == Element.LLETTER) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_SECOND) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_THIRD) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 280 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_FOURTH) {
							int numberOfSquares = 1;
							boolean firstSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 0)
											firstSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						
						if (DrawSquare.elementType == Element.TLETTER) {
							int numberOfSquares = 1;
							boolean firstSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 60 && j == 0)
											firstSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 240 && firstSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.TLETTER_SECOND) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.TLETTER_THIRD) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.TLETTER_FOURTH) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						
						if (DrawSquare.elementType == Element.LLETTER_REVERSE) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_SECOND) {
							int numberOfSquares = 1;
							boolean firstSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 60 && j == 0)
											firstSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 240 && firstSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_THIRD) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 260 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_FOURTH) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 60 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 60 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX < 240 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX + 20;
						}
					}
					
												
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						if (DrawSquare.elementType == Element.SQUARE) {
								int numberOfSquares = 1;
								boolean firstSquareOccupied = false;
								for (int j = 0; j < numberOfSquares; j++) {
									for (int i = 0; i < DrawSquare.elements.size(); i++) {
										if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + 20) {
											if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20)
												firstSquareOccupied = true;												
										}
									}
								}	
							if (DrawSquare.positionX > 100 && firstSquareOccupied == false)
								DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.BIGSQUARE) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						
						if (DrawSquare.elementType == Element.STICK) {
							int numberOfSquares = 1;
							boolean firstSquareOccupied = false;
							for (int j = 0; j < numberOfSquares; j++) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + 20) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20)
											firstSquareOccupied = true;												
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						
						if (DrawSquare.elementType == Element.STICK_VERTICAL) {
							int numberOfSquares = 4;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							boolean fourthSquareOccupied = false;
							int k = 60;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX && j == 2)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX && j == 3)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 80 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false && fourthSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						
						if (DrawSquare.elementType == Element.LLETTER) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_SECOND) {
							
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 40 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 40 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 120 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						
						if (DrawSquare.elementType == Element.LLETTER_THIRD) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 40 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 120 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_FOURTH) {
							
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 40 && j == 0)
											firstSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 120 && firstSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}	
						
						if (DrawSquare.elementType == Element.TLETTER) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.TLETTER_SECOND) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.TLETTER_THIRD) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 40 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 120 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;									
						}
						if (DrawSquare.elementType == Element.TLETTER_FOURTH) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						
						if (DrawSquare.elementType == Element.SLETTER) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.SLETTER_SECOND) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						
						if (DrawSquare.elementType == Element.LLETTER_REVERSE) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_SECOND) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 20;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_THIRD) {
							int numberOfSquares = 3;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							boolean thirdSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 1)
											secondSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20 && j == 2)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false && thirdSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_FOURTH) {
							int numberOfSquares = 2;
							boolean firstSquareOccupied = false;
							boolean secondSquareOccupied = false;
							int k = 40;
							for (int j = 0; j < numberOfSquares; j++, k = k - 20) {
								for (int i = 0; i < DrawSquare.elements.size(); i++) {
									if ((int) DrawSquare.elements.get(i).get(1) == DrawSquare.positionY + k) {
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20 && j == 0)
											firstSquareOccupied = true;
										if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20  && j == 1)
											secondSquareOccupied = true;
									}
								}
							}	
						if (DrawSquare.positionX > 100 && firstSquareOccupied == false && secondSquareOccupied == false)
							DrawSquare.positionX = DrawSquare.positionX - 20;
						}
					}
					
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						
						if (DrawSquare.elementType == Element.SQUARE) {
							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						if (DrawSquare.elementType == Element.BIGSQUARE) {

							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.STICK) {

							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40
								        || (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 60){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.STICK_VERTICAL) {

							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 80;
						}
						
						if (DrawSquare.elementType == Element.SLETTER) {
																
							boolean firstCondition = false;
							boolean secondCondition = false;
							int minY = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX
									    || (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY = (int) DrawSquare.elements.get(i).get(1);
										firstCondition = true;
										secondCondition = false;
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY = (int) DrawSquare.elements.get(i).get(1);
										secondCondition = true;
										firstCondition = false;
									}
								}
							}
							if (firstCondition == true) DrawSquare.positionY = minY - 40;
							if (secondCondition == true) DrawSquare.positionY = minY - 20;
						}
						
						if (DrawSquare.elementType == Element.SLETTER_SECOND) {
							int minY20 = 520;
							int minY = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							if (minY20 < minY || minY20 == minY) DrawSquare.positionY = minY20 - 60;
							if (minY < minY20) DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.TLETTER) {

							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20
									    || (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.TLETTER_SECOND) {
							int minY20 = 520;
							int minY = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							if (minY20 < minY ) DrawSquare.positionY = minY20 - 40;
							if (minY < minY20 || minY20 == minY) DrawSquare.positionY = minY - 60;
						}
						if (DrawSquare.elementType == Element.TLETTER_THIRD) {
							int minYMinus20 = 520;
							int minY = 520;
							int minY20 = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minYMinus20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minYMinus20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							System.out.println();
							if ((minYMinus20 < minY && minYMinus20 < minY20) 
									|| (minYMinus20 < minY && minYMinus20 == minY20)) DrawSquare.positionY = minYMinus20 - 40;
							if (minY < minY20 && minY < minYMinus20 || (minY == minYMinus20 && minY == minY20) 
									|| (minY == minYMinus20 && minY < minY20) 
									|| (minY == minY20 && minY < minYMinus20)) DrawSquare.positionY = minY - 60;
							if ((minY20 < minY && minY20 < minYMinus20) 
									|| (minY20 < minY && minY20 == minYMinus20)) DrawSquare.positionY = minY20 - 40;
						}
						if (DrawSquare.elementType == Element.TLETTER_FOURTH) {
							int minY20 = 520;
							int minY = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							if (minY20 < minY || minY20 == minY) DrawSquare.positionY = minY20 - 60;
							if (minY < minY20) DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.LLETTER) {

							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX  
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.LLETTER_SECOND) {
							int minYMinus20 = 520;
							int minY = 520;
							int minY20 = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minYMinus20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minYMinus20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							System.out.println();
							if ((minYMinus20 < minY && minYMinus20 < minY20) 
									|| (minYMinus20 == minY && minYMinus20 == minY20)) DrawSquare.positionY = minYMinus20 - 60;
							if (minY < minY20 && minY < minYMinus20 || (minY < minYMinus20 && minY == minY20)) DrawSquare.positionY = minY - 40;
							if ((minY20 < minY && minY20 < minYMinus20)) DrawSquare.positionY = minY20 - 40;
						}
						if (DrawSquare.elementType == Element.LLETTER_THIRD) {
							int minYMinus20 = 520;
							int minY = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minYMinus20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minYMinus20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							if (minYMinus20 < minY ) DrawSquare.positionY = minYMinus20 - 20;
							if (minY < minYMinus20 || minYMinus20 == minY) DrawSquare.positionY = minY - 60;
						}
						if (DrawSquare.elementType == Element.LLETTER_FOURTH) {
							
							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 20
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 0
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.LLETTER_REVERSE) {

							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_SECOND) {
							int minY = 500;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX 
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20
										|| (int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40){
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY)
										minY = (int) DrawSquare.elements.get(i).get(1);
								}
							}
							DrawSquare.positionY = minY - 40;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_THIRD) {
							int minY20 = 520;
							int minY = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							if (minY20 < minY ) DrawSquare.positionY = minY20 - 20;
							if (minY < minY20 || minY20 == minY) DrawSquare.positionY = minY - 60;
						}
						if (DrawSquare.elementType == Element.LLETTER_REVERSE_FOURTH) {
							int minY = 520;
							int minY20 = 520;
							int minY40 = 520;
							for (int i = 0; i < DrawSquare.elements.size(); i++) {
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX - 0) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 20) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY20 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY) {
										minY20 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
								if ((int) DrawSquare.elements.get(i).get(0) == DrawSquare.positionX + 40) {
									if ((int) DrawSquare.elements.get(i).get(1) < minY40 && (int) DrawSquare.elements.get(i).get(1) > DrawSquare.positionY - 20) {
										minY40 = (int) DrawSquare.elements.get(i).get(1);
									}
								}
							}
							System.out.println();
							if ((minY < minY20 && minY < minY40) 
									|| (minY < minY40 && minY == minY20)) DrawSquare.positionY = minY - 40;
							if (minY20 < minY && minY20 < minY40) DrawSquare.positionY = minY20 - 40;
							if ((minY40 < minY20 && minY40 < minY) || (minY40 == minY20 && minY40 == minY)) DrawSquare.positionY = minY40 - 60;
						}
					}
				}
				return false;
			}
	});
}
}


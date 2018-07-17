package tetris;

public enum Element {
	
	 SQUARE(1),
	 BIGSQUARE(2),
	 LLETTER(3),
	 STICK(4),
	 TLETTER(5),
	 SLETTER(6),
	 ZLETTER(7),
	 STICK_VERTICAL(8),	 
	 LLETTER_SECOND(9),
	 LLETTER_THIRD(10),
	 LLETTER_FOURTH(11),
	 TLETTER_SECOND(12),
	 TLETTER_THIRD(13),
	 TLETTER_FOURTH(14),
	 SLETTER_SECOND(15),
	 LLETTER_REVERSE(16),
	 LLETTER_REVERSE_SECOND(17),
	 LLETTER_REVERSE_THIRD(18),
	 LLETTER_REVERSE_FOURTH(19);
	 
	 
	
	 private int elementNumber;

     Element(int elementNumber) {
         this.elementNumber = elementNumber;
     }

     public int getElementNumber() { 
         return elementNumber;
     }
}


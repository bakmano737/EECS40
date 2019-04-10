package bdemsky.Tetris;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;


public class Tetris {
	private Grid grid;
	private Piece currpiece;
	private PrimPiece[] pieces;
	
	private int fallCounter;
	static final private int HANG_STEPS = 3;
	
	private Random random;
	
	public Tetris() {
		grid=new Grid(12,20);
		pieces=new PrimPiece[3];
		
		pieces[0] = 
		  new PrimPiece(
		    new boolean[][]
		{{true,false,false,false},
		 {true,false,false,false},
		 {true,false,false,false},
		 {true,false,false,false}}, Color.RED);

		pieces[1] = 
				  new PrimPiece(
				    new boolean[][]
				{{true, true, true, false},
 				 {true, false,true, false},
				 {false,false,false,false},
				 {false,false,false,false}}, Color.GREEN);

		pieces[2] = 
				  new PrimPiece(
				    new boolean[][]
				{{false,false,false,false},
				 {false,false,true, false},
				 {false,true, false,false},
				 {true, false,false,false}}, Color.BLUE);		
		
		random = new Random(0);
		genNewPiece();
		new Piece(pieces[0],grid);
		
		fallCounter = HANG_STEPS;
		
		
	}
	
	private void genNewPiece() {
		int r = random.nextInt(3);
		currpiece=new Piece(pieces[r],grid);
	}
	
	public Piece getPiece() {
		return currpiece;
	}
	
	public void draw(Canvas c) {
		currpiece.draw(c);
		grid.draw(c);
	}
	
	public void update() {
		fallCounter--;
		if( fallCounter == 0 ) {
			fallCounter = HANG_STEPS;
			
			boolean settled = currpiece.fall();
			if( settled ) {
				grid.checkFullRow();
				
				genNewPiece();
				
			}
		}
	}
}

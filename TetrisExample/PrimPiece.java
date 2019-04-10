package bdemsky.Tetris;

public class PrimPiece {
	private boolean [][] blocklayout;
	private int color;
	
	public PrimPiece(boolean [][]layout, int color) {
		this.blocklayout=layout;
		this.color=color;
	}

	public int getColor() {
		return color;
	}
	
	public boolean hasBlock(int x, int y, int rotate) {
		switch(rotate) {
		case 0: //normal orientation
			return blocklayout[x][y];
		case 1: //
			return blocklayout[y][3-x];
		case 2:
			return blocklayout[3-x][3-y];
		case 3:
			return blocklayout[3-y][x];
		}
		throw new Error("bad rotation");
	}

}

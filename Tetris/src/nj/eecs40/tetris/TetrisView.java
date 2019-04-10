package nj.eecs40.tetris;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** TetrisView handles all of the drawing activities */
public class TetrisView extends SurfaceView 
{
	// Fields
	
	// Active Piece
	private TetrisPiece activePiece;
	// Next Piece - determined by random number
	private TetrisPiece nextPiece;
	
	//The Grid Board, to be used by everything in package
	TetrisGrid Grid;
	
	//Variables to help get Canvas size
	boolean gameStarted;
	Canvas canvas;
	int canvasWidth;
	int canvasHeigth;
	
	// Game over is public so that the thread can reach and change it freely
	public static boolean gameOver;
	
	//Socre of the player
	private static Integer score;
	
	//speed that the piece fall at
	private static int speed;
	
	// Constant values for piece types
	public static final int IPIECE = 1;
	public static final int JPIECE = 2;
	public static final int LPIECE = 3;
	public static final int SPIECE = 4;
	public static final int ZPIECE = 5;
	public static final int TPIECE = 6;
	public static final int OPIECE = 7;
	
	// KeyEvent Booleans
	public boolean movingLeft;
	public boolean movingRight;
	public boolean rotateClock;
	public boolean rotateCounter;
	
	
	// Random number generator
	Random rand;
	
	// Bitmap variables
	private Bitmap gridBMP;
	
	/** Default Constructor. Calls the super constructor with
	 *  the given context. Initializes canvas variables to false/0,
	 *  creates the random number generator, and decodes the bitmaps
	 * @param context
	 */
	public TetrisView(Context context)
	{
		// Call the SurfaceView constructor
		super(context);
		// Set Focusable to true so as to activate the key listener
		setFocusable(true);
		
		// Initialize canvas variables
		gameStarted = false;
		canvasWidth = 0;
		canvasHeigth = 0;
		rand = new Random();
		
		// Decode resources - grid bitmap and piece bitmaps
		// This is temporary for testing purposes.
		// gridBMP = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		gridBMP = null;
	}
	
	/** Return the holder of the parent SurfaceView */
	public SurfaceHolder getHolder() 
	{
		return super.getHolder();
	}
	
	/** Initialize -Only to be called by the TetrisView
	 *  It initializes the Canvas variables
	 *  and tells all other functions that game is started */
	private void Initialize(int w, int h, Canvas c)
	{
		canvas = c;
		canvasWidth = w;
		canvasHeigth = h;
		gameStarted = true;
		score = 0;
		speed = 0;
		
		//Creates the board and the individual pieces
		Grid = new TetrisGrid(canvasWidth, canvasHeigth, gridBMP);
		
		// Randomly choose the first two pieces
		activePiece = newTetrisPiece();
		activePiece.setOrientation(TetrisPiece.UP);
		nextPiece = newTetrisPiece();
	}
	
	/** onDraw - override from SurfaceView. Draw the background black, draw the grid
	 *  then draw the active piece. */
	protected synchronized void onDraw(Canvas c)
	{
		if(gameStarted)
		{
			// Set the background to black
			c.drawColor(Color.BLACK);
			
			// Give Grid its bitmap and draw it
			TetrisGrid.bmp = gridBMP;
			Grid.drawGrid(c);
			
			if(activePiece.settled)
			{
				activePiece = nextPiece;
				//done with displaying, hence position should now be up:
				activePiece.setOrientation(TetrisPiece.UP);
				nextPiece = newTetrisPiece();
			}
			
			// Give activePiece its bitmap and draw it
			activePiece.drawPiece(c);
			
			drawSideInfo(c);
		}
		else 
		{
			Initialize(c.getWidth(), c.getHeight(), c);
		}
		
	}
	
	/** This is to be called only by Tetris View.
	 *  It draws the scoring. */
	private void drawSideInfo(Canvas c)
	{
		float x = TetrisGrid.getCols()*TetrisPiece.getSize() + 
					(c.getWidth() - TetrisGrid.getCols()*TetrisPiece.getSize())/2;
		float y = TetrisPiece.getSize();
		Paint p = new Paint();
		p.setTextSize(20);
		p.setStrokeWidth(40);
		p.setColor(Color.WHITE);
		c.drawText("Score:", x-TetrisPiece.getSize(), y, p);
		c.drawText(score.toString(), x, y*2, p);
		
		nextPiece.dispOrigX=(int)x+TetrisPiece.getSize();
		nextPiece.dispOrigY=(int)y*4;
		nextPiece.setCoords();
		nextPiece.drawDispPiece(c);
	}
	
	/** newTetrisPiece - randomly generate a new tetris piece */
	public TetrisPiece newTetrisPiece()
	{
		gameOver = true;
		int piece = rand.nextInt(7) + 1;
		
		switch(piece)
		{
			case IPIECE:
			{
				return new IPiece(getResources());
			}
			case JPIECE:
			{
				return new JPiece(getResources());
			}
			case LPIECE:
			{
				return new LPiece(getResources());
			}
			case SPIECE:
			{
				return new SPiece(getResources());
			}
			case ZPIECE:
			{
				return new ZPiece(getResources());
			}
			case TPIECE:
			{
				return new TPiece(getResources());
			}
			case OPIECE:
			{
				return new OPiece(getResources());
			}
			default:
			{
				// If the random number is not in our desired range
				// give a new OPiece. No reason why its just better
				// than throwing an error or an exception, just let
				// the game keep playing
				return new OPiece(getResources());
			}
		}
	}
	
	// KeyEvent handling
	public boolean onKeyDown( KeyEvent event, int keyCode )
	{
		synchronized(this.activePiece)
		{
			switch(keyCode)
			{
				case KeyEvent.KEYCODE_DPAD_UP:
				{
					//getActivePiece().rotate(getActivePiece().getOrientation(), TetrisPiece.CLOCKWISE);
					return true;
				}
				case KeyEvent.KEYCODE_SPACE:
				{
					//getActivePiece().rotate(getActivePiece().getOrientation(), TetrisPiece.COUNTER);
					return true;
				}
				case KeyEvent.KEYCODE_DPAD_LEFT:
				{
					getActivePiece().translate(-TetrisPiece.getSize(), 0);
					return true;
				}
				case KeyEvent.KEYCODE_DPAD_RIGHT:
				{
					getActivePiece().translate(TetrisPiece.getSize(), 0);
					return true;
				}
				case KeyEvent.KEYCODE_DPAD_DOWN:
				{
					getActivePiece().translate(0, TetrisPiece.getSize());
					return true;
				}
			}
			return false;
		}
	}

	/** onKeyUp - override for android os call. switch on the keyCode. Left, Right, and Down
	 *  translate the piece by one grid square in the appropriate directon. Up rotates the 
	 *  piece clockwise and SPACE rotates the piece counter clockwise. */
	public boolean onKeyUp( int KeyCode, KeyEvent event )
	{
		synchronized(this.activePiece)
		{
			switch(KeyCode)
			{
				case KeyEvent.KEYCODE_DPAD_UP:
				{
					if(getActivePiece().checkBottom() || getActivePiece().checkSides())
					{
						//getActivePiece().freezePiece();
					}
					else
					{
						getActivePiece().rotate(getActivePiece().getOrientation(), TetrisPiece.CLOCKWISE);
					}
					return true;
				}
				case KeyEvent.KEYCODE_SPACE:
				{
					if(getActivePiece().checkBottom() || getActivePiece().checkSides())
					{
						//getActivePiece().freezePiece();
					}
					else
					{
						getActivePiece().rotate(getActivePiece().getOrientation(), TetrisPiece.COUNTER);
					}
					return true;
				}
				case KeyEvent.KEYCODE_DPAD_LEFT:
				{
					if(getActivePiece().checkBottom() || getActivePiece().checkSides())
					{
						//getActivePiece().freezePiece();
					}
					else
					{
						getActivePiece().translate(-1, 0);
					}
					return true;
				}
				case KeyEvent.KEYCODE_DPAD_RIGHT:
				{
					if(getActivePiece().checkBottom() || getActivePiece().checkSides())
					{
						//getActivePiece().freezePiece();
					}
					else
					{
						getActivePiece().translate(1, 0);
					}
					return true;
				}
				case KeyEvent.KEYCODE_DPAD_DOWN:
				{
					if(getActivePiece().checkBottom() || getActivePiece().checkSides())
					{
						//getActivePiece().freezePiece();
					}
					else
					{
						getActivePiece().translate(0, 1);
					}
					return true;
				}
			}
			return false;
		}
	}	
	
	/** Increments score of game by desired amount */
	public static void addScore(int incScore){ score += incScore;   }
	
	/**Increments the speed by 1 */
	public static void incSpeed()			{  speed++;				}
	
	// Accessor Methods
	public static int 	getSpeed()			{ return speed;			}
	public Canvas 		getCanvas()			{ return canvas;		}
	public TetrisPiece 	getActivePiece()	{ return activePiece;	}
	public TetrisPiece 	getNextPiece()		{ return nextPiece;		}
	
	// Set Methods
	public void setActivePiece	( TetrisPiece piece )	{ activePiece = piece;	}
	public void setNextPiece	( TetrisPiece piece )	{ nextPiece = piece;	}
}

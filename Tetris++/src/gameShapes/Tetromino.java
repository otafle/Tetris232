package gameShapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import component.GamePanel;
import geometry.SpaceNode;

public class Tetromino {
	private SpaceNode square1;
	private SpaceNode square2;
	private SpaceNode square3;
	private SpaceNode square4;
	
	private boolean active; // va servir a savoir quand spwaner les autre pieces
	private GamePanel game; // Pour pouvoir spawner des nouveaux tetrominos! LOL
	
	public Tetromino(SpaceNode space, GamePanel fame){
	    game = fame; // Rock'n'roll baby! LOL  -Loli 
	    active = true;
		square1 = space;
		square2 = space.getRight();
		square3 = space.getDown();
		square4 = space.getDown().getRight();
		square1.setOccupied(true);
		square2.setOccupied(true);
		square3.setOccupied(true);
		square4.setOccupied(true);
	}

	public void draw(Graphics2D g2d){
		AffineTransform matBackup = new AffineTransform(g2d.getTransform());
		g2d.setColor(Color.YELLOW);
		
		square1.drawSquare(g2d);
		square2.drawSquare(g2d);
		square3.drawSquare(g2d);
		square4.drawSquare(g2d);
		
		g2d.setTransform(matBackup);
	}
	
	public void goDown(){
		if(square4.getDown() == null || square3.getDown().getOccupied() || square4.getDown().getOccupied()){ //permet de detecter la collision par en-bas
		    active = false; // qd active est faux on transfet le controle aux  enfants (autre pieces)
		    game.spawnTetromino(4, 2); //(spawn la nouvelle piece)
			return;
		}
		if(active) {
		    square1.setOccupied(false);
	        square2.setOccupied(false);
	        square4 = square4.getDown();
	        square3 = square3.getDown();
	        square2 = square2.getDown();
	        square1 = square1.getDown();
	        square3.setOccupied(true);
	        square4.setOccupied(true);
		}
	}
	
	public void goLeft(){
		if(square1.getLeft() == null){
			return;
		}
		if(active) {
		    square2.setOccupied(false);
		    square4.setOccupied(false);
		    square1 = square1.getLeft();
		    square3 = square3.getLeft();
		    square2 = square2.getLeft();
		    square4 = square4.getLeft();
		    square1.setOccupied(true);
		    square3.setOccupied(true);
		}
	}
	
	public void goRight(){
		if(square2.getRight() == null){
		    return;
		}
		if(active) {
		    square1.setOccupied(false);
		    square3.setOccupied(false);
		    square2 = square2.getRight();
		    square4 = square4.getRight();
		    square1 = square1.getRight();
		    square3 = square3.getRight();
		    square2.setOccupied(true);
		    square4.setOccupied(true);
		}
	}
}

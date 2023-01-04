# Dama game movements

### Description
Pawn movements depend on the pawn type. The type of the pawn can be one of two types namely white or black. 
The type indicate the position of chessboard (white down and black top).
The pawn can perform an update when it turns into checkers, to do so the pawn must reach the last row of the chessboard.

## Default Movement
It's assign for all pawn to start. This possible movement are `forwardRight()` and `forwardLeft()`. The two movements are influenced by the type of pawn.

The positions of chessBoard are declaration: 
``` Java
//Increment or decrement row
final int RIGHT = 1;
final int LEFT = -1;
//Increment or decrement column
final int TOP = 1;
final int BOTTOM = -1;
```
This is example for setting direction: _(it's change for only movement of type pawn)_
``` Java
 if (pawn.getType()) {
      this.directionRow = TOP;
      this.directionColumn = RIGHT;
 } else {
   this.directionRow = BOTTOM;
   this.directionColumn = LEFT;
 }
```
### Capture
The pawn performs the capture only if an empty square is available after the pawn to be captured. 
Pawns can make multiple captures in one move, as long as they do not exceed three captures in a row.
> **The pawn must be able to make a maximum of 3 captures**

## Dama Movement

The dama movement implement the default method and adding two new moves: `backRight()` and `backLeft()`, 
for implement new moves it set the variable of default moves pawn and use method `forwardRight()` and `forwardLeft()`.

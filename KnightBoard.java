public class KnightBoard{
  private int board[][];
  private int moves[][];

  /**
  Default constructor.
  Throws IllegalArgumentException when either parameter is <= 0.
  */
  public KnightBoard(int rows,int cols){
    if (row < 0 || col < 0){
      throw new IllegalArgumentException("No negative values for your board dimensions!";
    }
    board = new int[row][col];
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        board[x][y] = 0;
      }
    }
  }

  /*
  You get a blank board if you never called solve or when there is no solution.
  Blank boards display 0's as underscores.
  Returns the properly formatted string (see format for toString later in the post).
  */
  public String toString(){
    return "";
  }

  /*
  Modifies the board by labeling the moves from 1 (at startingRow,startingCol) up to the area of the board in proper knight move steps.
  Throws IllegalStateException when the board contains non-zero values.
  Throws IllegalArgumentException when either parameter is negative or out of bounds.
  Returns true when the board is solvable from the specified starting position.
  */
  public boolean solve(int startingRow, int startingCol){
    // illegal state exception
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[c].length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("Non-zero number on your original board!");
        }
      }
    }

    // illegal argument exception
    if (startingRow > board.length || startingCol > board[0].length || startingRow < 0 || startingCol < 0){
      throw new IllegalArgumentException("No negative parameters!");
    }

    return solveHelper(startingRow, startingCol, 1);
  }

  public boolean solveHelper(int row, int col, int stage){
    if (stage > board.length * board[0].length){
      return true;
    }
    board[row][col] = stage;
    boolean isSolved = (((
                          solveHelp(startingRow - 2, startingCol - 1, level + 1) ||
                          solveHelp(startingRow - 1, startingCol - 2, level + 1)
                          ) ||
                          (
                          solveHelp(startingRow + 1, startingCol - 2, level + 1) ||
                          solveHelp(startingRow + 2, startingCol - 1, level + 1)
                          )
                          ) ||
                          (
                          (
                          solveHelp(startingRow + 2, startingCol + 1, level + 1) ||
                          solveHelp(startingRow + 1, startingCol + 2, level + 1)
                          ) ||
                          (
                          solveHelp(startingRow - 1, startingCol + 2, level + 1) ||
                          solveHelp(startingRow - 2, startingCol + 1, level + 1)
                          )
                          )
                          );
    if (!isSolved){
      board[row][col] = 0;
    }
    return isSolved;
  }

  /*
  Throws IllegalStateException when the board contains non-zero values.
  Throws IllegalArgumentException when either parameter is negative or out of bounds.
  Returns the number of solutions from the starting position specified.
  */
  public int countSolutions(int startingRow, int startingCol){
    return 1;
  }





}

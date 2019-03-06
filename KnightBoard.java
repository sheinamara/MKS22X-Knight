public class KnightBoard{
  private int board[][];
  private int moves[][];

  /**
  Default constructor.
  Throws IllegalArgumentException when either parameter is <= 0.
  */
  public KnightBoard(int row, int col){
    if (row < 0 || col < 0){
      throw new IllegalArgumentException("No negative values for your board dimensions!");
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
    String toReturn = "";
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[r].length; c++){
        if (board[r][c] < 10){
          toReturn = toReturn + " " + board[r][c] + " ";
        }
        else {
          toReturn = toReturn + board[r][c] + " ";
        }
      }
      toReturn = toReturn + "\n";
    }
    return toReturn;
  }

  /*
  Modifies the board by labeling the moves from 1 (at startingRow,startingCol) up to the area of the board in proper knight move steps.
  Throws IllegalStateException when the board contains non-zero values.
  Throws IllegalArgumentException when either parameter is negative or out of bounds.
  Returns true when the board is solvable from the specified starting position.
  */
  public boolean solve(int startingRow, int startingCol){
    /*
    // illegal state exception
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[c].length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("Non-zero number on your original board!");
        }
      }
    }
    */

    // illegal argument exception
    if (startingRow < 0 || startingCol < 0){
      throw new IllegalArgumentException("No negative parameters!");
    }

    return solveHelper(startingRow, startingCol, 1);
  }

  public boolean solveHelper(int row, int col, int stage){
    if (stage > board.length * board[0].length){
      return true;
    }
    if ((row >= board.length || row < 0) || (col >= board[0].length || col < 0)){
      return false;
    }
    if (board[row][col] != 0){
	    return false;
    }
    board[row][col] = stage;
    boolean isSolved = (((
                          solveHelper(row - 2, col - 1, stage + 1) ||
                          solveHelper(row - 1, col - 2, stage + 1)
                          ) ||
                          (
                          solveHelper(row + 1, col - 2, stage + 1) ||
                          solveHelper(row + 2, col - 1, stage + 1)
                          )
                          ) ||
                          (
                          (
                          solveHelper(row + 2, col + 1, stage + 1) ||
                          solveHelper(row + 1, col + 2, stage + 1)
                          ) ||
                          (
                          solveHelper(row - 1, col + 2, stage + 1) ||
                          solveHelper(row - 2, col + 1, stage + 1)
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
    /*
    // illegal state exception
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[c].length; c++){
        if (board[r][c] != 0){
          throw new IllegalStateException("Non-zero number on your original board!");
        }
      }
    }
    */

    // illegal argument exception
    if (startingRow < 0 || startingCol < 0){
      throw new IllegalArgumentException("No negative parameters!");
    }

    return countHelper(startingRow, startingRow, 1);
  }

  public int countHelper(int row, int col, int stage){
    if ((row >= board.length || row < 0) || (col >= board[0].length || col < 0)){
      return 0;
    }
    if (board[row][col] != 0){
      return 0;
    }
    if (stage == board.length * board[0].length){
      return 1;
    }
    board[row][col] = stage;
    int isCounted = (
                    (
                    (
                    countHelper(row - 2, col - 1, stage + 1)
                    +
                    countHelper(row - 1, col - 2, stage + 1)
                    )
                    +
		                (
                    countHelper(row + 1, col - 2, stage + 1)
                    +
                    countHelper(row + 2, col - 1, stage + 1)
                    )
                    )
                    +
                    (
                    (
                    countHelper(row + 2, col + 1, stage + 1)
                    +
                    countHelper(row + 1, col + 2, stage + 1)
                    )
                    +
		                (
                    countHelper(row - 1, col + 2, stage + 1)
                    +
                    countHelper(row - 2, col + 1, stage + 1)
                    )
                    )
                    );
    board[row][col] = 0;
    return isCounted;
  }

// DO NOT TOUCH AFTER THIS
  // Mr. K's testing
  //testcase must be a valid index of your input/output array
  public static void runTest(int i){
    KnightBoard b;
    int[]m =   {4,5,5,5,5};
    int[]n =   {4,5,4,5,5};
    int[]startx = {0,0,0,1,2};
    int[]starty = {0,0,0,1,2};
    int[]answers = {0,304,32,56,64};
    if(i >= 0 ){
      try{
        int correct = answers[i];
        b = new KnightBoard(m[i%m.length],n[i%m.length]);

        int ans  = b.countSolutions(startx[i],starty[i]);

        if(correct==ans){
          System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
        }else{
          System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
        }
      }catch(Exception e){
        System.out.println("FAIL Exception case: "+i);

      }
    }
  }

  public static void main(String[] args){
    runTest(0);
    runTest(1);
    runTest(2);
    runTest(3);
    runTest(4);

  }
}

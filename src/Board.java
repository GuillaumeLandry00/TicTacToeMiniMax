import java.util.ArrayList;

public class Board {

    Notations[][] board = new Notations[3][3];

    public Board(){
        for(int i =0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] = Notations.BLANK;
            }
        }
    }

   public void setTiles(Notations notations, int x, int y){
        board[x][y]= notations;
   }

   public Notations getTile(int x, int y){
        return board[x][y];
   }

    public void printBoard(){
        System.out.println( board[0][0] + " | " + board[0][1] + " | "
                + board[0][2]);
        System.out.println("----------");
        System.out.println(board[1][0] + " | " + board[1][1] + " | "
                + board[1][2]);
        System.out.println("----------");
        System.out.println(board[2][0] + " | " + board[2][1] + " | "
                + board[2][2]);
    }

    public ArrayList<String> getPlayableTiles(){
        ArrayList<String> possibilities = new ArrayList<>();
        for(int i =0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j].equals(Notations.BLANK)){
                    //We add avaible index
                    possibilities.add(i+","+j);
                }
            }
        }
        return  possibilities;
    }

    public ArrayList<String> getPlayableTiles(Notations[][] boardGame){
        ArrayList<String> possibilities = new ArrayList<>();
        for(int i =0;i<boardGame.length;i++){
            for(int j=0;j<boardGame[0].length;j++){
                if(boardGame[i][j].equals(Notations.BLANK)){
                    //We add avaible index
                    possibilities.add(i+","+j);
                }
            }
        }
        return  possibilities;
    }

    public boolean isFinished(){
        //We check the horizontal first
        for(int rows =0;rows<board.length; rows++){
            if(board[rows][0] == board[rows][1] && board[rows][1] == board[rows][2] && !board[rows][0].equals(Notations.BLANK)){
                return true;
            }
        }

        //We check the horizontal first
        for(int col =0;col<board[0].length; col++){
            if(board[0][col] == board[1][col] && board[1][col] == board[2][col] && !board[0][col].equals(Notations.BLANK)){
                return true;
            }
        }

        //We check diagonal
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && !board[0][0].equals(Notations.BLANK)){
            return true;
        }

        if(board[0][2] == board[1][1] && board[1][1] == board[2][0] && !board[0][2].equals(Notations.BLANK)){
            return true;
        }
        return  false;
    }
}

public class GameOfLife {
    private boolean[][] game;
    private int rows, cols;

    public GameOfLife(int rows, int cols) {
        this.game = new boolean[rows + 2][cols + 2];
        this.rows = rows;
        this.cols = cols;
    }

    public void setValueGame(int l, int c, boolean status) {
        this.game[l + 1][c + 1] = status;
    }

    public void envolve(/*int ticks*/) {
        //for (int i = 0; i < ticks; i++)
            this.nextState();
    }

    public boolean[][] getState() {
        boolean[][] state = new boolean[rows][cols];
        for (int l = 0; l < rows; l++) {
            for (int c = 0; c < cols; c++) {
                state[l][c] = game[l + 1][c + 1];
            }
        }
        return state;
    }

    //create a new array with a different reference
    private boolean[][] prevState() {
        boolean[][] prevState = new boolean[rows + 2][cols + 2];
        for (int r = 1; r < rows + 1; r++) {
            for (int c = 1; c < cols + 1; c++) {
                prevState[r][c] = game[r][c];
            }
        }
        return prevState;
    }

    private void nextState() {
        boolean[][] prevState = prevState();
        for (int l = 1; l <= rows; l++) {
            for (int c = 1; c <= cols; c++) {
                int aliveCirc = countAliveCirc(l, c, prevState);
                if (prevState[l][c]) {
                    if (!(aliveCirc == 2 || aliveCirc == 3))
                        game[l][c] = false;
                } else {
                    if (aliveCirc == 3) {
                        game[l][c] = true;
                    }
                }
            }
        }
    }

    private int countAliveCirc(int currentLine, int currentCol, boolean[][] prevState) {
        int count = 0;
        for (int l = currentLine - 1; l <= currentLine + 1; l++) {
            for (int c = currentCol - 1; c <= currentCol + 1; c++)
                if (prevState[l][c] && !(c == currentCol && l == currentLine))
                    count++;
        }
        return count;
    }
}

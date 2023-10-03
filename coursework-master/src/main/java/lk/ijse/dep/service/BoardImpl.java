package lk.ijse.dep.service;

public class BoardImpl implements Board {
    Piece[][] pieces;
    BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                pieces[i][j] = Piece.EMPTY;
            }

        }
    }

    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for (int i = 0; i < pieces[col].length; i++) {
            if (pieces[col][i] == Piece.EMPTY) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        return findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)] = move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {

    }

    @Override
    public Winner findWinner() {
        for (int i = 0; i < NUM_OF_COLS; i++) {
            for (int j = 0; j <= NUM_OF_ROWS - 4; j++) {
                Piece piece = pieces[i][j];
                if (piece != Piece.EMPTY &&
                        piece == pieces[i][j + 1] &&
                        piece == pieces[i][j + 2] &&
                        piece == pieces[i][j + 3]) {
                    return new Winner(pieces[i][j],i,j,i,j+3);
                }
            }
        }

        for (int i = 0; i < NUM_OF_ROWS; i++) {
            for (int j = 0; j <= NUM_OF_COLS - 5; j++) {
                Piece piece = pieces[j][i];
                if (piece != Piece.EMPTY &&
                        piece == pieces[j + 1][i] &&
                        piece == pieces[j + 2][i] &&
                        piece == pieces[j + 3][i]) {
                    return new Winner(pieces[i][j],j,i,j+3,i);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}

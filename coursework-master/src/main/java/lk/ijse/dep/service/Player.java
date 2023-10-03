package lk.ijse.dep.service;

public abstract class Player {
    Board board;

    public Player(Board board) {
        this.board = board;
    }
    public abstract void movePiece(int col);
}

// Field.java

import java.util.ArrayList;
import java.util.List;

public class Field {
    // Koma型のリスト
    private List<Koma> komalist;
    private int ynum = 0;
    private int xnum = 0;

    // 盤面の大きさを指定できる。
    // xnum -- ヨコの大きさ
    // ynum -- タテの大きさ
    public Field (int xnum, int ynum) {
        this.xnum = xnum;
        this.ynum = ynum;
    }

    // prepare -- 準備する
    // 盤面をタテynum、ヨコxnumの大きさで用意する。
    // また、各マスを . で埋めて、komalistに加える。
    public void prepare () {
        this.komalist = new ArrayList <> ();
        for (int y = 0; y < this.ynum; y++ ) {
            for (int x = 0; x < this.xnum; x++) {
                Koma koma = new Koma(x, y);
                this.komalist.add( koma );
            }
        }
    }

    // ヨコx、タテy のマスのコマ情報を得る。
    public Koma getKoma (int x, int y) {
        for ( Koma koma : this.komalist ) {
            int[] pos = koma.getPosition();
            if (pos[0] == x && pos[1] == y) {
                return koma;
            }
        }
        return null;
    }

    // ヨコx タテy の位置にコマを置く。
    // state -- B or W or .
    public void putKoma (int x, int y, String state) {
        Koma koma = this.getKoma( x, y );
        koma.setState( state );
    }

    // 盤面の状態を可視化する。
    // board [タテ] [ヨコ] に state をセットする。
    public void feature () {
        String [][] board = new String [ynum][xnum];
        for (Koma koma : this.komalist) {
            int[] pos = koma.getPosition();
            String state = koma.getState();
            board[pos[1]][pos[0]] = state;
        }
        System.out.println("\n\t0\t1\t2\t3\t4\t5\n");

        for (int y = 0; y < board.length; y++) {
            System.out.print( y + "\t");
            for (int x = 0; x < board[0].length; x++) {
                String b = board[y][x];
                System.out.print( b + "\t" );
            }
            System.out.println("\n");
        }
    }

    // 盤面に着手する手を検討する
    public ArrayList<Direction> move (int x, int y, String state) {
        ArrayList<Direction> moveList = new ArrayList <> ();
        Direction direction;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) { continue; }
                direction = new Direction( i, j );
                if (canMove( direction, x, y, state)) {
                    System.out.println( direction.getX() + ":" + direction.getY() + " TRUE");
                    moveList.add(direction);
                } else {
                    System.out.println( direction.getX() + ":" + direction.getY() + " FALSE");
                }
                    
            }
        }
        return moveList;
    }

    public boolean canMove (Direction p, int x, int y, String state) {
        int considerX = x + p.getX();
        int considerY = y + p.getY();
        if (considerX < 0 || considerY < 0) { return false; }
        if (considerX >= this.xnum || considerY >= this.ynum) { return false; }
        Koma koma = this.getKoma( considerX, considerY);
        int pos[] = koma.getPosition();
        System.out.println("x:" + pos[0] +
                           " y:" + pos[1] +
                           " " + koma.getState() +
                           " my:" + state);
        if (koma.getState().equals(".")) {
            return false;
        }
        if (koma.getState().equals(state)) {
            return false;
        }
        while (! koma.getState().equals(state)) {
            considerX += p.getX();
            considerY += p.getY();
            if (considerX < 0 || considerY < 0) { return false; }
            if (considerX >= this.xnum  || considerY >= this.ynum ) { return false; }
            koma = this.getKoma( considerX, considerY );
            pos = koma.getPosition();
            System.out.println("x:" + pos[0] +
                               " y:" + pos[1] +
                               " " + koma.getState() +
                               " my:" + state);
            if (koma.getState().equals(state)) {
                return true;
            }
            if (koma.getState().equals(".")) {
                return false;
            }
        }
        return false;
    }
    
    
}



// 修正時刻: Sat Jul 18 20:38:12 2020

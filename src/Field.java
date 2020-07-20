// Field.java

import java.util.ArrayList;
import java.util.List;

/**
 * 盤面に関する情報と操作
 */
public class Field {
    // Koma型のリスト
    private List<Koma> komalist;
    private int ynum = 0;
    private int xnum = 0;

    private boolean DEBUG = false;

    // 盤面の大きさを指定できる。
    // xnum -- ヨコの大きさ
    // ynum -- タテの大きさ
    public Field (int xnum, int ynum) {
        this.xnum = xnum;
        this.ynum = ynum;
    }

    public int getXnum () { return this.xnum; }
    public int getYnum () { return this.ynum; }

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
    public void putKoma (Action action) {
        Koma koma = this.getKoma( action.getX(), action.getY() );
        koma.setState( action.getPlayer() );
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

    /**
     * 着手チェック
     *   盤上にあるか？
     *   すでにコマが置かれていないか？
     * @param:
     *   Action action -- x, y, player
     * @return:
     *   boolean
     */
    public boolean checkAction ( Action action ) {
        int x = action.getX();
        int y = action.getY();
        if (x < 0 || x > this.xnum) { return false; }
        if (y < 0 || y > this.ynum) { return false; }
        Koma koma = this.getKoma( x, y );
        if (koma.getState().equals("B") || koma.getState().equals("W")) {
            return false;
        }
        return true;
    }

    
    /**
     * 着手したあと、挟んだ敵コマを裏返すための処理
     */
	public void afterAction ( Action action ) {
        Direction direction = new Direction();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) { continue; }
                direction.setX( i );
                direction.setY( j );
                // 着手できるかどうかを canMove でチェック
                // 正の数なら挟める(裏返せる)
                if (canMove( direction, action ) > 0) {
                    int countEnemy = flipOver( direction, action );
                }
            }
        }
	}

    /**
     * flipOver -- 敵のコマを裏返す
     *          canMoveメソッドで盤上のチェックはすませているので、
     *          ここではチェックしない。
     * @param:
     *   Direction p -- 相手のコマをはさめるかを検討するための方向を表す
     *                現地点が(0,0)で、北西が(-1,-1)、南東が(1, 1)
     *                ここでは、8方向のうちの1方向が引数として与えられる。
     *                そして、その方向に、はさめる敵コマがいくつあるかを
     *                調べる。
     *   Action action -- x, y, state の構造体代わりのクラス
     * @return:
     *   int countEnemy -- 敵コマの数
     *   0 -- 挟める敵コマがないので、0 を返している。
     */
    public int flipOver (Direction p, Action action) {
		String state = action.getPlayer();
        // 裏返したコマの数
        int countFlipOver = 0;
        // 検討する方向の、まず一番近い地点の x, y を得る。
        int considerX = action.getX() + p.getX();
        int considerY = action.getY() + p.getY();

        // その検討方向のコマ情報を得る。
        Koma koma = this.getKoma( considerX, considerY);

        // デバッグ用 -- その方向の x座標、y座標を表示する。
        if (DEBUG) {
            int[] pos = koma.getPosition();
            System.out.println("x:" + pos[0] +
                               " y:" + pos[1] +
                               " " + koma.getState() +
                               " my:" + state);
        }

        // "." でもなく、味方のコマでもないならば、敵のコマなので、その方向には
        // どれだけの敵のコマがあるのか、また、敵のコマのその向こうに味方のコマ
        // があって、敵のコマを挟める状態であるかを調べる。
        while (! koma.getState().equals( state )) {
            // 敵コマがあったので、そのコマを裏返す。
			koma.setState( state );
            countFlipOver++;
            // その方向にさらに1つ進める
            considerX += p.getX();
            considerY += p.getY();

            // その地点のコマ情報を得る。
            koma = this.getKoma( considerX, considerY );
            // デバッグ用 -- その地点の x座標、y座標を表示する。
            if (DEBUG) {
                int[] pos = koma.getPosition();
                System.out.println("x:" + pos[0] +
                                   " y:" + pos[1] +
                                   " " + koma.getState() +
                                   " my:" + state );
            }
            // その地点に味方のコマがあれば、終了
            if (koma.getState().equals(state)) {
                break;
            }
        }
        return countFlipOver;
    }



    /**
     * 盤面に着手する手を検討する
     * @param:
     *   Action action -- int x, int y, String player
     *             この場合の x, y は、着手した地点の座標を
     *             あらわす。
     * @return:
     *   ArrayList<Direction> directionList --
     *             Direction -- int x, int y, int point
     *   着手する地点を (0, 0) として、八方向に敵コマを検索し
     *   その向こうに味方のコマがあれば、挟める。
     *   挟めた敵コマの数を point、その方向を x, y として保持
     *   する。
     */
    public ArrayList<Direction> move (Action action) {
        ArrayList<Direction> directionList = new ArrayList <> ();
        Direction direction;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) { continue; }
                direction = new Direction( i, j );
                int countEnemy = canMove( direction, action );
                if (countEnemy > 0) {
                    // System.out.println( direction.getX() + ":" + direction.getY() + " TRUE");
                    direction.setPoint( countEnemy );
                    directionList.add(direction);
                } else {
                    // System.out.println( direction.getX() + ":" + direction.getY() + " FALSE");
                }
                    
            }
        }
        return directionList;
    }

    /**
     * canMove -- 着手できる地点かどうか検討する
     *            consider -- 検討する
     * @param:
     *   Direction p -- 相手のコマをはさめるかを検討するための方向を表す
     *                現地点が(0,0)で、北西が(-1,-1)、南東が(1, 1)
     *                ここでは、8方向のうちの1方向が引数として与えられる。
     *                そして、その方向に、はさめる敵コマがいくつあるかを
     *                調べる。
     *   int x -- 着手する地点の x座標
     *   int y -- 着手する地点の y座標
     *   String state -- 着手するプレーヤーが B か W か
     * @return:
     *   int countEnemy -- 挟める敵コマの数
     *   0 -- 挟める敵コマがないので、0 を返している。
     */
    public int canMove (Direction p, Action action) {
		String state = action.getPlayer();
        // 挟める敵コマの数
        int countEnemy = 0;
        // 検討する方向の、まず一番近い地点の x, y を得る。
        int considerX = action.getX() + p.getX();
        int considerY = action.getY() + p.getY();
        // その x, y が、盤上にあるかどうかをチェックする。
        if (considerX < 0 || considerY < 0) { return 0; }
        // this.xnum、this.ynum は、盤の大きさである。
        // かりに this.xnum, this.ynum が 6 だと、0...5 が盤の大きさ。
        if (considerX >= this.xnum || considerY >= this.ynum) { return 0; }
        // その検討方向のコマ情報を得る。
        Koma koma = this.getKoma( considerX, considerY);
        // デバッグ用 -- その方向の x座標、y座標を表示する。
        if (DEBUG) {
            int[] pos = koma.getPosition();
            System.out.println("x:" + pos[0] +
                               " y:" + pos[1] +
                               " " + koma.getState() +
                               " my:" + state);
        }
        // もしその方向のコマが "." なら、false を返す
        if (koma.getState().equals(".")) {
            return 0;
        }
        // もしその方向のコマが味方のコマなら、検討地点にはコマを置けない。
        if (koma.getState().equals( state )) {
            return 0;
        }
        // "." でもなく、味方のコマでもないならば、敵のコマなので、その方向には
        // どれだけの敵のコマがあるのか、また、敵のコマのその向こうに味方のコマ
        // があって、敵のコマを挟める状態であるかを調べる。
        while (! koma.getState().equals( state )) {
            // 挟める敵コマがあったので、そのコマをプラスする。
            countEnemy++;
            // その方向にさらに1つ進める
            considerX += p.getX();
            considerY += p.getY();
            // 盤上にあるかをチェック
            if (considerX < 0 || considerY < 0) { return 0; }
            if (considerX >= this.xnum  || considerY >= this.ynum ) { return 0; }
            // その地点のコマ情報を得る。
            koma = this.getKoma( considerX, considerY );
            // デバッグ用 -- その地点の x座標、y座標を表示する。
            if (DEBUG) {
                int[] pos = koma.getPosition();
                System.out.println("x:" + pos[0] +
                                   " y:" + pos[1] +
                                   " " + koma.getState() +
                                   " my:" + state );
            }
            // その地点に味方のコマがあれば、挟めるので、着手可能。
            if (koma.getState().equals(state)) {
                return countEnemy;
            }
            // その地点が "." なら、味方のコマがいないので、挟めない。
            // 着手できない。
            if (koma.getState().equals(".")) {
                return 0;
            }
        }
        return 0;
    }
}

// 修正時刻: Mon Jul 20 13:17:48 2020

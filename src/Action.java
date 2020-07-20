// Action.java

/**
 * Actionクラス -- Cの構造体のように使うためのクラス
 */
public class Action {
    private int x = 0;
    private int y = 0;
    private String player = "?";

    public Action () {}
    public Action ( int x, int y ) {
        this.x = x;
        this.y = y;
    }
    public Action ( int x, int y, String player ) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public int getX () { return this.x; }
    public int getY () { return this.y; }
    public String getPlayer () { return this.player; }

    public void setX (int x) { this.x = x; }
    public void setY (int y) { this.y = y; }
    public void setPlayer (String player) { this.player = player; }
}


// 修正時刻: Mon Jul 20 16:35:48 2020


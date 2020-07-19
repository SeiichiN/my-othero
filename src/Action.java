// Action.java

public class Action {
    private int x;
    private int y;
    private String player;

    public Action () {}
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


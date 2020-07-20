// Direction.java

/**
 * Cにおける構造体のようなものとしてクラスを使う
 * Direction -- 方向
 * 着手する地点を (0, 0) として、北西方向が (-1, -1)、
 * 南西方向が (-1, 1)、北方向が (-1, 0) となる。
 * また、その方向にある敵コマの数を point として保持する。
 */
public class Direction {
    private int x = 0;
    private int y = 0;
    private int point = 0;

    public Direction () {};
    public Direction (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Direction (int x, int y, int point) {
        this.x = x;
        this.y = y;
        this.point = point;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getPoint() { return this.point; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setPoint(int point) { this.point = point; }
}

// 修正時刻: Sun Jul 19 09:14:13 2020



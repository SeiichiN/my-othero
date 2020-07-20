// Score.java

public class Score {
    int b = 0;
    int w = 0;

    public Score () {}

    public int getB () { return this.b; }
    public int getW () { return this.w; }
    public void setB (int x) { this.b = x; }
    public void setW (int y) { this.w = y; }
    public void plusB () { this.b++; }
    public void plusW () { this.w++; }
    public void init () { this.b = 0; this.w = 0; }
}

// 修正時刻: Tue Jul 21 05:52:10 2020

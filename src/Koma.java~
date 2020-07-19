// Koma.java

/**
 * 一つ一つのコマの情報を保持しているクラス
 * 
 */
public class Koma  {
    // 盤面状態 黒B、白W、空.
    private String state;  // B : W : .
    private int x;     // ヨコ
    private int y;     // タテ

    // コンストラクタ -- 初期状態
    public Koma (int x, int y) {
        this.state = ".";
        this.x = x;
        this.y = y;
    }

    public String getState () {
        return this.state;
    }

    public void setState( String state ) {
        this.state = state;
    }

    public int[] getPosition () {
        int[] pos = { this.x, this.y };
        // int[] pos = { this.y, this.x };
        return pos;
    }
}

// 修正時刻: Thu Jul 16 21:12:15 2020

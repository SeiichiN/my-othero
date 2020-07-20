// Bot.java

import java.util.ArrayList;
import java.util.List;

public class Bot {
    public Bot () {}

    public Action selectMove ( Field field ) {
        ArrayList <Direction> directionList = new ArrayList <> ();
        List <Action> actionList = new ArrayList <> ();

        boolean check = false;
        int valueOfAction = 0;
        int maxValue = 0;
        Action bestAction = null;
        Action action = null;

        // 盤面のすべての地点を捜査する。
        // 盤上から外れていないかチェック。
        // すでに置かれているコマとかぶっていないかチェック。
        // その結果を actionList に格納する。
        for (int i = 0; i < field.getXnum(); i++) {
            for (int j = 0; j < field.getYnum(); j++) {
                action = new Action( i, j );
                check = field.checkAction( action );
                // 空のコマをリストに入れる
                if (check) {
                    actionList.add( action );
                }
            }
        }


        // actionList のそれぞれの着手(地点)について、
        // それぞれ八方向に挟める敵コマをチェック。
        // 挟める敵コマがあれば、その数をカウント。
        // それを valueOfAction に格納し、その最大値を
        // maxValue に格納し、また、その action(ele) を
        // bestAction に格納している。
        for ( Action ele : actionList ) {
            // System.out.println( "x:" + ele.getX() + " y:" + ele.getY() );
            directionList = field.move( ele );
            for ( Direction dir : directionList ) {
                System.out.println( dir.getPoint() );
                if (dir.getPoint() > 0) {
                    valueOfAction = dir.getPoint();
                }
                if (maxValue < valueOfAction) {
                    maxValue = valueOfAction;
                    bestAction = ele;
                }
            }
        }
''
        return bestAction;
    }
}

// 修正時刻: Mon Jul 20 17:21:26 2020


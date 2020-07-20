// Human.java

import java.util.ArrayList;

import util.GetUserInput;

public class Human {
    public Human () {}
    
    public Action selectMove (Field field) {
		Action action = new Action();
        ArrayList<Direction> directionList = new ArrayList <> ();

        boolean check = false;

        while (! check) {
            String query =
                GetUserInput.get("x座標 y座標 B/W の順で記述。ex.'32B'\nパス:B/W EXIT:999");

            if (query.equals("999")) { System.exit(1); }
            if (query.equals("b") || query.equals("B") || query.equals("w") || query.equals("W")) {
                action.setX( -1 );
                action.setY( -1 );
                action.setPlayer( query );
                break;
            }
            
            String strX = query.substring( 0, 1 );
            String strY = query.substring( 1, 2 );
            String userState = query.substring( 2, 3 ).toUpperCase();
            int userX = Integer.parseInt( strX );
            int userY = Integer.parseInt( strY );

            action.setX( userX );
            action.setY( userY );
            action.setPlayer( userState );

            // checkActionが通れば、checkは true となる。
            check = field.checkAction( action );
            if (! check) {
                System.out.println( action.getX() + ":" + action.getY() +
                                    " には打てません。");
            }
            // この地点での着手を検討する
            // DirectionList -- 挟めるコマがある方向のリスト
            directionList = field.move( action );
            directionList.forEach( ele -> {
                    System.out.println(ele.getX() + ":" + ele.getY() + ":" + ele.getPoint());
                });
            if (directionList.size() <= 0) {
                System.out.println("そこでは挟める敵コマがありません。");
                check = false;
            }
        }
        return action;
    }
}

// 修正時刻: Sun Jul 19 07:14:21 2020


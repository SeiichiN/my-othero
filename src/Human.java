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
            String strX = GetUserInput.get("x> ");
            String strY = GetUserInput.get("y> ");
            String userState = GetUserInput.get("B / W (EXIT:999)> ").toUpperCase();
            int userX = Integer.parseInt( strX );
            int userY = Integer.parseInt( strY );

            action.setX( userX );
            action.setY( userY );
            action.setPlayer( userState );

            if (action.getPlayer().equals("999")) { System.exit(1); }
            
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


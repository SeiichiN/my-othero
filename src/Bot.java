// Bot.java

import java.util.ArrayList;

public class Bot {
    public Bot () {}

    public Action selectMove ( Field field ) {
        Action action = new Action();
        ArrayList <Direction> directionList = new ArrayList <> ();

        boolean check = false;

        while (! check) {
            for (int i = 0; i < field.getXnum(); i++) {
                for (int j = 0; j < field.getYnum(); j++) {
                    action.setX( i );
                    action.setY( j );
                    check = field.checkAction( action );
                }
            }
        }
        return action;
    }
}

// 修正時刻: Mon Jul 20 08:24:31 2020


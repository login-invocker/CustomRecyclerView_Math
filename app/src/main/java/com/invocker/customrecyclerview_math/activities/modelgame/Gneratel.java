package com.invocker.customrecyclerview_math.activities.modelgame;

import java.util.Random;

public class Gneratel {
    public static final int EASY = 10;
    public static final int MEDIUM = 20;
    public static final int HARD = 100;

    public static LevelM generalLever(int count) {
        LevelM level = new LevelM();
        Random random = new Random();
        if (count <= EASY) {
            level.difficultLever = 1;
        } else {
            if (count <= MEDIUM) {
                level.difficultLever = 2;
            } else {
                if (count <= HARD) {
                    level.difficultLever = 3;
                } else level.difficultLever = 4;
            }
        }
        //random operator
        level.operator = random.nextInt(level.difficultLever);
        //random operation
        level.x = random.nextInt(level.arrMaxOperatorValue[level.difficultLever]) + 1;
        level.y = random.nextInt(level.arrMaxOperatorValue[level.difficultLever]) + 1;

        //random result: corect or  vrong
        level.correctWrong = random.nextBoolean();

        //random result
        if (level.correctWrong == false) {
            switch (level.operator) {
                case LevelM.ADD:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLever]);
                    } while (level.result == (level.x + level.y));
                    break;
                case LevelM.SUB:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLever]);
                    } while (level.result == (level.x - level.y));
                    break;
                case LevelM.DIV:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLever]);
                    } while (level.result == (level.x / level.y));
                    break;
                case LevelM.MUL:
                    do {
                        level.result = random.nextInt(level.arrMaxOperatorValue[level.difficultLever]);
                    } while (level.result == (level.x * level.y));
                    break;
                    default:break;
            }

        } else {
            switch (level.operator) {
                case LevelM.ADD:
                    level.result = level.x + level.y;
                    break;
                case LevelM.DIV:
                    level.result = level.x / level.y;
                    break;
                case LevelM.SUB:
                    level.result = level.x - level.y;
                    break;
                case LevelM.MUL:
                    level.result = level.x * level.y;
                    break;
                    default: break;
            }
        }
        // in to screen
        level.strOperator = String.valueOf(level.x) + level.arrOperatorText[level.operator] + String.valueOf(level.y);
        //String result

        level.strResult = String.valueOf(level.EQU_TEXT) + String.valueOf(level.result);
        return level;
    }
}

package com.huawei.touchgame;

public class scoremodel {
    String BoardName;
    int BoardScore;


    public scoremodel(String boardName, int boardScore) {
        BoardName = boardName;
        BoardScore = boardScore;
    }


    public String getBoardName() {
        return BoardName;
    }

    public int getBoardScore() {
        return BoardScore;
    }

}

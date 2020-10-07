package niuniverse.mankala.demo.controllers;

/*
pits are 0-5 for first player
pits are 7-12 for second player

pitNo 6 is  the First  players BigPit
pitNo 13 is the second players BigPit
    --------                            --------
    |      | (5) (4) (3)  (2)  (1) (0)  |      |
    |   6  | (7) (8) (9) (10) (11) (12) | 13   |
    --------                            -------
 */

import niuniverse.mankala.demo.models.CapturingStonesMsg;
import niuniverse.mankala.demo.models.GameIsFinishedMsg;
import niuniverse.mankala.demo.models.PlayingStonesMsg;

import java.util.ArrayList;

public class GameRules {

    // Check each player picked it's own pit which is not empty
    public boolean checkValidation(int pitClicked, int playerNo, ArrayList<Integer> stones){
        if (stones.size() !=14) {return false;}
        System.out.println(stones);
        if(playerNo==1 && (pitClicked <=5 && pitClicked >=0) && stones.get(pitClicked)!=0 ) {return true;}
        if(playerNo==2 && (pitClicked <=13 && pitClicked >=7) && stones.get(pitClicked)!=0 ) {return true;}

        if(stones.get(pitClicked)==0) {return false;}
        return false;
    }

    //putting one stone in each pit showed in top for the player except BigPit other user
    public PlayingStonesMsg putStonesInPits(int pitClicked, int playerNo, ArrayList<Integer> stones){
        PlayingStonesMsg playStones= new PlayingStonesMsg();
        playStones.startPoint = pitClicked;
        //put stones in the ring
        int pickedStonesNo = stones.get(pitClicked);
        stones.set(pitClicked,0);

        for(int i = pickedStonesNo ; i>0 ; i-- ){
            if(!isOtherSidesBigPit(pitClicked++,playerNo)) {
                pitClicked++;//add one more index
            }
            if (pitClicked > 13)    {pitClicked=0;}
            stones.set(pitClicked,stones.get(pitClicked)+1);
        }
        playStones.endPoint = pitClicked;
        playStones.scorePlayer1 = stones.get(6);
        playStones.scorePlayer2 = stones.get(13);
        playStones.stonesInPits = new ArrayList<Integer>(stones);
        // if lastStone put in your BigPit nextTurn is yours
        if(playStones.endPoint==6 || playStones.endPoint==13) {
            playStones.nextPlayerNo=playerNo;
        }else{
            playStones.nextPlayerNo=(playerNo==1) ? 2 :  1;
        }
        return playStones;
    }

    // is used not to put sone in otherplayer BigPit
    private boolean isOtherSidesBigPit(int pitNo,int player){
        if(pitNo==12 && player==1)  {return false;}
        else if(pitNo==5 && player==2)  {return false;}
        return true;
    }

    //rule if last stone was putted in empty pit otherSides stones are yours
    public CapturingStonesMsg lastSoneInEmptyPit(int pitClicked, int playerNo, ArrayList<Integer> stones,int lastPit){
        CapturingStonesMsg otherSideStoneYours = new CapturingStonesMsg();
        otherSideStoneYours.getStonesInPits = new ArrayList<Integer>();

        if(!(stones.get(lastPit) ==1)) {return null;}
        else if(lastPit==6 || lastPit==13) {return null;} //Big Pits 1 put has not point exchange
        otherSideStoneYours.otherSidesStones = true;

        otherSideStoneYours.getStonesInPits.add(lastPit);
        if((stones.get(12-lastPit) ==0))    {
            otherSideStoneYours.getStonesInPits.add(12-lastPit);
        }

        otherSideStoneYours.totalStones = stones.get(lastPit)+stones.get(12-lastPit);
        stones.set(lastPit,0);
        stones.set((12-lastPit),0);
         //adding point
        if(playerNo==1) {
            stones.set(6,stones.get(6)+otherSideStoneYours.totalStones );
            otherSideStoneYours.giveStones2Player = 1;
            otherSideStoneYours.nextPlayerNo=2;
        }
        else if(playerNo==2) {
            stones.set(13,stones.get(13)+otherSideStoneYours.totalStones );
            otherSideStoneYours.giveStones2Player = 2;
            otherSideStoneYours.nextPlayerNo=1;
        }
        otherSideStoneYours.stonesInPits = new ArrayList<Integer>(stones);
        otherSideStoneYours.scorePlayer1 = stones.get(6);
        otherSideStoneYours.scorePlayer2 = stones.get(13);

        return otherSideStoneYours;
    }


    //rule if all pits of a player get empty game Finished and other player capturese stons in left pits
    public GameIsFinishedMsg isGameFinished(ArrayList<Integer> stones){
        GameIsFinishedMsg gameFinished = new GameIsFinishedMsg();
        int player1Stones=0;
        int player2Stones=0;

        for(int i= 0; i<6 ; i++) { player1Stones=player1Stones+stones.get(i);}
        for(int i= 7; i<13 ; i++) { player2Stones=player2Stones+stones.get(i);}

        if(player1Stones!=0 && player2Stones!=0) {return null;}
        gameFinished.CaptureStons = new CapturingStonesMsg();
        gameFinished.CaptureStons.getStonesInPits = new ArrayList<Integer>();

        for(int i=0 ; i<13;i++){
            if(stones.get(i) !=0 && i!=6)   {
                gameFinished.CaptureStons.getStonesInPits.add(i);
                stones.set(i,0);
            }
        }

        if(player1Stones!=0)    { //give stones tp player 1
            gameFinished.CaptureStons.giveStones2Player=1;
            gameFinished.CaptureStons.totalStones=player1Stones;
            stones.set(6,stones.get(6)+player1Stones);
        }else{          //give stones tp player 2
            gameFinished.CaptureStons.giveStones2Player=2;
            gameFinished.CaptureStons.totalStones=player2Stones;
            stones.set(13,stones.get(13)+player2Stones);
        }

        gameFinished.CaptureStons.scorePlayer1 = stones.get(6);
        gameFinished.CaptureStons.scorePlayer2 = stones.get(13);

        //winner is?
        if (stones.get(6) > stones.get(13)){
            gameFinished.winner =1;
        }else{
            gameFinished.winner=2;
        }
        gameFinished.CaptureStons.nextPlayerNo=0;
        gameFinished.CaptureStons.stonesInPits = new ArrayList<Integer>(stones);
        return gameFinished;

    }
}


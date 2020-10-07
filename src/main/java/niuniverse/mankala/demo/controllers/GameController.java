package niuniverse.mankala.demo.controllers;

import niuniverse.mankala.demo.models.RequestMessage;
import niuniverse.mankala.demo.models.ResponseMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class GameController {
    @RequestMapping(method = RequestMethod.POST, value = "/playgame")
    public ResponseMessage DoTurn(@RequestBody RequestMessage input){
        System.out.println("clicked:"+input.PickedPitNo +" player:"+input.PlayerNo);
        return PlayingGame(input);
    }

    public ResponseMessage PlayingGame(RequestMessage input){
        ResponseMessage response= new ResponseMessage();
        GameRules playingProcess= new GameRules();

        if(playingProcess.checkValidation(input.PickedPitNo,input.PlayerNo,input.StonesInPits)){
            response.success = true;
            response.playStonesMsg     = playingProcess.putStonesInPits(input.PickedPitNo,input.PlayerNo,input.StonesInPits);
            response.otherSideStonsMsg = playingProcess.lastSoneInEmptyPit(input.PickedPitNo,input.PlayerNo,input.StonesInPits,response.playStonesMsg.endPoint);
            response.gameIsFinishedMg  = playingProcess.isGameFinished(input.StonesInPits);

        }else{
            response.success = false;
        }
        return response;
    }
}

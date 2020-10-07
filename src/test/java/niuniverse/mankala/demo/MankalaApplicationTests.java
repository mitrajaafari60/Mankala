package niuniverse.mankala.demo;

import niuniverse.mankala.demo.controllers.GameRules;
import niuniverse.mankala.demo.models.ResponseMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class MankalaApplicationTests {

    int pitClicked = 4;
    int playerNo=1;
    int lastPit=9;
    @Test
    void contextLoads() {
    }

    GameRules myTest = new GameRules();

        @Test
        public void checkValidation() {
            System.out.println("Test1");
            ResponseMessage _response= new ResponseMessage();
            System.out.println(myTest.checkValidation(pitClicked,playerNo,RandomArraylist()));

        }

        @Test
        public void putStonesInPits() {
            System.out.println("Test2");
            ResponseMessage _response= new ResponseMessage();
            System.out.println(myTest.putStonesInPits(pitClicked,playerNo,RandomArraylist()));
        }
        @Test
        public void lastSoneInEmptyPit() {
            System.out.println("Test3");
            ResponseMessage _response= new ResponseMessage();
            System.out.println(myTest.lastSoneInEmptyPit(pitClicked,playerNo,RandomArraylist(), lastPit));
        }
    
        @Test
        public void isGameFinished() {
            System.out.println("Test4");
            ResponseMessage _response= new ResponseMessage();
            System.out.println(myTest.isGameFinished(RandomArraylist()));
        }

    private ArrayList<Integer> RandomArraylist() {
            ArrayList<Integer> myList=new ArrayList<Integer>();
            int counter=0;
            for(int i =0 ; i<13 ;i++){
                if(i!=6) {
                    myList.add(getRandomNumber(0, 6));
                    counter = counter + myList.get(i);
                }else{
                    myList.add(36-counter);
                }
            }
             myList.add((6*12)-counter);
             System.out.println(myList);

        //  return new ArrayList<Integer>();
           return myList;
        }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


}

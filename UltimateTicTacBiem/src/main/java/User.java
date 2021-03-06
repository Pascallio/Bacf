import javafx.scene.control.Label;

/**
 * Created by pascal on 3-1-17.
 */
public class User {

    private String name;
    private int numOfLifes = 0;
    private int numOfBombs = 0;
    private int time;
    private String token;
    private Label bommenLabel;

    public User(){

    }

    public User(String name, String token){
        this.name = name;
        this.token = token;
    }

    public User(String name, String token, int numOfLifes, int numOfBombs){
        this.name = name;
        this.numOfLifes = numOfLifes;
        this.numOfBombs = numOfBombs;
        this.token = token;

    }

    public void setBombs(int num){
        this.numOfBombs = num;
    }

    public int getBombs(){
        return this.numOfBombs;
    }

    public void setNumOfLifes(int lifes){
        this.numOfLifes = lifes;
    }

    public int getNumOfLifes(){
        return this.numOfLifes;
    }

    public boolean hasLifesLeft(){
        return this.numOfLifes > 0;
    }

    public boolean hasBombsLeft(){
        return this.numOfBombs > 0;
    }

    public void setTime(int seconds){
        this.time = seconds;
    }

    public int getTime(){
        return this.time;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}

/**
 * Created by pascal on 3-1-17.
 */
public class User {

    private String name;
    private String avatar;
    private int numOfLifes;
    private int numOfBombs;
    private int time;

    public User(String name, String avatar){
        this.name = name;
        this.avatar = avatar;
    }

    public User(String name, String avatar, int numOfLifes, int numOfBombs){
        this.name = name;
        this.avatar = avatar;
        this.numOfLifes = numOfLifes;
        this.numOfBombs = numOfBombs;

    }

    public int getNumOfBombs(){
        return this.numOfBombs;
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

    public String getAvatar(){
        return this.avatar;
    }

}

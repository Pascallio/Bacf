/**
 * Created by pascal on 3-1-17.
 */
public class User {

    private String name;
    private String avatar;
    private int numOfLifes = 0;
    private int numOfBombs = 0;
    private int time;
    private String token;

    public User(){

    }

    public User(String name, String avatar, String token){
        this.name = name;
        this.avatar = avatar;
        this.token = token;
    }

    public User(String name, String avatar, String token, int numOfLifes, int numOfBombs){
        this.name = name;
        this.avatar = avatar;
        this.numOfLifes = numOfLifes;
        this.numOfBombs = numOfBombs;
        this.token = token;

    }



    public int getNumOfBombs(){
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

    public String getAvatar(){
        return this.avatar;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}

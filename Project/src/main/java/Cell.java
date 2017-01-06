/**
 * Created by pascal on 3-1-17.
 */
public class Cell{

    private Boolean bomb;
    private String token = "";

    public Cell(){

    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public boolean hasToken(){
        return this.token.length() > 0;
    }

    public void setBomb(){
        this.bomb = true;
    }

    public boolean getBomb(){
        return this.bomb;
    }

    public boolean hasBomb(){
        return this.bomb != null;
    }


}

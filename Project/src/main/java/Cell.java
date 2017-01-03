/**
 * Created by pascal on 3-1-17.
 */
public class Cell {

    private boolean bomb = false;
    private String token = "";

    public Cell(){

    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public void setBomb(){
        this.bomb = true;
    }

    public boolean getBomb(){
        return this.bomb;
    }

}

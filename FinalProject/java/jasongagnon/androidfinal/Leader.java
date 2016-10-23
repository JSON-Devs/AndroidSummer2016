package jasongagnon.androidfinal;

/**
 * Created by jasongagnon on 9/21/16.
 */
public class Leader {
    //private variables
    int _id;
    String _name;
    String _time;

    // Empty constructor
    public Leader(){

    }
    // constructor
    public Leader(int id, String name, String _type){
        this._id = id;
        this._name = name;
        this._time = _type;
    }

    // constructor
    public Leader(String name, String _type){
        this._name = name;
        this._time = _type;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getTime(){
        return this._time;
    }

    // setting phone number
    public void setTime(String _type){
        this._time = _type;
    }
}

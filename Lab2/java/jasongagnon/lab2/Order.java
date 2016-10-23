package jasongagnon.lab2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jasongagnon on 7/20/16.
 */
public class Order implements Parcelable {
    private String fName, lName, type;
    private int noOfBars;
    private boolean shippingExpedited;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNoOfBars() {
        return noOfBars;
    }

    public void setNoOfBars(int noOfBars) {
        this.noOfBars = noOfBars;
    }

    public boolean isShippingExpedited() {
        return shippingExpedited;
    }

    public void setShippingExpedited(boolean shippingExpedited) {
        this.shippingExpedited = shippingExpedited;
    }

    public Order(){
        fName = "";
        lName = "";
        type = "";
        noOfBars = 0;
        shippingExpedited = false;
    }

    public Order(String FName, String LName, String Type, int NoOfBars, boolean Shipping){
        fName = FName;
        lName = LName;
        type = Type;
        noOfBars = NoOfBars;
        shippingExpedited = Shipping;
    }

    public Order(Parcel in){
        String[] data= new String[5];

        in.readStringArray(data);
        this.fName = data[0];
        this.lName = data[1];
        this.type = data[2];
        this.noOfBars = Integer.parseInt(data[3]);
        this.shippingExpedited = Boolean.parseBoolean(data[4]);

    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.fName,
                this.lName, this.type,
                String.valueOf(this.noOfBars),
                String.valueOf(this.shippingExpedited)});
    }

    public static final Parcelable.Creator CREATOR= new Parcelable.Creator() {

        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);  //using parcelable constructor
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    @Override
    public String toString(){
        return fName + " - " + lName + " - " + type;
    }

}

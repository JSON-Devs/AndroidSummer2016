package jasongagnon.fragmentlab;

/**
 * Created by jasongagnon on 7/20/16.
 */
public class Order {
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

}

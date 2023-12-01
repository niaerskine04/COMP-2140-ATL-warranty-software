import java.util.ArrayList;

public class Customer extends BasePerson{
    
    public static String ID;
    private ArrayList<Warranty> Warranty;
    private ArrayList<Appliance> Appliances;

    public Customer() {}
    
    /**
     *Employee Login Constructor
     * @param password
     */
    public Customer(String name, String telephone,String password, String email, String  ID, ArrayList<Warranty> Warranty,ArrayList<Appliance> Appliances) {
        super(name,telephone,email,password);
        //this.ID = ID;
        this.Warranty= new ArrayList<Warranty>();
        this.Appliances= new ArrayList<Appliance>();
    }
        /**
     *Employee Login Constructor
     * @param password
     */
    public Customer(String ID,String password) {
        super(password);
        //this.ID = ID;
       // this.Warranty= new ArrayList<Warranty>();
     //   this.Appliances= new ArrayList<Appliance>();
    }

    /** 
    *  getter for the ID  attributes
    * @return String
    */
    public String getID() {
        return ID;
    }
        /** 
    *  getter for the Warranty  attributes
    * @return String
    */
    public ArrayList<Warranty> getWarranty() {
        return Warranty;
    }
    public void setWarranty(ArrayList<Warranty> warrantyList){
        Warranty = warrantyList;
    }
            /** 
    *  getter for the Appliances attributes
    * @return String
    */
    public ArrayList<Appliance> getAppliances() {
        return Appliances;
    }
}

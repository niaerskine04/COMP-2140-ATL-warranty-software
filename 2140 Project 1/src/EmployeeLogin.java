public class EmployeeLogin extends BasePerson{
    

    private String password;
 
    public EmployeeLogin() {}
    
    /**
     *Employee Login Constructor
     * @param password
     */
    public EmployeeLogin(String name, String telephone, String email, String  password) {
        super(name, telephone, email, password);
        this.password = password;
    }
     
    /** 
    *  getter for the password  attributes
    * @return String
    */
    public String getPassword() {
        return password;
    }
}



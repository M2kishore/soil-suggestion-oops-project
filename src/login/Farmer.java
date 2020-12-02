package login;

public class Farmer {
    private int ID;
    private String name, address, gender,password, email;
   
    public Farmer(int ID, String gender, String name,String password , String address , String email )
    {
        this.ID=ID;
        this.name=name;
        this.password=password;
        this.address=address;
        this.gender=gender;
        this.name=name;
        this.email=email;
      
    }
    public int getid(){
        return ID;
    }
    public String getname(){
        return name;
    }
    public String getaddress(){
        return address;
    }
    public String getgender(){
        return gender;
    }
    public String getemail(){
        return email;
    }
    public String getpass() {
    	return password;
    }

   
	}
}

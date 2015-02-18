package models;
 
import java.util.*;
import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;
 
@Entity
public class User0 extends Model {
 
	@Required
	@Id
    public String email;
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public boolean isIs_admin() {
		return is_admin;
	}



	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	@Required
    public String password;
    public String fullname;
    public boolean is_admin=false;

    

	public User0(String email, String password, String fullname, boolean isAdmin) {
		super();
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.is_admin = isAdmin;
	}

	
    
    public User0(String email, String password, String fullname) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
    }
    
    public static boolean validate(String email, String password) {
    	
    
        for(User0 u:  User0.find.findList()) {
        	if(u.email.equals(email) && u.password.equals(password))
        		return true;
           
        }
        return false;
        
    }
    public static  List<User0> findAll(){
        return  User0.find.findList();  
    }

	 /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long,User0> find = new Finder<Long,User0>(Long.class, User0.class); 
}
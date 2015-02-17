package models;
import java.util.*;
import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Page;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;
import play.data.format.*;
import play.data.validation.*;
import play.data.validation.Constraints.Required;

/**
 * Computer entity managed by Ebean
 */
@Entity 
public class Sav extends Model {
	

	private static final long serialVersionUID = 1L;
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User0 getUser() {
		return user;
	}



	public void setUser(User0 user) {
		this.user = user;
	}



	public String getDate_recu() {
		return date_recu;
	}



	public void setDate_recu(String date_recu) {
		this.date_recu = date_recu;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getSouhait() {
		return souhait;
	}



	public void setSouhait(String souhait) {
		this.souhait = souhait;
	}



	@Id
	public  Long id;
	
	@ManyToOne
	public User0 user;
	
	@Formats.DateTime(pattern="yyyy-MM-dd")
	public String date_recu;
	
	@Constraints.Required
	public String message;
	
	public String souhait;
	
	
	 /**
     * Generic query helper for entity Computer with id Long
     */
    public static Finder<Long,Sav> find = new Finder<Long,Sav>(Long.class, Sav.class); 
    

	
	/**
     * Return a page of computer
     *
     * @param page Page to display
     * @param pageSize Number of computers per page
     * @param sortBy Computer property used for sorting
     * @param order Sort order (either or asc or desc)
     * @param filter Filter applied on the name column
     */
    public static Page<Sav> page(int page, int pageSize, String sortBy, String order, String filter) {
    	
    	return 
                find.where()
                    .ilike("message", "%" + filter + "%")
                    .orderBy(sortBy + " " + order)
                    .fetch("user")
                    .findPagingList(pageSize)
                    .setFetchAhead(false)
                    .getPage(page);
        }

    
}
	
	
package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

/**
 * Manage a database of application
 */
public class Application extends Controller {
    
  
	/**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.Application.listSAV(0, "message", "asc", "")
    );
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result login() {
    	Form<User0> userForm = form(User0.class);
        return ok(
          loginForm.render(userForm)
        );
    }
    /**
     * Handle the login user
     */
    public static Result testLogin() {
        Form<User0> userForm = form(User0.class).bindFromRequest();

        if(userForm.hasErrors()) {
        	flash("success", "email ou mot de passe n'est pas valide!");
           // return badRequest("Non Autorisé");
            Form<User0> userForm1 = form(User0.class);
            return ok(
              loginForm.render(userForm1)
            );
        }
        /*  
         * TODO test user avec BD
         * 
         */
        //userForm
        
       if(User0.validate(userForm.get().email, userForm.get().password) ){
        
        session("connected user",userForm.get().email);
        flash("success", "user " + userForm.get().email + "connected");
        if(!userForm.get().isIs_admin())
        	return GO_HOME;
        else
        {
        	return GO_HOME;
        }
       }
       
       else 
       {
        
    	   flash("info","Résultat login email ou mot de passe n'est pas valide! "+userForm.get().email+"--"+ userForm.get().password);
            Form<User0> userForm1 = form(User0.class);
            return ok(
              loginForm.render(userForm1)
            );
       }
    }
    
    

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public static Result listSAV(int page, String sortBy, String order, String filter) {
        return ok(
            listSav.render(
                Sav.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    
    
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public static Result editSAV(Long id) {
        Form<Sav> savForm = form(Sav.class).fill(
        		Sav.find.byId(id)
        );
        return ok(
            editFormSav.render(id, savForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public static Result updateSAV(Long id) {
        Form<Sav> savForm = form(Sav.class).bindFromRequest();
        if(savForm.hasErrors()) {
            return badRequest(editFormSav.render(id, savForm));
        }
        savForm.get().update(id);
        flash("success", "Demande id: " + savForm.get().id + " modifié");
        return GO_HOME;
    }
    
    /**
     * Display the 'new SAV form'.
     */
    public static Result createSAV() {
        Form<Sav> savForm = form(Sav.class);
        return ok(
            createFormSav.render(savForm)
        );
    }
    
    /**
     * Handle the 'new sav form' submission 
     */
    public static Result saveSAV() {
        Form<Sav> savForm = form(Sav.class).bindFromRequest();
        if(savForm.hasErrors()) {
            return badRequest(createFormSav.render(savForm));
        }
        savForm.get().save();
        flash("success", "SAV : " + savForm.get().message + " crée.");
        return GO_HOME;
    }
    
    /**
     * Handle sav deletion
     */
    public static Result deleteSAV(Long id) {
        Sav.find.ref(id).delete();
        flash("success", "Demande avec id:"+id+" supprimée");
        return GO_HOME;
    }
    
    
    /**********************************COMP******************************************************************/
    
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return login();
    }

    /**
     * Display the paginated list of computers.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
            list.render(
                Computer.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
            )
        );
    }
    
    /**
     * Display the 'edit form' of a existing Computer.
     *
     * @param id Id of the computer to edit
     */
    public static Result edit(Long id) {
        Form<Computer> computerForm = form(Computer.class).fill(
            Computer.find.byId(id)
        );
        return ok(
            editForm.render(id, computerForm)
        );
    }
    
    /**
     * Handle the 'edit form' submission 
     *
     * @param id Id of the computer to edit
     */
    public static Result update(Long id) {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(editForm.render(id, computerForm));
        }
        computerForm.get().update(id);
        flash("success", "Computer " + computerForm.get().name + " has been updated");
        return GO_HOME;
    }
    
    /**
     * Display the 'new computer form'.
     */
    public static Result create() {
        Form<Computer> computerForm = form(Computer.class);
        return ok(
            createForm.render(computerForm)
        );
    }
    
    /**
     * Handle the 'new computer form' submission 
     */
    public static Result save() {
        Form<Computer> computerForm = form(Computer.class).bindFromRequest();
        if(computerForm.hasErrors()) {
            return badRequest(createForm.render(computerForm));
        }
        computerForm.get().save();
        flash("success", "Computer " + computerForm.get().name + " has been created");
        return GO_HOME;
    }
    
    /**
     * Handle computer deletion
     */
    public static Result delete(Long id) {
        Computer.find.ref(id).delete();
        flash("success", "Computer has been deleted");
        return GO_HOME;
    }
    

}
            

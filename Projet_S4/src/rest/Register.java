package rest;

import java.io.FileWriter;
import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.DAOUser;
import form.InscriptionForm;

@Path("/register")
public class Register {
 // This method is called if TEXT_PLAIN is request
    
	// This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
      return "<html> " + "<title>" + "Hello Jersey" + "</title>"
          + "<body><h1>" + "Hello Boulshit 2" + "</body></h1>" + "</html> ";
    }
    
	
    //CALL JSON
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUser(@FormParam("name") String username, @FormParam("email") String email, @FormParam("password") String pwd, @FormParam("conf") String conf){
        Gson gson = new Gson();

    	InscriptionForm form = new InscriptionForm();
    	User u = form.inscrireUtilisateurAndroid(username, email, pwd, conf);
    	if(form.getErreurs().isEmpty()){
    		JsonObject json = new JsonObject();
    		json.addProperty("success", "1");
    		json.addProperty("user", gson.toJson(u));
    		return gson.toJson(json);
    	}
    	else{
    		JsonObject json = new JsonObject();
    		json.addProperty("success", "2");
    		if(form.getErreurs().containsKey("email")){
    			json.addProperty("error", form.getErreurs().get("email"));
    		}
    		else if(form.getErreurs().containsKey("motdepasse")){
    			json.addProperty("error", form.getErreurs().get("motdepasse"));
    		}
    		else if(form.getErreurs().containsKey("nom")){
    			json.addProperty("error", form.getErreurs().get("nom"));
    		}
    		else if(form.getErreurs().containsKey("confirmation")){
    			json.addProperty("error", form.getErreurs().get("confirmation"));
    		}
    		else if(form.getErreurs().containsKey("emailSend")){
    			json.addProperty("error", form.getErreurs().get("emailSend"));
    		}
    		else{
    			json.addProperty("error", "Une erreur inconnue est survenue");
    		}
    		return gson.toJson(json);
    	}
            
        
    }
}

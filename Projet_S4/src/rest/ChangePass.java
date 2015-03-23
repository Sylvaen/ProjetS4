package rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Configurable;
import javax.ws.rs.core.MediaType;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import beans.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.DAOUser;
import form.InscriptionForm;

@Path("/chpass")
public class ChangePass {
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
    public Object getUser(@FormParam("name") String username, @FormParam("password") String pwd){
        Gson gson = new Gson();
        User u = DAOUser.getUserByUsername(username);
    	InscriptionForm form = new InscriptionForm();
    	if(u != null){
    		JsonObject json = new JsonObject();
    		ConfigurablePasswordEncryptor cpe = new ConfigurablePasswordEncryptor();
    		cpe.setAlgorithm("SHA-256");
    		u.setPwd(cpe.encryptPassword(pwd));
    		DAOUser.update(u);
    		json.addProperty("success", "1");
    		json.addProperty("user", gson.toJson(u));
    		return gson.toJson(json);
    	}
    	else{
    		JsonObject json = new JsonObject();
			json.addProperty("error", "Une erreur inconnue est survenue");
    		return gson.toJson(json);
    	}
            
        
    }
}

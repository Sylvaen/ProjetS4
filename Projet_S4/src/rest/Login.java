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

import dao.DAOSaccoche;
import dao.DAOStats;
import dao.DAOUser;

@Path("/login")
public class Login {
 // This method is called if TEXT_PLAIN is request
    
	// This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
      return "<html> " + "<title>" + "Hello Jersey" + "</title>"
          + "<body><h1>" + "Hello Boulshit" + "</body></h1>" + "</html> ";
    }
    
	
    //CALL JSON
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUser(@FormParam("username") String username){
        User user = DAOUser.getUserByUsername(username);
        if(user == null){
            return null;
        }
        else{
            Gson gson = new Gson();
            
            String json = gson.toJson(user);
               FileWriter writer;
            try
            {
                writer = new FileWriter(user.getName()+".json");
                writer.write(json);
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            return gson.toJson(user) + gson.toJson(DAOStats.getStatsByUser(user));
        }
            
        
    }
}

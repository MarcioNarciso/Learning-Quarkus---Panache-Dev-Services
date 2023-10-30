package br.dev.marcionarciso;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.resteasy.reactive.RestQuery;

import br.dev.marcionarciso.entities.Greeting;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional // ANOTAÇÃO OBRITAGÓRIA QUANDO SE USA HIBERNATE
    public String hello(@RestQuery String name) {
    	Greeting greeting = new Greeting();
    	greeting.name = name;
    	
    	greeting.persist();
    	
        return "Hello "+name;
    }
    
    @GET
    @Path("names")
    @Produces(MediaType.TEXT_PLAIN)
    public String names() {
		List<Greeting> greetings = Greeting.listAll();
		
		String names = greetings
			.stream()
			.map(g -> g.name)
			.collect(Collectors.joining(", "));
		
		return "Eu disse \"Olá\" para: "+names; 
	}
}

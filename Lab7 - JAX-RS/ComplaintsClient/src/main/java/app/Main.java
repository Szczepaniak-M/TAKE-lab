package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Arrays;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

public class Main {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        config.register(new ObjectMapper().registerModule(new JavaTimeModule()));
        Client client = ClientBuilder.newClient(config);

        String count = client.target("http://localhost:8080/Complaints/"
                + "resources/complaints/count")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println("Count: " + count);

        Complaint[] complaints = client.target("http://localhost:8080/Complaints/"
                + "resources/complaints")
                .request(MediaType.APPLICATION_JSON)
                .get(Complaint[].class);
        System.out.println("All complaints before update: \n" + Arrays.toString(complaints));

        Complaint complaint = null;
        for (Complaint com : complaints) {
            if (com.getStatus().equals("open")) {
                complaint = client.target("http://localhost:8080/Complaints/"
                        + "resources/complaints/" + com.getId())
                        .request(MediaType.APPLICATION_JSON)
                        .get(Complaint.class);
                break;
            }
        }
        System.out.println("Open complaint:\n" + complaint);
        
        complaint.setStatus("closed");
        Response response = client.target("http://localhost:8080/Complaints/"
                + "resources/complaints/" + complaint.getId())
                .request()
                .put(Entity.entity(complaint, MediaType.APPLICATION_JSON));
        System.out.println("Status:\n"+ response.getStatus());
        
        Complaint[] openComplaints = client.target("http://localhost:8080/Complaints/"
                + "resources/complaints")
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(Complaint[].class);
        System.out.println("Open complaints:\n" + Arrays.toString(openComplaints));

        client.close();
    }
}

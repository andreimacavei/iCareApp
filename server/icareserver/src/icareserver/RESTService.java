package icareserver;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

@Path("/")
public class RESTService {


	@POST
	@Path("/postexample")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ingestScrapedData(InputStream incomingData) {
		StringBuilder stringB = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				stringB.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + stringB.toString());

		// return HTTP response 200 in case of success
		return Response.status(200).entity(stringB.toString()).build();
	}

	@GET
	@Path("/getexample")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMatches(@DefaultValue("SomeDefaultValue") @QueryParam("param1") String param1,
			InputStream incomingData) {

		System.out.println("Param1=" + param1);

		JsonObject json = new JsonObject();


		if (param1.equalsIgnoreCase("SomeDefaultValue")) {

			json.addProperty("key1", "value1");
			json.addProperty("key2", "value2");
			json.addProperty("key3", "value3");

		}

		String result = json.toString();

		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}

}
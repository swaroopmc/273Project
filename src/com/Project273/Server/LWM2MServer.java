package com.Project273.Server;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Project273.Client.Track;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Path("/api")
public class LWM2MServer {
	
	
	@POST
	@Path("/registration")	
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN	)	
	public String createTrackInJSON() throws Exception {
		DBObject doc;
		String result="";
		
		MongoClient mongoClient = new MongoClient("167.88.36.125", 27017);
		try
		{							
			DB db  = mongoClient.getDB("adas");					
			boolean auth = db.authenticate("cmpe273", "1234567a".toCharArray());
			if(auth)
			{	
				DBCollection col = db.getCollection("ClientRegistration");
				doc = createDBObject();
				col.insert(doc);
				result = "Registered";						 			   
				
			}			
			else
			{
				result = "Not Registered";
			}
								
		}
		catch(Exception e)
		{
			throw e;
		}		
		mongoClient.close();
		return result;
		
	}	
	
	 private static DBObject createDBObject() {
	        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
	                                 	 
	        docBuilder.append("_id", "client-id1");	
	    	docBuilder.append("ClientEndPointName","Client1");
			docBuilder.append("Lifetime", 12345);
			docBuilder.append("Lwm2mVersion", 1.4);
			docBuilder.append("BindingMode", "S");
			docBuilder.append("SmsNumber", 512578672);
			docBuilder.append("ObjectInstances", "Yes");
	        return docBuilder.get();
	    }
	 
	 	@GET
		@Path("/select/{id}")	
	 	@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public List<Track> selectTrackInJSON(@PathParam("id") String _id) throws Exception {	
	 		DBObject dbo = null;			
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("_id", _id);		
			Track cr = new Track();	
			List<Track> lis = new ArrayList<Track>(); 
			
			MongoClient mongoClient = new MongoClient("167.88.36.125", 27017);
			try
			{							
				DB db  = mongoClient.getDB("adas");					
				boolean auth = db.authenticate("cmpe273", "1234567a".toCharArray());
				if(auth)
				{	
					DBCollection col = db.getCollection("ClientRegistration");				
					
					dbo = col.findOne(whereQuery);				   
				        String id = (String) dbo.get("_id");
				        String clientEndPointName = (String) dbo.get("ClientEndPointName");
				        int lifetime = (int) dbo.get("Lifetime");
				        double  lwm2m_version = (Double) dbo.get("Lwm2mVersion");
				        String binding_mode = (String) dbo.get("BindingMode");
				        int smsNumber = (int) dbo.get("SmsNumber");
				        String object_instances = (String) dbo.get("ObjectInstances");				        
				        cr.setId(id);
				        cr.setClientEndPointName(clientEndPointName);
				        cr.setLifetime(lifetime);
				        cr.setBindingMode(binding_mode);
				        cr.setLwm2mVersion(lwm2m_version);
				        cr.setSmsNumber(smsNumber);
				        cr.setObjInst(object_instances);
				        lis.add(cr);
				    }																									 			  				
									
			}
			catch(Exception e)
			{
				throw e;
			}		
			mongoClient.close();
			return lis;		
		}
	 	
	 	@POST
		@Path("/update/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateTrackInJSON(@PathParam("id")String _id) throws Exception {			 
			String result = null;
			
			MongoClient mongoClient = new MongoClient("167.88.36.125", 27017);
			try
			{							
				DB db  = mongoClient.getDB("adas");					
				boolean auth = db.authenticate("cmpe273", "1234567a".toCharArray());
				if(auth)
				{	
					DBCollection col = db.getCollection("Trackistration");				
					
					BasicDBObject newDocument = new BasicDBObject();
					newDocument.append("$set", new BasicDBObject().append("Lwm2mVersion", 2.2));

					BasicDBObject searchQuery = new BasicDBObject().append("_id", _id);

					col.update(searchQuery, newDocument);
					
					result = "Updated Successfully";
				}
				else
				{
					result = "Update Failed";
				}
			}
				catch(Exception e)
				{
					throw e;
				}		
				mongoClient.close();
				return result;	
			
		}
	 	
	 	@POST
		@Path("/delete/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteTrackinJSON(@PathParam("id")String _id) throws Exception {			 
			String result = null;
			
			MongoClient mongoClient = new MongoClient("167.88.36.125", 27017);
			try
			{							
				DB db  = mongoClient.getDB("adas");					
				boolean auth = db.authenticate("cmpe273", "1234567a".toCharArray());
				if(auth)
				{	
					DBCollection col = db.getCollection("ClientRegistration");														
					
					BasicDBObject document = new BasicDBObject();
					document.put("_id", _id);
					col.remove(document);
					
					result = "Deleted Successfully";
				}
				else
				{
					result = "Delete Failed";
				}
			}
				catch(Exception e)
				{
					throw e;
				}		
				mongoClient.close();
				return result;	
			
		}
	
	
	
}

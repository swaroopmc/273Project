package com.Project273.Client;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.util.*;

import javax.ws.rs.*;

import org.codehaus.jettison.json.JSONObject;

import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
@Path("bson/client")
public class DriverlessClient 
{
	private static final DBObject NULL = null;
	
	@GET
	@Path("/read/{ObjectID}/{ObjectInstanceID}/{ResourceID}")
	@Produces("application/json")
	public static String read(@PathParam("ObjectID") int ObjectID,@PathParam("ObjectInstanceID") int ObjectInstanceID,@PathParam("ResourceID") int ResourceID) throws ClassNotFoundException, SQLException, UnknownHostException
	{

		
		String dbs="";
		String Object="ACL";
		String resource="";
		if(ObjectID==2)
		{
			dbs="ACL";
			Object="ACL";
			if(ResourceID==2)
			{
				resource="ACL";
			}
			if(ResourceID==3)
			{
				resource="AccessControlOwner";
			}
		}
		
		if(ObjectID==3)
		{
			dbs="Device";
			Object="Device";
			switch(ResourceID)
			{
			case 4:
				resource="Reboot";break;
			case 0:
				resource="Manufacturer";break;
			case 11:
				resource="ErrorCode";break;
			case 16:
				resource="SupportedBindingsandModes";break;
			case 1:
				resource="ModelNumber";break;
			case 2:
				resource="SerialNumber";break;
			}		
		}
		
	DBObject empty=new BasicDBObject();
		 
	MongoClient mc=new MongoClient("167.88.36.125",27017);
	DB db=mc.getDB("adas");
	DBCollection tab=db.getCollection(dbs);
	DBObject doc=new BasicDBObject();
	doc.put("ObjectID", ObjectID);
	doc.put("ObjectInstanceID", ObjectInstanceID);
	DBObject search=tab.findOne(doc);
	
//	return search;
	
	return search.get(resource).toString();

	}
	
	@GET
	@Path("/read/{ObjectID}")
	@Produces("application/json")
	public static String reads(@PathParam("ObjectID") int ObjectID) throws ClassNotFoundException,  UnknownHostException
	{

		String dbs="";
		String g="";
		
		if(ObjectID==2)
			dbs="ACL";
		if(ObjectID==3)
			dbs="Device";
		
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBCollection tab=db.getCollection(dbs);
		DBObject doc=new BasicDBObject();
		doc.put("ObjectID", ObjectID);
		DBCursor search=tab.find(doc);	
		while(search.hasNext())
		{
			g=g+search.next()+"\n";	
		}
		return g;
	}
	
	
	@GET
	@Path("/read/{ObjectID}/{ObjectInstanceID}")
	@Produces("application/json")
	public static String reader(@PathParam("ObjectID") int ObjectID,@PathParam("ObjectInstanceID") int ObjectInstanceID) throws ClassNotFoundException, SQLException, UnknownHostException
	{
		String dbs="";
		String g="";
		if(ObjectID==2)
			dbs="ACL";
		if(ObjectID==3)
			dbs="Device";
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBCollection tab=db.getCollection(dbs);
		DBObject doc=new BasicDBObject();
		doc.put("ObjectID", ObjectID);
		doc.put("ObjectInstanceID", ObjectInstanceID);
		DBCursor search=tab.find(doc);
			while(search.hasNext())
			{		
				g=g+(search.next());
			}			
			return g;		
	}
	
	@POST
	@Path("/write/{ObjectID}/{ObjectInstanceID}/{ResourceID}/{UpdateValue}")
	public static String write(@PathParam("ObjectID") int ObjectID,@PathParam("ObjectInstanceID") int ObjectInstanceID,@PathParam("ResourceID") int ResourceID,@PathParam("UpdateValue") int UpdateValue) throws SQLException, ClassNotFoundException, UnknownHostException, InterruptedException
	{
		String dbs="";

		String Object="ACL";
		String resource="ACL";
		
		if(ObjectID==2)
			dbs="ACL";
		if(ObjectID==3)
			dbs="Device";
		
		if(ObjectID==2)
		{
			Object="ACL";
			if(ResourceID==2)
			{
				resource="ACL";
			}
			if(ResourceID==3)
			{
				resource="AccessControlOwner";
			}
		}
		
		if(ObjectID==3)
		{
			Object="Device";
			switch(ResourceID)
			{
			case 4:
				resource="Reboot";break;
			case 0:
				resource="Manufacturer";break;
			case 11:
				resource="ErrorCode";break;
			case 16:
				resource="SupportedBindingsandModes";break;
			case 1:
				resource="ModelNumber";break;
			case 2:
				resource="SerialNumber";break;
			}		
		}
	
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBCollection tab=db.getCollection(dbs);
		BasicDBObject doc=new BasicDBObject();
		doc.append("ObjectID", ObjectID);
		doc.append("ObjectInstanceID", ObjectInstanceID);
	
		BasicDBObject squery=new BasicDBObject();	
		
	
		squery.append("$set",new BasicDBObject().append(resource,UpdateValue));
		tab.update(doc, squery);
	
		return "Data updated";
	}
	
	@DELETE
	@Path("/delete/{ObjectID}/{ObjectInstanceID}")
	public static String delete(@PathParam("ObjectID") int ObjectID,@PathParam("ObjectInstanceID") int ObjectInstanceID) throws SQLException, ClassNotFoundException, UnknownHostException
	{
		String dbs="";
		
		if(ObjectID==2)
		{
			dbs="ACL";
		}
		if(ObjectID==3)
		{
			dbs="Device";
		}
		
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBCollection tab=db.getCollection(dbs);
		DBObject doc=new BasicDBObject();
		doc.put("ObjectID", ObjectID);
		doc.put("ObjectInstanceID", ObjectInstanceID);
		tab.remove(doc);
		return "\n\n2.02 Deleted\n\nSuccess";
	}
	
	
	@DELETE
	@Path("/delete/{ObjectID}")
	public static String delete(@PathParam("ObjectID") int ObjectID) throws SQLException, ClassNotFoundException, UnknownHostException
	{
		String dbs="";
		if(ObjectID==2)
		{
			dbs="ACL";
		}
		if(ObjectID==3)
		{
			dbs="Device";
		}
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBCollection tab=db.getCollection(dbs);
		DBObject doc=new BasicDBObject();
		doc.put("ObjectID", ObjectID);
		tab.remove(doc);
		return "\n\n2.02 Deleted\n\nSuccess";
	}

	@POST
	@Path("/create/{ObjectID}/{ObjectInstanceID}/{ACL}/{AccessControlOwner}")
	@Consumes("application/json")
	public static String create(@PathParam("ObjectID") int ObjectID,@PathParam("ObjectInstanceID") int ObjectInstanceID,@PathParam("ACL") int ACL,@PathParam("AccessControlOwner") int AccessControlOwner) throws SQLException, ClassNotFoundException
, UnknownHostException
			
	{
		String dbs="";
		if(ObjectID==2)
		{
			dbs="ACL";
		}
		if(ObjectID==3)
		{
			dbs="Device";
		}
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBCollection tab=db.getCollection(dbs);
		DBObject doc=new BasicDBObject();
		if(ObjectID==2)
		{
		doc.put("ObjectID", ObjectID);
		doc.put("ObjectInstanceID", ObjectInstanceID);
		doc.put("ACL", ACL);
		doc.put("AccessControlOwner", AccessControlOwner);
		}
			
		tab.insert(doc);
		
			return "\n2.01 Created\nCreated Object Instance\nSuccess ";
	}
	
	
	@GET
	@Path("/discover/{ObjectID}")
	@Produces("application/json")
	public static JSONObject discover(@PathParam("ObjectID") int ObjectID) throws ClassNotFoundException, SQLException, UnknownHostException  
	{
		//String dbs="";
		MongoClient mc;
		try {
			mc = new MongoClient("167.88.36.125",27017);
			JSONObject json=new JSONObject();
			DB db=mc.getDB("adas");
			boolean auth=db.authenticate("cmpe273", "1234567a".toCharArray());
			if(auth)
			{
			DBCollection tab=db.getCollection("attributes");
			DBObject o=new BasicDBObject();
			o.put("ObjectID", ObjectID);
			
			DBObject dbs=tab.findOne(o);	
			
		JSONObject d=new JSONObject();
		d.put("ObjectID", dbs.get("ObjectID"));
			
		return d;
		} 
			}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
//	@GET
//	@Path("/reset/{varX}")
//	public static String reset(@PathParam("varX") int x) throws UnknownHostException
//	{
//		MongoClient cl=new MongoClient("167.88.36.125",27017);
//		DB db=cl.getDB("adas");
//		DBCollection tab=db.getCollection("ACL");
//		DBObject doc=new BasicDBObject();
//		doc.put("Name", x);
//		tab.insert(doc);
//		return "Inserted";
//	}
	
	
	@POST
	@Path("/update/{boolFront}/{boolBottom}/{boolLeft}/{boolRight}/{frontdistance}/{speedfront}/{speedbottomleft}/{speedbottomright}/{speedtopleft}/{speedtopright}/{carspeed}")
	public static String updateValues(@PathParam("boolFront") boolean boolFront,@PathParam("speedfront") int speedfront,@PathParam("speedtopleft") int speedtopleft,@PathParam("speedtopright") int speedtopright,@PathParam("frontdistance") int frontdistance,@PathParam("boolLeft") boolean boolLeft
			,@PathParam("boolRight") boolean boolRight,@PathParam("speedbottomleft") int speedbottomleft,@PathParam("speedbottomright") int speedbottomright,@PathParam("boolBottom") boolean boolBottom,@PathParam("carspeed") int carspeed) throws UnknownHostException
	{
		
		String dbs="";
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBObject doc=new BasicDBObject();
		DBObject squery=new BasicDBObject();
		squery.put("ID", 1001);
		dbs="sensor";
		DBCollection tab=db.getCollection(dbs);
		doc.put("ID", 1001);
		doc.put("boolFront", boolFront);
		doc.put("speedfront", speedfront);
		doc.put("speedtopleft", speedtopleft);
		doc.put("speedtopright", speedtopright);
		doc.put("frontdistance", frontdistance);
		
		
		
		
		doc.put("boolLeft", boolLeft);
		doc.put("boolRight", boolRight);
		
		doc.put("speedbottomleft", speedbottomleft);
		doc.put("speedbottomright", speedbottomright);
		doc.put("boolBottom", boolBottom);
		doc.put("carspeed", carspeed);
		tab.update(squery,doc);
		return "Updated";

	}
	
	
	@GET
	@Path("/discover/{varX}/{varY}")
	public static String discovers(@PathParam("ObjectID") int ObjectID,@PathParam("ObjectInstanceID") int ObjectInstanceID) throws ClassNotFoundException, SQLException  
	{	
		String dbs="";
return "";
	}
	

	
	
	public static void main(String args[]) throws UnknownHostException
	{
//		String Object="ACL";
//		String resource="";
//		int ObjectID=2;
//		int ObjectInstanceID=2;
//		int ResourceID=2;
//		if(ObjectID==2)
//		{
//			Object="ACL";
//			if(ResourceID==2)
//			{
//				resource="ACL";
//			}
//			if(ResourceID==3)
//			{
//				resource="AccessControlOwner";
//			}
//		}
//		
//		if(ObjectID==3)
//		{
//			Object="Device";
//			switch(ResourceID)
//			{
//			case 4:
//				resource="Reboot";break;
//			case 0:
//				resource="Manufacturer";break;
//			case 11:
//				resource="ErrorCode";break;
//			case 16:
//				resource="SupportedBindingsandModes";break;
//			case 1:
//				resource="ModelNumber";break;
//			case 2:
//				resource="SerialNumber";break;
//			}		
//		}
//		
//	DBObject empty=new BasicDBObject();
//		 
//	MongoClient mc=new MongoClient("167.88.36.125",27017);
//	DB db=mc.getDB("adas");
//	DBCollection tab=db.getCollection("adas");
//	DBObject doc=new BasicDBObject();
//	doc.put("ObjectID", ObjectID);
//	doc.put("ObjectInstanceID", ObjectInstanceID);
//	DBObject search=tab.findOne(doc);
	
		String g="";
		String dbs="";
		MongoClient mc=new MongoClient("167.88.36.125",27017);
		DB db=mc.getDB("adas");
		DBCollection tab=db.getCollection(dbs);
		DBObject doc=new BasicDBObject();
		doc.put("ObjectID", 2);
		DBCursor search=tab.find(doc);
		while(search.hasNext())
		{
			g=g+search.next()+"\n";
		}
		System.out.println(g);
	}
	

}

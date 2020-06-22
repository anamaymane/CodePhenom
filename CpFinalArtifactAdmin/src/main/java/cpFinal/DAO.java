package cpFinal;

import static com.mongodb.client.model.Filters.*;

import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import org.bson.types.Binary;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.bson.types.Binary;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class DAO {
	// Problem List

	public static int getNumberOfTestCases(String problemId) {
		ArrayList<Document> list = mongodbConnection.getCollection("TestCase").find(Filters.eq("problemId", problemId))
				.into(new ArrayList<>());
		return list.size();
	}

	public static Iterator<org.bson.Document> problemListforAdmin(String displayWhat, String min, String max,
			String type) {
		Iterator<org.bson.Document> cursor = null;
		if (displayWhat.equals("all")) {
			cursor = mongodbConnection.getCollection("Problem").find().iterator();
		} else if (displayWhat.equals("difficulty")) {
			int minF = Integer.valueOf(min);
			int maxF = Integer.valueOf(max);
			cursor = mongodbConnection.getCollection("Problem")
					.find(and(gte("difficulty", minF), lte("difficulty", maxF))).iterator();
		} else
			cursor = mongodbConnection.getCollection("Problem").find(Filters.eq("type", type)).iterator();
		return cursor;
	}

	// Contest list
	public static Iterator<org.bson.Document> getCurrentContestsList() {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("Contest").find(gte("endDateRegistration", new Date())).iterator();
		//
		return cursor;
	}

	public static int isUserRegisteredToPlateform(String username, String email) {
		ArrayList<Document> list = mongodbConnection.getCollection("User")
				.find(or(Filters.eq("username", username), Filters.eq("email", email))).into(new ArrayList<>());
		return list.size();
	}

	// Top users List
	public static Iterator<org.bson.Document> topUsersList() {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("User").find().sort(new BasicDBObject("score", -1)).limit(8)
				.iterator();
		return cursor;
	}

	public static ArrayList<Document> problemCategory() {

		AggregateIterable<Document> documents = mongodbConnection.getCollection("Problem")
				.aggregate(Arrays.asList(Aggregates.group("$type", Accumulators.sum("total", "$count")),
						Aggregates.sort(Sorts.ascending("type"))));
		ArrayList<Document> listDoc = new ArrayList<Document>();
		for (Document doc : documents) {
			listDoc.add(doc);
		}
		return listDoc;

	}

	public static Iterator<org.bson.Document> messageReceived(String username) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("Message")
				.find(or(Filters.eq("receiver", username), Filters.eq("sender", username)))
				.sort(new BasicDBObject("date", -1)).iterator();
		return cursor;
	}

	public static Iterator<org.bson.Document> getUserDescription(String username) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("User").find(Filters.eq("username", username)).iterator();
		return cursor;
	}

	public static int deleteUserfFC(String username) {
		try {
			DeleteResult result = mongodbConnection.getCollection("User").deleteOne(Filters.eq("username", username));
			if (result.wasAcknowledged()) {
				return 1;
			}

		} catch (MongoException e) {
		}
		return 0;
	}

	public static int isProblemIdExist(String problemId) {
		ArrayList<Document> list = mongodbConnection.getCollection("Problem").find(Filters.eq("problemId", problemId))
				.into(new ArrayList<>());
		return list.size();
	}

	public static Iterator<org.bson.Document> getAllUsersList() {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("User").find().iterator();
		return cursor;
	}

	public static String NumberOfUserRegistredLastMonth() {
		long d = new Date().getTime();
		Date last15Days = new Date(d - (15 * 24 * 60 * 60 * 1000));
		ArrayList<Document> list = mongodbConnection.getCollection("User")
				.find(Filters.gte("dateRegistration", last15Days)).into(new ArrayList<>());
		return String.valueOf(list.size());
	}

	public static String TodaySubmission(String type) {
		long d = new Date().getTime();
		Date last15Days = new Date(d - (15 * 24 * 60 * 60 * 1000));
		ArrayList<Document> list =null;
		if (type.equals("all")) {
			 list = mongodbConnection.getCollection("Submission")
					.find(Filters.gte("dateSubmission", last15Days)).into(new ArrayList<>());
		} else {
			 list = mongodbConnection.getCollection("Submission")
						.find(and(Filters.gte("dateSubmission", last15Days),(Filters.eq("verdict", type)))).into(new ArrayList<>());
		}
		return String.valueOf(list.size());
	}

	public static ArrayList<Document> problemCategoryNumber() {
		AggregateIterable<Document> documents = mongodbConnection.getCollection("Problem")
				.aggregate(Arrays.asList(Aggregates.addFields(new Field<Integer>("count", 1)),
						Aggregates.group("$type", Accumulators.sum("total", "$count")),
						Aggregates.sort(Sorts.ascending("type"))));
		ArrayList<Document> listDoc = new ArrayList<Document>();
		for (Document doc : documents) {
			listDoc.add(doc);
		}
		return listDoc;
	}

	public static int isUsernameRegistered(String username) {
		ArrayList<Document> list = mongodbConnection.getCollection("User").find(Filters.eq("username", username))
				.into(new ArrayList<>());
		return list.size();
	}

	public static ArrayList<Document> userSolvedCategoryNumber(String username) {

		AggregateIterable<Document> documents = mongodbConnection.getCollection("Submission")
				.aggregate(Arrays.asList(
						Aggregates.match(and(Filters.eq("username", username), Filters.eq("verdict", "Accepted"))),
						Aggregates.addFields(new Field<Integer>("count", 1)),
						Aggregates.group("$type", Accumulators.sum("total", "$count")),
						Aggregates.sort(Sorts.ascending("type"))));

		ArrayList<Document> listDoc = new ArrayList<Document>();
		for (Document doc : documents) {
			listDoc.add(doc);
		}
		return listDoc;

	}

	public static ArrayList<Document> submissionStatistics(String username) {
		AggregateIterable<Document> documents = mongodbConnection.getCollection("Submission")
				.aggregate(Arrays.asList(Aggregates.match(Filters.eq("username", username)),
						Aggregates.addFields(new Field<Integer>("count", 1)),
						Aggregates.group("$verdict", Accumulators.sum("total", "$count")),
						Aggregates.sort(Sorts.ascending("verdict"))));
		ArrayList<Document> listDoc = new ArrayList<Document>();
		for (Document doc : documents) {
			listDoc.add(doc);
		}

		return listDoc;

	}

	public static ArrayList<Document> programmingLanguageUsedByUser(String username) {

		AggregateIterable<Document> documents = mongodbConnection.getCollection("Submission")
				.aggregate(Arrays.asList(Aggregates.match(Filters.eq("username", username)),
						Aggregates.addFields(new Field<Integer>("count", 1)),
						Aggregates.group("$languageName", Accumulators.sum("total", "$count")),
						Aggregates.sort(Sorts.ascending("type"))));
		ArrayList<Document> listDoc = new ArrayList<Document>();
		for (Document doc : documents) {
			listDoc.add(doc);
		}
		return listDoc;
	}

	public static Iterator<org.bson.Document> submissionsPerProblem(String problemId) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("Submission").find(Filters.eq("problemId", problemId)).iterator();
		return cursor;
	}

	public static Iterator<org.bson.Document> getAllSubmissions() {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("submission").find().sort(Sorts.descending("dateSubmission"))
				.iterator();
		return cursor;
	}

	public static Iterator<org.bson.Document> getproblemInfo(String problemId) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("Problem").find(Filters.eq("problemId", problemId)).iterator();
		return cursor;
	}

	public static Iterator<org.bson.Document> getProblemTestCases(String problemId) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("TestCase").find(Filters.eq("problemId", problemId))
				.sort(Sorts.descending("dateAjout")).iterator();
		return cursor;
	}

	public static void savefileIntoMongoDB(String ProblemId, String typeOfTest, String inputfile, String outputfile,
			Date dateAjout) throws IOException {
		long maxentries = getMaxDocument("TestCase");
		long _idd = maxentries + 1 ;
		Document test = new Document().append("_id",_idd).append("problemId", ProblemId).append("input", inputfile)
				.append("typeOfTest", typeOfTest).append("output", outputfile).append("dateAjout", dateAjout);
		mongodbConnection.getCollection("TestCase").insertOne(test);
	}

	public static Iterator<Document> getSubmissionDescription(String id) {
		Iterator<org.bson.Document> list = mongodbConnection.getCollection("submission")
				.find(Filters.eq("_id", new ObjectId(id))).into(new ArrayList<>()).iterator();
		return list;

	}
	public static Iterator<org.bson.Document> getSubmissions(String username) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("submission").find(Filters.eq("username", username))
				.sort(Sorts.descending("dateSubmission")).iterator();
		return cursor;
	}

	public static Iterator<org.bson.Document> supportedProgrammingLanguage() {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("AvailableLanguage").find().iterator();
		return cursor;
	}

	public static int isLanguageNameExiste(String name) {
		ArrayList<Document> list = mongodbConnection.getCollection("AvailableLanguage").find(Filters.eq("name", name))
				.into(new ArrayList<>());
		return list.size();
	}

	public static long getLanguageIdByName(String name) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("AvailableLanguage").find(Filters.eq("name", name)).iterator();
		return cursor.next().getLong("_id");
	}

	public static Iterator<org.bson.Document> announcement() {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("Announcement").find().sort(new BasicDBObject("dateAnnouncement", -1))
				.iterator();
		return cursor;
	}

	public static Iterator<org.bson.Document> getAnnouncementInfo(String id) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("Announcement").find(Filters.eq("_id", id)).iterator();
		return cursor;
	}

	public static int deleteTestCase(String id) {
		try {
			DeleteResult result = mongodbConnection.getCollection("TestCase")
					.deleteOne(Filters.eq("_id", new ObjectId(id)));
			if (result.wasAcknowledged()) {
				return 1;
			}

		} catch (MongoException e) {
		}
		return 0;
	}

	public static Iterator<org.bson.Document> getTestCaseInfo(String id) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("TestCase").find(Filters.eq("_id", new ObjectId(id))).iterator();
		return cursor;
	}

	public static int deleteLanguage(long id) {
		try {
			DeleteResult result = mongodbConnection.getCollection("AvailableLanguage")
					.deleteOne(Filters.eq("_id", id));
			if (result.wasAcknowledged()) {
				return 1;
			}

		} catch (MongoException e) {
		}
		return 0;
	}

	public static int getNumberOfSubmissionsPerProblem(String problemId) {
		ArrayList<Document> list = mongodbConnection.getCollection("Submission")
				.find(Filters.eq("problemId", problemId)).into(new ArrayList<>());
		return list.size();
	}
	public static String getNumberOfProblems(String option) {
		long d = new Date().getTime();
		Date last15Days = new Date(d - (15* 24 * 60 * 60 * 1000));
		ArrayList<Document> list =null;
		if (option.equals("all")) {
			 list = mongodbConnection.getCollection("Problem")
					.find().into(new ArrayList<>());
		} else {
			 list = mongodbConnection.getCollection("Problem")
						.find(Filters.gte("dateAjout", last15Days)).into(new ArrayList<>());
		}
		return String.valueOf(list.size());
	}
	
	
	public static Iterator<org.bson.Document> getSubmissionsPerProblem(String id) {
		Iterator<org.bson.Document> cursor = null;
		cursor = mongodbConnection.getCollection("Submission").find(Filters.eq("problemId", id)).iterator();
		return cursor;
	}
	public static String problemNumberOfSolves(String problemId) {  
		  ArrayList<Document> list = mongodbConnection.getCollection("Submission").find(and(Filters.eq("problemId",problemId),Filters.eq("verdict","Accepted"))).into(new ArrayList<>());
		  return String.valueOf(list.size());
	}


	public static Long getMaxDocument(String Coll){
		Iterator<org.bson.Document> cursor = null;
		 cursor  = mongodbConnection.getCollection(Coll).find().sort(new BasicDBObject("_id",-1)).limit(1).iterator();
		return cursor.next().getLong("_id");
	}

}

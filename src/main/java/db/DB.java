package db;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import users.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {
    private final Datastore datastore;
    private Morphia morphia;

    /**
     * Constructor for creating database instance
     */
    public DB() {
        morphia = new Morphia();
        morphia.mapPackage("users");
        datastore =
                morphia.createDatastore(new MongoClient("spring-demo-mongo", 27017), "test");
        datastore.ensureIndexes();
    }

    /**
     * Inserts given user to the database
     *
     * @param u a newly created user.
     */
    public void addUser(User u) {
        datastore.save(u);
    }

    /**
     * Fetches and prints all documents in database
     *
     * @return json array of all documents in database
     */
    public String getUsers() {
        final Query<User> query = datastore.createQuery(User.class);
        final List<User> employees = query.asList();

        return objToJson(employees);
    }

    /**
     * Returns the user with the specified ID.
     *
     * @param userID is the ID of the document in database
     * @return document in json format
     */
    public String getUser(ObjectId userID) {
        final Query<User> query = datastore.createQuery(User.class)
                .field("id").equal(userID);

        final List<User> employees = query.asList();
        return objToJson(employees);
    }

    /**
     * Deletes the document with the given ID from database.
     *
     * @param userID is the ID of the document to be removed
     */
    public void removeUser(ObjectId userID) {
        final Query<User> query = datastore.createQuery(User.class)
                .field("id").equal(userID);
        datastore.delete(query);
    }

    /**
     * Deletes all documents from database.
     */
    public void removeUsers() {
        final Query<User> query = datastore.createQuery(User.class);
        datastore.delete(query);
    }

    /**
     * Updates the fields of the document with given ID.
     *
     * @param userID  is the ID of the document to be updated
     * @param newUser contains the new fields
     * @return results of the update in json format
     */
    public String updateUser(ObjectId userID, User newUser) {
        final Query<User> query = datastore.createQuery(User.class)
                .field("id").equal(userID);
        final UpdateOperations<User> updateOperations = datastore.createUpdateOperations(User.class);

        if (newUser.getFirst_name() != null) {
            String newName = newUser.getFirst_name();
            updateOperations.set("first_name", newName);
        }

        if (newUser.getLast_name() != null) {
            String newLastName = newUser.getLast_name();
            updateOperations.set("last_name", newLastName);
        }

        if (newUser.getSalary() != 0) {
            int newSalary = newUser.getSalary();
            updateOperations.set("salary", newSalary);
        }

        final UpdateResults results = datastore.update(query, updateOperations);
        return results.toString();

    }

    /**
     * Translate object lists to the json array.
     *
     * @param query is the list of the user objects
     * @return json array of the given users
     */
    private String objToJson(List<User> query) {
        List<String> attsToRemove = Arrays.asList(new String[]{"className"});

        List<DBObject> dbObjList = new ArrayList<>(query.size());
        DBObject dbObj;
        for (Object obj : query) {
            dbObj = morphia.toDBObject(obj);
            for (int i = 0; i < attsToRemove.size(); i++) {
                dbObj.removeField(attsToRemove.get(i));
            }
            dbObjList.add(dbObj);
        }
        String json = JSON.serialize(dbObjList);
        return json;
    }
}

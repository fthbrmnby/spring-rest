package users;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity("users")
@Indexes(
        @Index(value="first_name", fields = @Field("first_name"))
)

/**
 * A simple POJO class. Represents the structure of the database.
 * Database objects are mapped to the this class's instances.
 */
public class User {
    @Id
    private ObjectId id;
    private String first_name;
    private String last_name;
    private int salary;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public User() {

    }

    public User(String first_name, String last_name, int salary) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
    }
}

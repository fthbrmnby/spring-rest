package api.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class UserEntity {

    @Id
    @Generated
    private String id;

    private String firstName;

    private String lastName;

    private int salary;

}


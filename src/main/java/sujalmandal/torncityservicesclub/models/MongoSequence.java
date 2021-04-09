package sujalmandal.torncityservicesclub.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document("MongoSequence")
@Data
@AllArgsConstructor
public class MongoSequence {

    @Id
    private String collectionName;
    private Long sequence;
}

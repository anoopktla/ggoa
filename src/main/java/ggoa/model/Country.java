package ggoa.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Countries")
public class Country {
    private String id;
    private String key;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

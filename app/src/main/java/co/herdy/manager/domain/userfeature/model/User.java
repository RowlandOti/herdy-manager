package co.herdy.manager.domain.userfeature.model;

public class User {


    public User(int id) {
        this.id = id;
    }

    private String value;
    private int id;


    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** User Details *****\n");
        stringBuilder.append("id=" + this.getId() + "\n");
        stringBuilder.append("value=" + this.getValue() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}

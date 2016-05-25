package co.herdy.manager.domain.userfeature.model;

public class User {


    public User() {

    }

    private String value;
    private String key;


    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** User Details *****\n");
        stringBuilder.append("key=" + this.getKey() + "\n");
        stringBuilder.append("value=" + this.getValue() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}

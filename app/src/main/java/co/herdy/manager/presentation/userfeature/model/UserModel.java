package co.herdy.manager.presentation.userfeature.model;

/**
 * Class that represents a user in the presentation layer.
 */

public class UserModel {

    int id;
    String value;

    public UserModel() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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

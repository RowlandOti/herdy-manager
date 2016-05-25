package co.herdy.manager.presentation.userfeature.model;

/**
 * Class that represents a user in the presentation layer.
 */

public class UserModel  {

  String key;
  String value;

  public UserModel() {

  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
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
    stringBuilder.append("key=" + this.getKey() + "\n");
    stringBuilder.append("value=" + this.getValue() + "\n");
    stringBuilder.append("*******************************");
    return stringBuilder.toString();
  }
}

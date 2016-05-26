package co.herdy.manager.domain.userfeature.model;

/**
 * User Entity used in the domain layer.
 */
public class User {

    private int userId;

    private String username;

    private String fullname;

    private String email;

    public User(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** User Details *****\n");
        stringBuilder.append("id=" + this.getUserId() + "\n");
        stringBuilder.append("fullname=" + this.getFullname() + "\n");
        stringBuilder.append("username=" + this.getUsername() + "\n");
        stringBuilder.append("email=" + this.getEmail() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}

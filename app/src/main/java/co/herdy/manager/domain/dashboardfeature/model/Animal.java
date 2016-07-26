package co.herdy.manager.domain.dashboardfeature.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "animals")
public class Animal extends Model {

    // The class Log identifier
    private static final String LOG_TAG = Animal.class.getSimpleName();

    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private Date date;
    @Column(name = "hatchno")
    private String hatchno;
    @Column(name = "sex")
    private String sex;
    @Column(name = "breed")
    private String breed;
    @Column(name = "status")
    private String status;
    @Column(name = "weaned")
    private boolean weaned;
    @Column(name = "litter")
    private String litter;


    public Animal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHatchno() {
        return hatchno;
    }

    public void setHatchno(String hatchno) {
        this.hatchno = hatchno;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getWeaned() {
        return weaned;
    }

    public void setWeaned(boolean weaned) {
        this.weaned = weaned;
    }

    public String getLitter() {
        return litter;
    }

    public void setLitter(String litter) {
        this.litter = litter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** Animal Entity Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("date=" + this.getDate() + "\n");
        stringBuilder.append("hatchno=" + this.getHatchno() + "\n");

        stringBuilder.append("sex=" + this.getSex() + "\n");
        stringBuilder.append("breed=" + this.getBreed() + "\n");
        stringBuilder.append("status=" + this.getStatus() + "\n");
        stringBuilder.append("weaned=" + this.getWeaned() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}

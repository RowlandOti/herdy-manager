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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** Animal Entity Details *****\n");
        stringBuilder.append("name=" + this.getName() + "\n");
        stringBuilder.append("date=" + this.getDate() + "\n");
        stringBuilder.append("hatchno=" + this.getHatchno() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }
}

package co.herdy.manager.data.userfeature.payload;

import android.util.Log;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(strict = false, name = "users")
public class DownloadPayloadCollection {

    // Class log identifier
    public final static String LOG_TAG = DownloadPayloadCollection.class.getSimpleName();

    @ElementList(entry = "item", inline = true)
    public List<String> resultString;


    public DownloadPayloadCollection() {
        this.resultString = new ArrayList<>();
    }


    public List<String> getResult() {
        Log.d(LOG_TAG, "CALLED FROM getResult() " + resultString.toString());
        return resultString;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** Download List *****\n");
        stringBuilder.append("resultString=" + this.getResult() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }

}

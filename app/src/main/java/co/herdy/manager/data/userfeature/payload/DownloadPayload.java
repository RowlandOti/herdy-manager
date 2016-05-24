package co.herdy.manager.data.userfeature.payload;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false, name = "user")
public class DownloadPayload {

    // Class log identifier
    public final static String LOG_TAG = DownloadPayload.class.getSimpleName();

    @Element(name = "item")
    private Item item;

    public DownloadPayload() {
        this.item = new Item();
    }

    @Root(strict = false, name = "item")
    public static class Item {

        public Item() {
        }

        @Element(name = "key")
        private String key;

        @Element(name = "value")
        private String value;

        public String getDownloadPayloadKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("key=" + this.key + "\n");
            stringBuilder.append("value=" + this.value + "\n");
            return stringBuilder.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("***** Download Details *****\n");
        stringBuilder.append("key=" + this.item.toString() + "\n");
        stringBuilder.append("*******************************");
        return stringBuilder.toString();
    }

    public String getDownloadPayloadKey() {
        return item.getDownloadPayloadKey();
    }

    public String getValue() {
        return item.getValue();
    }
}

package co.herdy.manager.data.api;

import android.content.Context;

import co.herdy.manager.data.userfeature.payload.DownloadPayload;
import co.herdy.manager.data.userfeature.payload.DownloadPayloadCollection;

import rx.Observable;

public class ApiManager extends ABaseApiManager {

    // The class Log identifier
    private static final String LOG_TAG = ABaseApiManager.class.getSimpleName();

    public ApiManager(Context context) {
        mContext = context;
    }

    /**
     * Download API
     */
    public Observable<DownloadPayloadCollection> listDownloads() {
        return getDownloadsApi().listDownloads();
    }

    public Observable<DownloadPayload> getDownloadById(String key) {
        return getDownloadsApi().getDownloadWithId(key);
    }
}

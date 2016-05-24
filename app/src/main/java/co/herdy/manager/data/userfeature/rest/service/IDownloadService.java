package co.herdy.manager.data.userfeature.rest.service;


import co.herdy.manager.data.userfeature.payload.DownloadPayload;
import co.herdy.manager.data.userfeature.payload.DownloadPayloadCollection;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface IDownloadService {

    // Constants for Downloads API End Points
    String DOWNLOADS = "/";
    String DOWNLOADS_ITEM = "/users/{key}";

    @GET(DOWNLOADS)
    Observable<DownloadPayloadCollection> listDownloads();

    @GET(DOWNLOADS_ITEM)
    Observable<DownloadPayload> getDownloadWithId(@Path("key") String userKey);

}

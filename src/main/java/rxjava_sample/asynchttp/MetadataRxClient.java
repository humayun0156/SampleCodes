package rxjava_sample.asynchttp;


import org.glassfish.jersey.client.rx.rxjava.RxObservable;
import rx.Observable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A generic client for communicating with the metadata service.
 * <p/>
 * Created by jobaer on 3/29/16.
 */
public class MetadataRxClient {
    private static Client client = ClientBuilder.newClient();
    private static final int defaultRetryAfter = 3;

    public Observable<String> getMetadataItems(List<String> itemUrls, final int retryCount) {
        Observable<String> obsUrls = Observable.from(itemUrls);
        return obsUrls.flatMap(url -> {
            return getMetadataItem(url, retryCount);
        });
    }

    public Observable<String> getMetadataItem(String itemUrl, final int retryCount) {
        Observable<Response> observable2 = RxObservable.from(client)
            .target(itemUrl)
            .request()
            .rx()
            .get();

        return observable2.flatMap(response -> {
            int status = response.getStatus();
            if (status == 503) {
                if (retryCount <= 0) {
                    return Observable.just("Got consecutive 503");
                } else {
                    int retryAfter = 23;//getRetryAfterFromHeader(response.getHeaderString("Retry-After"));
                    return delayedRequest(retryAfter, getMetadataItem(itemUrl, (retryCount - 1)));
                }
            } else {
                String responseBody = "";//response.readEntity(String.class);
                return Observable.just(responseBody);
            }
        });
    }

    private int getRetryAfterFromHeader(String headerString) {
        try {
            return Integer.parseInt(headerString);
        } catch (Exception e) {
            return defaultRetryAfter;
        }
    }

    private Observable<String> delayedRequest(long delay, Observable<String> source) {
        Observable<Long> interval = Observable.interval(delay, TimeUnit.SECONDS).take(1);
        return interval.flatMap(aLong -> source);
    }
}


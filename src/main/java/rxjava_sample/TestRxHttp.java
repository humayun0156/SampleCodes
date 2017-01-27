package rxjava_sample;

import rx.Observable;
import rxjava_sample.asynchttp.MetadataRxClient;

/**
 * Class for testing rx http client
 *
 * Created by jobaer on 3/30/16.
 */
public class TestRxHttp {
    private static final String url1 = "http://localhost:8080/metadata/always200";

    public static void main(String[] args) {
        System.out.println("Testing rx http requests ....");

        MetadataRxClient client = new MetadataRxClient();
        Observable<String> item = client.getMetadataItem(url1, 3);
        item.subscribe(resp -> {
            System.out.println("Got response");
            System.out.println(resp);
        });
    }
}

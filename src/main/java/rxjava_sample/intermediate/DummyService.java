package rxjava_sample.intermediate;

import rx.Observable;
import rxjava_sample.asynchttp.MetadataRxClient;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DummyService {
    public Observable<List<Book>> getPopularBooks() {
        List<Book> popularBooks = new ArrayList<>();
        popularBooks.add(new Book(1, "Book1", "http://localhost:8080/metadata/always200"));
        popularBooks.add(new Book(2, "Book2", "http://localhost:8080/metadata/always404"));
        popularBooks.add(new Book(3, "Book3", "http://localhost:8080/metadata/200after1"));
        popularBooks.add(new Book(4, "Book4", "http://localhost:8080/metadata/200after1"));
        popularBooks.add(new Book(5, "Book5", "http://localhost:8080/metadata/200after3"));
        popularBooks.add(new Book(6, "Book6", "http://localhost:8080/metadata/always503"));
        popularBooks.add(new Book(7, "Book7", "http://localhost:8080/metadata/404after1"));
        popularBooks.add(new Book(8, "Book8", "http://localhost:8080/metadata/always200"));
        popularBooks.add(new Book(9, "Book9", "http://localhost:8080/metadata/always503"));
        popularBooks.add(new Book(10, "Book10", "http://localhost:8080/metadata/404after1"));

        return Observable.just(popularBooks);
    }

    public static void main(String[] args) throws InterruptedException {
        DummyService service = new DummyService();
        Observable<List<Book>> popularBooks = service.getPopularBooks();
        popularBooks.subscribe(books -> {
            System.out.println("Updating the ui with initial book information");
            System.out.println(books);
            List<String> metaUrls = books.stream().map(Book::getMetaUrl).collect(toList());
            requestForMetadata(metaUrls);
        });
    }

    private static void requestForMetadata(List<String> metaUrls) {
        MetadataRxClient metadataRxClient = new MetadataRxClient();
        System.out.println("Requesting for metadata");
        Observable<String> result = Observable.from(metaUrls).flatMap(url -> {
            return metadataRxClient.getMetadataItem(url, 3);
        });

        result.subscribe(res -> {
            System.out.println("---- Got metadata ----- ");
            System.out.println(res);
        });
    }
}

class Book {
    private int id;
    private String title;
    private String metaUrl;

    public Book(int id, String title, String metaUrl) {
        this.id = id;
        this.title = title;
        this.metaUrl = metaUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaUrl() {
        return metaUrl;
    }

    public void setMetaUrl(String metaUrl) {
        this.metaUrl = metaUrl;
    }

    @Override
    public String toString() {
        return "Book[id=" + id + ", title=" + title + "]";
    }
}

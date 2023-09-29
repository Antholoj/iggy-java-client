package rs.iggy.http;

import rs.iggy.stream.StreamsClient;
import rs.iggy.system.SystemClient;
import rs.iggy.topic.TopicsClient;
import rs.iggy.user.UsersClient;

public class IggyHttpClient {

    private final SystemHttpClient systemClient;
    private final StreamsHttpClient streamsClient;
    private final UsersHttpClient usersClient;
    private final TopicsHttpClient topicsClient;

    public IggyHttpClient(String url) {
        HttpClient httpClient = new HttpClient(url);
        systemClient = new SystemHttpClient(httpClient);
        streamsClient = new StreamsHttpClient(httpClient);
        usersClient = new UsersHttpClient(httpClient);
        topicsClient = new TopicsHttpClient(httpClient);
    }

    public SystemClient system() {
        return systemClient;
    }

    public StreamsClient streams() {
        return streamsClient;
    }

    public UsersClient users() {
        return usersClient;
    }

    public TopicsClient topics() {
        return topicsClient;
    }

}

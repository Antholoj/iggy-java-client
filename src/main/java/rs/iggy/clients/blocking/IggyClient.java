package rs.iggy.clients.blocking;

public interface IggyClient {

    SystemClient system();

    StreamsClient streams();

    UsersClient users();

    TopicsClient topics();

    PartitionsClient partitions();

    ConsumerGroupsClient consumerGroups();

    ConsumerOffsetsClient consumerOffsets();

    MessagesClient messages();

}

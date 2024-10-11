package rs.iggy.clients.blocking.tcp;

import rs.iggy.clients.blocking.ConsumerOffsetsClient;
import rs.iggy.consumergroup.Consumer;
import rs.iggy.consumeroffset.ConsumerOffsetInfo;
import rs.iggy.identifier.StreamId;
import rs.iggy.identifier.TopicId;
import java.math.BigInteger;
import java.util.Optional;
import static rs.iggy.clients.blocking.tcp.BytesDeserializer.readConsumerOffsetInfo;
import static rs.iggy.clients.blocking.tcp.BytesSerializer.toBytes;
import static rs.iggy.clients.blocking.tcp.BytesSerializer.toBytesAsU64;

class ConsumerOffsetTcpClient implements ConsumerOffsetsClient {

    private static final int GET_CONSUMER_OFFSET_CODE = 120;
    private static final int STORE_CONSUMER_OFFSET_CODE = 121;

    private final TcpConnectionHandler connection;

    public ConsumerOffsetTcpClient(TcpConnectionHandler connection) {
        this.connection = connection;
    }

    @Override
    public void storeConsumerOffset(StreamId streamId, TopicId topicId, Optional<Long> partitionId, Consumer consumer, BigInteger offset) {
        var payload = toBytes(consumer);
        payload.writeBytes(toBytes(streamId));
        payload.writeBytes(toBytes(topicId));
        payload.writeIntLE(partitionId.orElse(0L).intValue());
        payload.writeBytes(toBytesAsU64(offset));

        connection.send(STORE_CONSUMER_OFFSET_CODE, payload);
    }

    @Override
    public ConsumerOffsetInfo getConsumerOffset(StreamId streamId, TopicId topicId, Optional<Long> partitionId, Consumer consumer) {
        var payload = toBytes(consumer);
        payload.writeBytes(toBytes(streamId));
        payload.writeBytes(toBytes(topicId));
        payload.writeIntLE(partitionId.orElse(0L).intValue());

        var response = connection.send(GET_CONSUMER_OFFSET_CODE, payload);

        return readConsumerOffsetInfo(response);
    }

}
package rs.iggy.consumergroup;

import rs.iggy.identifier.ConsumerId;

public record Consumer(Kind kind, ConsumerId id) {

    public static Consumer of(Long id) {
        return new Consumer(Kind.Consumer, ConsumerId.of(id));
    }

    public enum Kind {
        Consumer(1), ConsumerGroup(2);

        private final int code;

        Kind(int code) {
            this.code = code;
        }

        public int asCode() {
            return code;
        }

    }
}

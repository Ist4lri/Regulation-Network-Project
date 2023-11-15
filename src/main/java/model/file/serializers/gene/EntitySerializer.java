package model.file.serializers.gene;

public interface EntitySerializer<E> {
    String getCode();

    String serialize(E entity, RegulatoryNetworkWriter writer);

    E deserialize(String string, RegulatoryNetworkReader reader);
}

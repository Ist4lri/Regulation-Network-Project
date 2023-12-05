package model.file.serializers.event;

import model.events.SetSignaledEvent;
import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;

public class SetSignaledEventSerializer implements EntitySerializer<SetSignaledEvent> {

    private static SetSignaledEventSerializer instance = null;

    public SetSignaledEventSerializer() {
    };

    @Override
    public String getCode() {
        return "SetSignaledEvent";
    }

    @Override
    public String serialize(SetSignaledEvent entity, RegulatoryNetworkWriter writer) {
        return entity.getClass().getSimpleName() + " "
                + entity.getTime() + " "
                + new ListGeneSerializer().serialize(entity.getGenes(), writer) + " "
                + entity.description() + "\n";
    }

    @Override
    public SetSignaledEvent deserialize(String string, RegulatoryNetworkReader reader) {
        String[] toDispatch = string.split(" ");
        Double time = Double.parseDouble(toDispatch[1]);
        String newSignaledValue = toDispatch[3];

        if (time <= 0) {
            throw new IllegalArgumentException(
                    "Time can't be under or equals 0 for an event .");
        } else if (!newSignaledValue.equals("false") && !newSignaledValue.equals("true")) {
            throw new IllegalArgumentException(
                    "Is Signaled option must be 'false' or 'true' (case sensitive)");
        }

        return new SetSignaledEvent(new ListGeneSerializer().deserialize(toDispatch[2], reader),
                time,
                Boolean.parseBoolean(newSignaledValue));
    }

    public synchronized static SetSignaledEventSerializer getInstance() {
        if (instance == null) {
            instance = new SetSignaledEventSerializer();
        }
        return instance;
    }

}

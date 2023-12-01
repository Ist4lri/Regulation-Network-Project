package model.file.serializers.event;

import model.events.SetProteinConcentrationEvent;
import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;

public class SetProteinConcentrationEventSerializer implements EntitySerializer<SetProteinConcentrationEvent> {

    private static SetProteinConcentrationEventSerializer instance = null;

    public SetProteinConcentrationEventSerializer() {
    }

    @Override
    public String getCode() {
        return "SetProteinConcentrationEvent";
    }

    @Override
    public String serialize(SetProteinConcentrationEvent entity, RegulatoryNetworkWriter writer) {
        return entity.getClass().getSimpleName() + " "
                + entity.getTime() + " "
                + new ListGeneSerializer().serialize(entity.getGenes(), writer) + " "
                + entity.description() + "\n";
    }

    @Override
    public SetProteinConcentrationEvent deserialize(String string, RegulatoryNetworkReader reader) {
        String[] toDispatch = string.split(" ");
        return new SetProteinConcentrationEvent(new ListGeneSerializer().deserialize(toDispatch[1], reader),
                Double.parseDouble(toDispatch[0]),
                Double.parseDouble(toDispatch[2]));
    }

    public synchronized static SetProteinConcentrationEventSerializer getInstance() {
        if (instance == null) {
            instance = new SetProteinConcentrationEventSerializer();
        }
        return instance;
    }

}

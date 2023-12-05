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
        Double time = Double.parseDouble(toDispatch[1]);
        Double newProteinConcentration = Double.parseDouble(toDispatch[3]);

        if (time <= 0) {
            throw new IllegalArgumentException(
                    "Time can't be under or equals 0 for an event .");
        } else if (newProteinConcentration < 0) {
            throw new IllegalArgumentException(
                    "A New Concentration protein can't be negative.");
        }

        return new SetProteinConcentrationEvent(new ListGeneSerializer().deserialize(toDispatch[2], reader),
                time,
                newProteinConcentration);
    }

    public synchronized static SetProteinConcentrationEventSerializer getInstance() {
        if (instance == null) {
            instance = new SetProteinConcentrationEventSerializer();
        }
        return instance;
    }

}

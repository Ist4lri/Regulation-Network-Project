package model.file.serializers.event;

import model.events.SetProteinConcentrationEvent;
import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;

public class SetProteinConcentrationEventSerializer implements EntitySerializer<SetProteinConcentrationEvent> {

    @Override
    public String getCode() {
        return "SetProteinConcentrationEvent";
    }

    @Override
    public String serialize(SetProteinConcentrationEvent entity, RegulatoryNetworkWriter writer) {
        return entity.getAllInformation();
    }

    @Override
    public SetProteinConcentrationEvent deserialize(String string, RegulatoryNetworkReader reader) {
        String[] toDispatch = string.split(" ");
        return new SetProteinConcentrationEvent(new ListGeneSerializer().deserialize(toDispatch[2], reader),
                Double.parseDouble(toDispatch[1]),
                Double.parseDouble(toDispatch[3]));
    }

}

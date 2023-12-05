package model.file.serializers.list;

import java.util.ArrayList;
import java.util.List;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.serializers.regulators.BooleanActivatorSerializer;
import model.file.serializers.regulators.BooleanRepressorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.Regulator;

public class ListRegulatorSerializer implements EntitySerializer<List<Regulator>> {

    @Override
    public String getCode() {
        return "ListRegulator";
    }

    @Override
    public String serialize(List<Regulator> entity, RegulatoryNetworkWriter writer) {
        String toSend = "";
        for (Regulator regulator : entity) {
            toSend += regulator.description() + ",";
        }
        return "[" + toSend.substring(0, toSend.length() - 1) + "]";
    }

    @Override
    public List<Regulator> deserialize(String string, RegulatoryNetworkReader reader) {
        String stringOfRegulator = string.split("Regulator")[1].replace("]", "").replace(" [", "");
        List<Regulator> listOfRegulator = new ArrayList<>();
        String[] toDispatch = stringOfRegulator.split(",");
        for (String onePart : toDispatch) {
            String[] newDispatch = onePart.split(" ");

            if (newDispatch[0].equals("BooleanActivator")) {
                listOfRegulator.add(new BooleanActivatorSerializer().deserialize(onePart, reader));

            } else if (newDispatch[0].equals("BooleanRepressor")) {
                listOfRegulator.add(new BooleanRepressorSerializer().deserialize(onePart, reader));

            } else {
                throw new IllegalArgumentException(
                        "Unknown regulator type: " + newDispatch[0] + ". Need to define one");
            }
        }
        return listOfRegulator;
    }
}

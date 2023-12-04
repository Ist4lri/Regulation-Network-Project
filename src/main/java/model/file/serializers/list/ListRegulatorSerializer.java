package model.file.serializers.list;

import java.util.ArrayList;
import java.util.List;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;
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
            Regulator regulator;

            if (newDispatch[0].equals("BooleanActivator")) {
                regulator = new BooleanActivator(Double.parseDouble(newDispatch[1]), reader.getGene(newDispatch[2]));
            } else if (newDispatch[0].equals("BooleanRepressor")) {
                regulator = new BooleanRepressor(Double.parseDouble(newDispatch[1]), reader.getGene(newDispatch[2]));
            } else {
                throw new IllegalArgumentException("Unknown regulator type: " + newDispatch[0]);
            }
            listOfRegulator.add(regulator);
        }
        return listOfRegulator;
    }
}

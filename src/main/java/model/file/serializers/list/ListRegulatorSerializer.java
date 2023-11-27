package model.file.serializers.list;

import java.util.ArrayList;
import java.util.List;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.EntitySerializer;
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
            toSend += "+" + regulator.getClass().getSimpleName();
        }
        return "[" + toSend + "]";
    }

    @Override
    public List<Regulator> deserialize(String string, RegulatoryNetworkReader reader) {
        List<Regulator> listOfRegulator = new ArrayList<>();
        string.replace("[", "");
        string.replace("]", "");
        String[] toDispatch = string.split(",");
        for (String onePart : toDispatch) {
            String[] newDispatch = onePart.split(" ");
            listOfRegulator.add(reader.getRegulators(newDispatch[0]));
        }
        return listOfRegulator;

    }

}

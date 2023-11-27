package model.file.writer;

import model.events.SetSignaledEvent;
import model.events.SetProteinConcentrationEvent;

public interface EventVisitor {

    public String visit(SetSignaledEvent event);

    public String visit(SetProteinConcentrationEvent event);
}

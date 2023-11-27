package model.file.writer;

import java.io.BufferedWriter;
import java.io.IOException;

import model.events.SimulationEvent;
import model.genes.Gene;
import model.network.RegulatoryNetwork;

public class RegulatoryNetworkWriter {
    private GeneVisitor geneVisitor;
    private EventVisitor eventVisitor;

    public RegulatoryNetworkWriter() {
    };

    public void write(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
        this.writeConfiguration(bufferedWriter, regulatoryNetwork);
        this.writeGenes(bufferedWriter, regulatoryNetwork);
        this.writeEvents(bufferedWriter, regulatoryNetwork);
    }

    private void writeGenes(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork)
            throws IOException {
        for (Gene gene : regulatoryNetwork.getGenes()) {
            bufferedWriter.write(gene.accept(geneVisitor));
        }
    }

    private void writeConfiguration(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork)
            throws IOException {
        bufferedWriter.write("TimeStep" + regulatoryNetwork.getTimeStepLength());
        bufferedWriter.write("TimeUpperBound" + regulatoryNetwork.getTimeUpperBound());
    }

    private void writeEvents(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork)
            throws IOException {
        for (SimulationEvent event : regulatoryNetwork.getSimulationEvents()) {
            bufferedWriter.write(event.accept(eventVisitor));
        }
    }

}

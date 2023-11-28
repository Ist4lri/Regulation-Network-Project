package model.file.writer;

import java.io.BufferedWriter;
import java.io.IOException;

import model.events.SimulationEvent;
import model.genes.Gene;
import model.network.RegulatoryNetwork;
import model.regulators.Regulator;

public class RegulatoryNetworkWriter {
    private GeneVisitor geneVisitor;
    private EventVisitor eventVisitor;
    private RegulatorVisitor regulatorVisitor;

    public RegulatoryNetworkWriter() {
    };

    public void write(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
        this.writeConfiguration(bufferedWriter, regulatoryNetwork);
        this.writeGenes(bufferedWriter, regulatoryNetwork);
        this.writeRegulators(bufferedWriter, regulatoryNetwork);
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

    private void writeRegulators(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork)
            throws IOException {
        for (Gene gene : regulatoryNetwork.getGenes()) {
            if (gene.getRegulator() instanceof Regulator) {
                bufferedWriter.write(gene.getName());
                bufferedWriter.write((gene.getRegulator()).accept(regulatorVisitor));
            }
        }
    }
}

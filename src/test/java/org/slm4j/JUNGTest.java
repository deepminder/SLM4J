package org.slm4j;

import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by Mitchell on 8/9/2016.
 */
public class JUNGTest {
    private static DirectedOrderedSparseMultigraph<String, String> getSimpleMultigraph() {
        DirectedOrderedSparseMultigraph<String, String> multigraph = new DirectedOrderedSparseMultigraph<>();
        Stream.of("A", "B", "C", "D", "E", "F").forEachOrdered(multigraph::addVertex);

        multigraph.addEdge("A->B", "A", "B");
        multigraph.addEdge("B->C", "B", "C");
        multigraph.addEdge("C->A", "C", "A");

        multigraph.addEdge("A->D", "A", "D");

        multigraph.addEdge("D->E", "D", "E");
        multigraph.addEdge("E->F", "E", "F");
        multigraph.addEdge("F->D", "F", "D");

        return multigraph;
    }

    @Test
    public void jungToNetworkEquivalence() {
        DirectedOrderedSparseMultigraph<String, String> multigraph = getSimpleMultigraph();
        Network network = ModularityOptimizer.readJUNGGraph(multigraph, 1);

        Assert.assertEquals(network.getNNodes(), multigraph.getVertexCount(), "Vertex counts do not match!");
        Assert.assertEquals(network.getNEdges(), multigraph.getEdgeCount(), "Edge counts do not match!");
        Assert.assertEquals(network.getNEdgesPerNode(), multigraph.getVertices().stream().mapToInt(multigraph::getNeighborCount).toArray(), "The numbers of neighbors do not match!");
    }

    @Test
    public void networkModularityDetection() {
        DirectedOrderedSparseMultigraph<String, String> multigraph = getSimpleMultigraph();
        Network network = ModularityOptimizer.readJUNGGraph(multigraph, 1);

        double resolution = 1d / (2 * network.getTotalEdgeWeight() + network.getTotalEdgeWeightSelfLinks());
        VOSClusteringTechnique vosClusteringTechnique = new VOSClusteringTechnique(network, resolution);
        Random r = new Random(0);
        vosClusteringTechnique.runIteratedLouvainAlgorithmWithMultilevelRefinement(100, r);
        Assert.assertEquals(vosClusteringTechnique.getClustering().getNodesPerCluster(), new int[][]{{0, 1, 2}, {3, 4, 5}}, "Incorrect clustering!");
    }
}

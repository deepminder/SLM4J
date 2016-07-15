# SLM4J
Smart Local Moving (SLM) algorithm is an algorithm for community detection (or clustering) in large networks
***
The SLM algorithm maximizes a so-called modularity function. The algorithm has been successfully applied to networks with tens of millions of nodes and hundreds of millions of edges. The details of the algorithm are documented in a paper (Waltman & Van Eck, 2013).
The SLM algorithm has been implemented in the Modularity Optimizer, a simple command-line computer program written in Java. The Modularity Optimizer can be freely downloaded (last updated: August 31, 2015). The program can be run on any system with Java support. In addition to the SLM algorithm, the Modularity Optimizer also provides an implementation of the well-known Louvain algorithm for large-scale community detection developed by Blondel, Guillaume, Lambiotte, and Lefebvre (2008). An extension of the Louvain algorithm with a multilevel refinement procedure, as proposed by Rotta and Noack (2011), is implemented as well. All algorithms implemented in the Modularity Optimizer support the use of a resolution parameter to determine the granularity level at which communities are detected.
![Complex Network](http://www.ludowaltman.nl/slm/network.png)

###Running the Modularity Optimizer

To run the Modularity Optimizer, take the following steps:

1. Download the Modularity Optimizer JAR file (last updated: August 31, 2015). Also, check whether Java is available on your system.
2. In your command-line environment, make sure you find yourself in the folder in which you saved the Modularity Optimizer JAR file. On a Windows system, for instance, use the Command Prompt tool and move to the right folder using the cd command.
3. Launch the Modularity Optimizer using the following command:
java -jar ModularityOptimizer.jar
If you are working with a large network, you may need to allocate additional memory to the Modularity Optimizer. This may for instance be done as follows:
java -Xmx10000m -jar ModularityOptimizer.jar
4. The Modularity Optimizer will ask you to provide the name of an input file. The input file is a simple tab-delimited text file listing all pairs of nodes in a network that are connected by an edge. An example of an input file for Zachary's karate club network is available here. Notice that the numbering of nodes starts at 0. For each pair of nodes, the node with the lower index is listed first, followed by the node with the higher index. The lines in an input file are sorted based on the node indices (first based on the indices in the first column, then based on the indices in the second column). In the case of a weighted network, edge weights are provided in a third column.
5. The Modularity Optimizer will ask you to provide the name of an output file. The output file is a simple text file listing the community to which each of the nodes in your network has been assigned. An example of an output file for Zachary's karate club network is available here. Notice that the numbering of communities starts at 0.
6. The Modularity Optimizer will ask you to indicate whether you want to optimize the standard modularity function or an alternative modularity function. The standard modularity function has been proposed by Newman and Girvan (2004) and Newman (2004). The alternative modularity function has been proposed by Traag, Van Dooren, and Nesterov (2011).
7. The Modularity Optimizer will ask you to provide a value for the resolution parameter. The resolution parameter determines the granularity level at which communities are detected. Use a value of 1.0 for standard modularity-based community detection. Use a value above (below) 1.0 if you want to obtain a larger (smaller) number of communities.
8. The Modularity Optimizer will ask you to indicate the algorithm you want to use for modularity optimization (the original Louvain algorithm, the Louvain algorithm with multilevel refinement, or the SLM algorithm) and to provide values for three parameters related to the optimization: The number of random starts, the number of iterations per random start, and the seed of the random number generator. For more details, we refer to Waltman and Van Eck (2013).
9. Finally, the Modularity Optimizer will ask you to indicate whether or not you want the program to print output to the console. If you choose yes, the Modularity Optimizer will provide some information on the progress of the optimization and on the final community structure that is obtained.

###Using command-line arguments

The Modularity Optimizer can also be run using command-line arguments. The following syntax must be used:

java -jar ModularityOptimizer.jar input_file output_file modularity_function resolution_parameter optimization_algorithm n_random_starts n_iterations random_seed print_output
The command-line arguments are defined as follows:
input_file	Name of the input file
output_file	Name of the output file
modularity_function	Modularity function (1 = standard; 2 = alternative)
resolution_parameter	Value of the resolution parameter
optimization_algorithm	Algorithm for modularity optimization (1 = original Louvain algorithm; 2 = Louvain algorithm with multilevel refinement; 3 = SLM algorithm)
n_random_starts	Number of random starts
n_iterations	Number of iterations per random start
random_seed	Seed of the random number generator
print_output	Whether or not to print output to the console (0 = no; 1 = yes)
As an example, the Modularity Optimizer may be run as follows:

java -jar ModularityOptimizer.jar network.txt communities.txt 1 1.0 3 10 10 0 0
This will cause the Modularity Optimizer to read a network from the network.txt input file, to carry out standard modularity-based community detection (i.e., standard modularity function with resolution parameter equal to 1.0) by performing 10 runs of 10 iterations of the SLM algorithm, and to write the resulting community structure to the communities.txt output file. The random number generator will be initialized with a seed of 0, and no output will be printed to the console.

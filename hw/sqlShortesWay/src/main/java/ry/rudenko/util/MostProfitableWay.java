package ry.rudenko.util;

public class MostProfitableWay {

  private final Dijkstra dijkstra = new Dijkstra();

  public String mostProfitableWay(int start, int stop, int[][] inputMatrix) {
    StringBuilder retOutput = new StringBuilder();
    int step = inputMatrix.length - (stop - start + 1);
    int[][] adjMatrix = new int[stop - start + 1][stop - start + 1];
    for (int i = 0; i <= stop - start; i++) {
      if (stop - start + 1 >= 0) {
        System
            .arraycopy(inputMatrix[start - 1 + i], step, adjMatrix[i], 0, stop - start + 1);
      }
    }
    int[] result = dijkstra.getShortestPaths(adjMatrix);
    retOutput.append(result[result.length - 1]).append("\n");
    return retOutput.toString();
  }
}














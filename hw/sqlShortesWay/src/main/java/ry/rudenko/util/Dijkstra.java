package ry.rudenko.util;


public class Dijkstra {

  public int[] getShortestPaths(int[][] adjMatrix) {
    int[] result = new int[adjMatrix.length];
    boolean[] used = new boolean[adjMatrix.length];
    used[0] = true;
    for (int i = 1; i < adjMatrix.length; i++) {
      result[i] = adjMatrix[0][i];
      used[i] = false;
    }
    for (int i = 1; i < adjMatrix.length; i++) {
      int min = Integer.MAX_VALUE;
      int k = 0;
      for (int j = 1; j < adjMatrix.length; j++) {
        if (!used[j] && result[j] != -1 && min > result[j]) {
          min = result[j];
          k = j;
        }
      }
      used[k] = true;
      for (int j = 1; j < adjMatrix.length; j++) {
        if (!used[j]) {
          if (adjMatrix[k][j] != -1 && (result[j] > min + adjMatrix[k][j] || result[j] == -1)) {
            result[j] = min + adjMatrix[k][j];
          }
        }
      }
    }
    return result;
  }
}

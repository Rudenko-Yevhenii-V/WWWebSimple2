package ry.rudenko.util;

import java.util.HashMap;
import java.util.Map;

public class MostProfitableWay {

  private final Dijkstra dijkstra = new Dijkstra();

  public String mostProfitableWay(int start, int stop,  int[][] inputMatrix ) {
    StringBuilder retOutput = new StringBuilder();
    System.out.println();
    System.out.println("start = " + start);
    System.out.println("stop = " + stop);
    for (int[] matrix : inputMatrix) {
      System.out.println();
      for (int i : matrix) {
        System.out.print(i);
      }
    }


//
//
//
//
//
//
//
//
//        int step = inputMatrix.length - (stop - start + 1);
//        int[][] adjMatrix = new int[stop - start + 1][stop - start + 1];
//        for (int i = 0; i <= stop - start; i++) {
//          if (stop - start + 1 >= 0) {
//            System
//                .arraycopy(inputMatrix[start - 1 + i], step, adjMatrix[i], 0, stop - start + 1);
//          }
//        }
//        //dsfgdfgsd
////        sdfgsdfg
////            sdfg
//        int[] result = dijkstra.getShortestPaths(adjMatrix);
//        retOutput.append(result[result.length - 1]).append("\n");
//        continue;
//      }
//      if (s.matches("[0-9]+\\s[0-9]+")) {
//        final String[] s1 = s.split(" ");
//        if (Integer.parseInt(s1[1].replaceAll("[^0-9]", "")) > 200000) {
//          s1[1] = "200000";
//        }
//        inputMatrix[inputMatrixCount][Integer.parseInt(s1[0].replaceAll("[^0-9]", ""))
//            - 1] = Integer.parseInt(s1[1].replaceAll("[^0-9]", ""));
//      }
//    }
    return retOutput.toString();
  }
}














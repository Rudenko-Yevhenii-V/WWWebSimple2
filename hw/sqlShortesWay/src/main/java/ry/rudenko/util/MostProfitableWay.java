package ry.rudenko.util;

import java.util.HashMap;
import java.util.Map;

public class MostProfitableWay {

  private final Dijkstra dijkstra = new Dijkstra();

  public String mostProfitableWay(String inputCity) {
    StringBuilder retOutput = new StringBuilder();
    Map<String, Integer> cityes = new HashMap<>();
    final String[] split = inputCity.split("\n");
    split[0] = split[0].replaceAll("\n", "");
    int valueCityes = Integer.parseInt(split[0].replaceAll("[^0-9]", ""));
    int[][] inputMatrix = new int[valueCityes][valueCityes];
    for (int i = 0; i < inputMatrix.length; i++) {
      for (int i1 = 0; i1 < inputMatrix[i].length; i1++) {
        if (i == i1) {
          inputMatrix[i][i1] = 0;
          continue;
        }
        inputMatrix[i][i1] = -1;
      }
    }
    int inputMatrixCount = -1;
    int countCityes = 1;
    for (String s : split) {
      if (String.valueOf((s.charAt(s.length() - 1))).matches("\\s") || String
          .valueOf((s.charAt(s.length() - 1))).matches("\n")) {
        s = s.substring(0, s.length() - 1);
      }
      if (s.matches("[^0-9]*") && !(s.matches("[^0-9]+\\s[^0-9]+"))) {
        s = s.replaceAll("\n", "");
        s = s.replaceAll("\\s", "");
        cityes.put(s, countCityes);
        countCityes++;
        inputMatrixCount++;
        continue;
      }
      if (s.matches("[^0-9]+\\s[^0-9]+")) {
        final String[] split1 = s.split(" ");
        for (int i = 0; i < split1.length; i++) {
          split1[i] = split1[i].replaceAll("\n", "");
          split1[i] = split1[i].replaceAll("\\s", "");
        }
        int start = cityes.get(split1[0]);
        int stop = cityes.get(split1[1]);
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
        continue;
      }
      if (s.matches("[0-9]+\\s[0-9]+")) {
        final String[] s1 = s.split(" ");
        if (Integer.parseInt(s1[1].replaceAll("[^0-9]", "")) > 200000) {
          s1[1] = "200000";
        }
        inputMatrix[inputMatrixCount][Integer.parseInt(s1[0].replaceAll("[^0-9]", ""))
            - 1] = Integer.parseInt(s1[1].replaceAll("[^0-9]", ""));
      }
    }
    return retOutput.toString();
  }
}














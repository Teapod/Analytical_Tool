package tea.pod.analTool;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Analyser {
   public static List<String> analyse(Deque<String> inputList) {
        List<String> listToOutput = new ArrayList<>();

        String countOfAllLines = inputList.removeFirst();

        try {
            int numberOfAllLines = Integer.parseInt(countOfAllLines);

            List<String[]> listWithWaitingTimeLines = new ArrayList<>();

            for (int i = 0; i < numberOfAllLines; i++) {
                String[] lineToAnalyse = inputList.removeFirst().split(" ");

                if (lineToAnalyse[0].equals("C")) {
                    listWithWaitingTimeLines.add(lineToAnalyse);
                } else if (lineToAnalyse[0].equals("D")) {
                    listToOutput.add(queryAnalyser(listWithWaitingTimeLines, lineToAnalyse));
                }
            }
            // Exceptions need to handle in proper way ( according to requirements )
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listToOutput;
    }

    private static String queryAnalyser(List<String[]> listToAnalyze, String[] queryLine) throws ParseException {
        String noAnswerToReturn = "-";
        
        List<Integer> waitingTimeInMinutesList = new ArrayList<>();

        for (String[] strings : listToAnalyze) {
            int analysedTime = lineAnalyser(strings, queryLine);
            if (analysedTime > -1) {
                waitingTimeInMinutesList.add(analysedTime);
            }
        }

        if (!waitingTimeInMinutesList.isEmpty()) {
            int sumWaitingTimeInMinutes = waitingTimeInMinutesList.stream().mapToInt(Integer::intValue).sum();
            double avgWaitingTimeInMinutes = sumWaitingTimeInMinutes / waitingTimeInMinutesList.size() * 1.0;

            return Math.round(avgWaitingTimeInMinutes) + "";
        }

        return noAnswerToReturn;

    }

    private static int lineAnalyser(String[] lineToAnalyse, String[] queryLine) throws ParseException {

        if (lineToAnalyse[1].startsWith(queryLine[1]) || queryLine[1].equals("*")) {
            if (lineToAnalyse[2].startsWith(queryLine[2]) || queryLine[2].equals("*")) {
                if (lineToAnalyse[3].startsWith(queryLine[3])) {

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    String[] beginEndDates = queryLine[4].split("-");

                    Date fromDay = sdf.parse(beginEndDates[0]);
                    Date toDay = new Date();
                    if (beginEndDates.length > 1) {
                        toDay = sdf.parse(beginEndDates[1]);
                    }
                    Date dayToAnalyse = sdf.parse(lineToAnalyse[4]);

                    if (dayToAnalyse.after(fromDay) && dayToAnalyse.before(toDay)) {
                        return parseInt(lineToAnalyse[5]);
                    }
                }
            }
        }
        return -1;
    }
}

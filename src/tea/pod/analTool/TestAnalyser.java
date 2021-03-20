package tea.pod.analTool;

import java.util.*;

public class TestAnalyser {
    public static void main(String[] args) {
        Deque<String> inputList = new LinkedList<>(Arrays.asList(
                "12",
                "C 1.1 8.15.1 P 15.10.2012 83",
                "C 4.1.2 5.3 N 01.10.2013 50",
                "C 1 10.1 P 01.12.2012 65",
                "C 1.1 5.5.1 P 01.11.2012 117",
                "D 1.1 8 P 01.01.2012-01.12.2012",
                "C 3 10.2 N 02.10.2012 100",
                "C 4 5 N 01.10.2014 100",
                "D 1 * P 08.10.2012-20.11.2012",
                "D 3 10 P 01.12.2012",
                "D * 5 N 01.01.2012-01.01.2015",
                "D * * P 01.01.2012-01.01.2015",
                "D * * N 01.01.2012-01.01.2015"));
        List<String> out = Analyser.analyse(inputList);
        System.out.println(out);
    }
}

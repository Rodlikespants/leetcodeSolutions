package interviews.mock;
import java.io.*;
import java.util.*;

public class CompilerDependencies {

    /*
     * Click `Run` to execute the snippet below!
     */


    /*
     * To execute Java, please define "static void main" on a class
     * named Solution.
     *
     * If you need more classes, simply define them inline.
     */
    interface File {
        String name();
        List<String> includes();
        int compileTime();
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }
      /*
      "home/chris/foo.txt"
      {"work/files/foo2.cpp", "scratch/temp/foo3.java", "foo4.java", "foo5.java"}

A(1) -> B(1) -> C(1)

D(100)

E(5) -> F(10)
G(1) -> H(13)

--------------


mainapp.cc
#include "lib1.h"
#include "lib2.h"
compileTime 5

lib1.h
#include "string.h"
compileTime 10

lib2.h
#include "string.h"
compileTime 7

string.h
compileTime 3

{
    "mainapp.cc" : ["lib1.h", "lib2.h"],
    "lib1.h" : ["string.h"],
    "lib2.h" : ["string.h"],
    "string.h" : []
}

string.h starts at 0, ends at 3
lib1.h starts at 3, ends at 13
lib2.h starts at 3, ends at 10
mainapp.cc starts at 13, ends at 18
final result: 18

package compile time = max finish time of all files
finish time of a file = file's compile time + max finish time of its includes

pseudocode:
Map<String, Integer> make a map of name to compile time
(Map<String, List<String>>) make an adjacency list of file names to includes

for each file name:
    get finish time, keep track of max

return max

to get the finish time of a file
memoization check

for each included file name:
    get finish time of included file name, keep track of max

result = file's compile time + max finish time of includes
return result

      */
        List<File> testFiles = new ArrayList<>();
        /*
        Solution solution = new Solution();
        int result = solution.processFiles(testFiles);
        */
    }


    private static Map<String, Integer> getFileCompileTimes(List<File> files) {
        Map<String, Integer> result = new HashMap<>();
        for (File file : files) {
            result.put(file.name(), file.compileTime());
        }

        return result;
    }

    private static Map<String, List<String>> getFilesToIncludes(List<File> files) {
        Map<String, List<String>> result = new HashMap<>();
        for (File file : files) {
            result.put(file.name(), file.includes());
        }

        return result;
    }

    private static int getFinishTime(String filename, Map<String, List<String>> filesToIncludes,
                                     Map<String, Integer> compileTimes, Map<String, Integer> finishTimes) {
        if (finishTimes.containsKey(filename)) {
            return finishTimes.get(filename);
        }

        int latestIncludeFinishTime = 0;
        for (String includeFilename : filesToIncludes.get(filename)) {
            latestIncludeFinishTime = Math.max(latestIncludeFinishTime,
                    getFinishTime(includeFilename, filesToIncludes, compileTimes, finishTimes));
        }

        int result = compileTimes.get(filename) + latestIncludeFinishTime;
        finishTimes.put(filename, result);
        return result;
    }

    /**
     * Given a list of all the files in a compilation unit, finds the total time to compile all of the files
     * assuming files can be compiled in parallel. Can stack overflow if the longest dependency chain exceeds
     * the stack limit.
     * @param files all of the files in this compilation unit. Must be a valid dependency graph with no cycles.
     * @return time taken to compile all of the files.
     */
    public static int totalCompileTimeRec(List<File> files) {
        Map<String, Integer> compileTimes = getFileCompileTimes(files);
        Map<String, List<String>> filesToIncludes = getFilesToIncludes(files);

        int latest = 0;
        Map<String, Integer> finishTimes = new HashMap<>();
        for (String filename : compileTimes.keySet()) {
            latest = Math.max(latest, getFinishTime(filename, filesToIncludes, compileTimes, finishTimes));
        }

        return latest;
    }


    private static Map<String, List<String>> reversedGraph(Map<String, List<String>> graph) {
        Map<String, List<String>> result = new HashMap<>();
        for (String key : graph.keySet()) {
            result.put(key, new ArrayList<>());
        }

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                result.get(value).add(key);
            }
        }

        return result;
    }

    private static Map<String, Integer> findInDegrees(Map<String, List<String>> graph) {
        Map<String, Integer> inDegrees = new HashMap<>();

        for (String key : graph.keySet()) {
            inDegrees.put(key, 0);
        }

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            for (String dep : entry.getValue()) {
                inDegrees.put(dep, 1 + inDegrees.get(dep));
            }
        }

        return inDegrees;
    }

    private static List<String> topologicalOrderBfs(Map<String, List<String>> graph) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> reversed = reversedGraph(graph);
        Map<String, Integer> inDegrees = findInDegrees(reversed);

        Queue<String> queue = new ArrayDeque<>();
        for (Map.Entry<String, Integer> entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            String filename = queue.poll();
            result.add(filename);

            for (String nextFilename : reversed.get(filename)) {
                int newDegree = inDegrees.get(nextFilename) - 1;
                inDegrees.put(nextFilename, newDegree);

                if (newDegree == 0) {
                    queue.add(nextFilename);
                }
            }
        }
        return result;
    }

    /**
     * Given a list of all the files in a compilation unit, finds the total time to compile all of the files
     * assuming files can be compiled in parallel.
     * @param files all of the files in this compilation unit. Must be a valid dependency graph with no cycles.
     * @return time taken to compile all of the files.
     */
    public static int totalCompileTime(List<File> files) {
        Map<String, Integer> fileCompileTimes = getFileCompileTimes(files);
        Map<String, List<String>> filesToIncludes = getFilesToIncludes(files);
        List<String> sortedFiles = topologicalOrderBfs(filesToIncludes);

        Map<String, Integer> finishTimes = new HashMap<>();
        for (String filename : sortedFiles) {
            int latestFinishTime = 0;
            for (String includedFile : filesToIncludes.get(filename)) {
                latestFinishTime = Math.max(latestFinishTime, finishTimes.get(includedFile));
            }

            int finishTime = fileCompileTimes.get(filename) + latestFinishTime;
            finishTimes.put(filename, finishTime);
        }

        return Collections.max(finishTimes.values());
    }

    private static List<String> topologicalOrderBfsAlternate(Map<String, List<String>> graph) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> inDegrees = findInDegrees(graph);

        Queue<String> queue = new ArrayDeque<>();
        for (Map.Entry<String, Integer> entry : inDegrees.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            String filename = queue.poll();
            result.add(filename);

            for (String nextFilename : graph.get(filename)) {
                int newDegree = inDegrees.get(nextFilename) - 1;
                inDegrees.put(nextFilename, newDegree);

                if (newDegree == 0) {
                    queue.add(nextFilename);
                }
            }
        }
        Collections.reverse(result);
        return result;
    }
    public int processFiles(List<File> files) {

        return 0;
    }


/*
Your previous Plain Text content is preserved below:

Welcome to your interviewing.io interview.

Once you and your partner have joined, a voice call will start automatically.

Use the language dropdown near the top right to select the language you would like to use.

You can run code by hitting the 'Run' button near the top left.

Enjoy your interview!
 */
}

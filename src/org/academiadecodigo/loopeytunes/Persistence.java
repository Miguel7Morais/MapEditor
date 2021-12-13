package org.academiadecodigo.loopeytunes;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import java.io.*;
import java.util.Map;

public class Persistence {
    private BufferedWriter bWriter;
    private BufferedReader bReader;

    private void newFile() {
        try {
            File file = new File("CurrentState.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private String readFile() throws IOException {
        try {
            bReader = new BufferedReader(new FileReader("CurrentState.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String line;
        StringBuilder result = new StringBuilder();

        while ((line = bReader.readLine()) != null) {
            result.append(line).append("\n");
        }

        try {
            bReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result.toString();

    }

    public void save(Map<String, Rectangle> marked, Map<Color, Integer> colorToInt) {
        newFile();

        try {
            bWriter = new BufferedWriter(new FileWriter("CurrentState.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (String cord : marked.keySet()) {
                bWriter.write(cord + "|" + colorToInt.get(marked.get(cord).getColor()) + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            bWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String load() {
        newFile();

        String stringToLoad = "";
        try {
            stringToLoad = readFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return stringToLoad;
    }

}

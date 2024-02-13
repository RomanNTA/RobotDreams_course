package cz.robodreams.javadeveloper.project.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class Loader implements ILoader {

    private ILoaderCallback recipient;

    private String source;

    public Loader(ILoaderCallback recipient, String source) {
        this.recipient = recipient;
        this.source = source;
    }

    public void loadData() {

        if (recipient == null) {
            throw new AssertionError("Loader: Chybí přiřazení calleru.");
        }

        String[] typeOfColumns = new String[0];
        String[] nameOfColumns = new String[0];
        String line;
        int counter = 0;

        ClassLoader viaCl = Thread.currentThread().getContextClassLoader();
        InputStream is = viaCl.getSystemResourceAsStream(source);
        try (
                InputStreamReader sr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader buffer = new BufferedReader(sr)) {

            while ((line = buffer.readLine()) != null) {

                String[] output = line.split(Pattern.quote("|"));

                if (counter >= 3) {
                    recipient.addRows(output);

                } else if (counter == 0) {
                    // načtení jmen sloupců
                    nameOfColumns = output.clone();

                } else if (counter == 1) {
                    // načtení typu sloupců
                    typeOfColumns = output.clone();

                } else if (counter == 2) {
                    // načtení "navíc"
                    recipient.createColumns(nameOfColumns, typeOfColumns, output);
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

package sk.itsovy.Artem;

import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Connector - nested class. Used for storing a URL object and link to site with JSON.
 * Method jsonRead - method-helper used to read character stream. Returns string.
 * Method readJsonFromURL - suppose to build a string from byte streams to character streams. Returns filled jsonObject from selected link.
 * Method formListOfCountries - used to parse the resulting JSONObject and writing its values to the List of Country objects. Returns filled List.
 * Method userPredicates - creates and applies two predicates to formed list of Country objects with JSONObject values and for showing the result into the console.
 * Netherlands - class with imported predicate interface. Represents a simple function that takes a single value as parameter, and returns true or false.
 */

class Connector {

    private final URL url = new URL("http://itsovy.sk:5000/data");

    Connector() throws IOException {
    }

    private URLConnection openConnection() throws IOException {
        return url.openConnection();
    }

    public URLConnection getConnection() throws IOException {
        return openConnection();
    }
}

public class Main {

    private static String jsonRead(Reader reader) throws IOException {

        StringBuilder sb = new StringBuilder();
        int line;
        while ((line = reader.read()) != -1)
            sb.append((char) line);
        return sb.toString();

    }

    private static JSONObject readJsonFromURl() throws IOException {

        Connector connector = new Connector();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connector.getConnection().getInputStream()))) {

            String jsonText = jsonRead(br);
            return new JSONObject(jsonText);

        } finally {
            connector.getConnection().getInputStream().close();
        }

    }

    private static List<Country> formListOfCountries(JSONObject jsonObject) {

        List<Country> countryList = new ArrayList<>();
        JSONObject obj;
        for (int i = 0; i < jsonObject.getJSONArray("world_x").length(); i++) {
            obj = jsonObject.getJSONArray("world_x").getJSONObject(i);
            countryList.add(new Country(obj.getInt("pop"), obj.getString("code"), obj.getString("district"), obj.getString("name")));
        }
        return countryList;

    }

    private static void userPredicates(List<Country> countryList) {

        Predicate<Country> countryPredicateByPopulation = x -> x.getPopulation() >= 100000;
        Netherlands netherlandsPredicate = new Netherlands();

        System.out.println("Output with population predicate: ");
        countryList.stream().filter(countryPredicateByPopulation).forEach(System.out::println);

        System.out.println("");

        System.out.println("Output with population and country predicates: ");
        countryList.stream().filter(countryPredicateByPopulation).filter(netherlandsPredicate).forEach(System.out::println);

    }

    public static void main(String[] args) throws IOException {

        userPredicates(formListOfCountries(readJsonFromURl()));

    }
}

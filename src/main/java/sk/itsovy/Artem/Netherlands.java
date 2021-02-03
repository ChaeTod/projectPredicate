package sk.itsovy.Artem;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Netherlands implements Predicate<Country> {

    @Override
    public boolean test(Country country) {
        return Pattern.compile(".*Holland$").matcher(country.getDistrict()).find() || country.getCode().equals("NLD");
    }
}

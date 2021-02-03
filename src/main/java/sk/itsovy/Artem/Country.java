package sk.itsovy.Artem;

public class Country {

    private int population;
    private String code;
    private String district;
    private String name;

    public Country(int population, String code, String district, String name) {
        this.population = population;
        this.code = code;
        this.district = district;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "Country{" +
                "population=" + population +
                ", code='" + code + '\'' +
                ", district='" + district + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

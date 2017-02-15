public class City {
    private String name;
    private boolean isCapital;
    private int count;
    private int code;
    private Country country;


    public City(String name, boolean isCapital, int count, int code, Country country) {
        this.name = name;
        this.isCapital = isCapital;
        this.count = count;
        this.code = code;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean capital) {
        isCapital = capital;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "name=" + name +
                ", isCapital=" + isCapital +
                ", count=" + count +
                ", code=" + code +
                ", country=" + country +
                '}';
    }
}
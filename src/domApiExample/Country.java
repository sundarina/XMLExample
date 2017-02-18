package domApiExample;

public class Country {
    public int code; // Уникальный код страны
    public String name; // Название страны

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country(int code, String name) {

        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "domApiExample.Country{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
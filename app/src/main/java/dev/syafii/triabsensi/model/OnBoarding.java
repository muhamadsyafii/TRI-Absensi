package dev.syafii.triabsensi.model;

public class OnBoarding {
    String tittle;
    String description;
    int image;

    public OnBoarding(String tittle, String description, int image) {
        this.tittle = tittle;
        this.description = description;
        this.image = image;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

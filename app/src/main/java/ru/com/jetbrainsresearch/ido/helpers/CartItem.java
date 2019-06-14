package ru.com.jetbrainsresearch.ido.helpers;

public class CartItem {
    private Boolean checked;
    private String name;

    public CartItem() {
    }

    public CartItem(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

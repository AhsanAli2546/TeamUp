package com.projectmanager.teamup.Modal;

public class CardModal {
    String TVTitle;
    String Description;

    CardModal() {

    }

    public CardModal(String Description, String TVTitle) {
        this.TVTitle = TVTitle;
        this.Description = Description;
    }

    public String getTVTitle() {
        return TVTitle;
    }

    public void setTVTitle(String TVTitle) {
        this.TVTitle = TVTitle;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}

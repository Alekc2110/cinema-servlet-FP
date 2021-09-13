package com.my.cinema.booking.model.entity;


public class Hall extends Entity {

    private Long capacity;
    private String description;

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {

        private Long capacity;
        private String description;


        public Builder setCapacity(Long capacity) {
            this.capacity = capacity;

            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;

            return this;
        }


        public Hall build() {
            Hall hall = new Hall();
            hall.capacity = this.capacity;
            hall.description = this.description;
            return hall;
        }

    }

    @Override
    public String toString() {
        return "Hall{" +
                "capacity=" + capacity +
                ", description='" + description + '\'' +
                '}';
    }
}

package es.codeurjc.board.model;

public class PlantImage {

        private String name;
        private boolean first = false;

        public PlantImage(String name, boolean first) {
            this.name = name;
            this.first = first;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
}

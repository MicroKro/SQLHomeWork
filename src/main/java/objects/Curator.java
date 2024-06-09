package objects;

public class Curator {
    private long id;
    private String fio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Curator(String fio) {
        this.fio = fio;
    }

    public Curator(long id, String fio) {
        this.id = id;
        this.fio = fio;
    }

    @Override
    public String toString() {
        return "Curator{" +
                "id=" + id +
                ", fio='" + fio +
                '}';
    }
}

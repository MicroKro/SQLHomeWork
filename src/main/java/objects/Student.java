package objects;

public class Student {
    private long id;
    private String fio, sex;
    private int id_group;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId_group() {
        return id_group;
    }

    public void setId_group(int id_group) {
        this.id_group = id_group;
    }

    public Student(String fio, String sex, int id_group) {
        this.fio = fio;
        this.sex = sex;
        this.id_group = id_group;
    }

    public Student(long id, String fio, String sex, int id_group) {
        this.id = id;
        this.fio = fio;
        this.sex = sex;
        this.id_group = id_group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", sex='" + sex + '\'' +
                ", id_group='" + id_group + '\'' +
                '}';
    }
}


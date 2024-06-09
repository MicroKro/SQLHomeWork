import objects.*;
import tables.CuratorTable;
import tables.GroupTable;
import tables.StudentTable;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StudentTable studentTable = new StudentTable();

        if (!studentTable.isDataExist()) {
            studentTable.selectAll();
            studentTable.insert(new Student("Маша Иванова", "жен", 1));
            studentTable.insert(new Student("Петр Кузнецов", "муж", 1));
            studentTable.insert(new Student("Иван Самсонов", "муж", 1));
            studentTable.insert(new Student("Анна Савельева", "жен", 1));
            studentTable.insert(new Student("Наталья Зуборева", "жен", 1));
            studentTable.insert(new Student("Олег Иванов", "муж", 2));
            studentTable.insert(new Student("Инга Абрамова", "жен", 2));
            studentTable.insert(new Student("Мария Краснова", "жен", 2));
            studentTable.insert(new Student("Илья Петров", "муж", 2));
            studentTable.insert(new Student("Анна Виноградова", "жен", 2));
            studentTable.insert(new Student("Петр Васильев", "муж", 3));
            studentTable.insert(new Student("Ольга Кузнецова", "жен", 3));
            studentTable.insert(new Student("Наталья Купатова", "жен", 3));
            studentTable.insert(new Student("Игорь Звонарев", "муж", 3));
            studentTable.insert(new Student("Анна Кузнецова", "жен", 3));
        }

        GroupTable groupTable = new GroupTable();

        if (!groupTable.isDataExist()) {
            groupTable.insert(new Group("Первая", 1));
            groupTable.insert(new Group("Вторая", 2));
            groupTable.insert(new Group("Третья", 3));
        }

        CuratorTable curatorTable = new CuratorTable();
        if (!curatorTable.isDataExist()) {
            curatorTable.insert(new Curator("Антон Картушин"));
            curatorTable.insert(new Curator("Яна Телегина"));
            curatorTable.insert(new Curator("Павел Балахонов"));
            curatorTable.insert(new Curator("Александр Суворов"));
        }

        ArrayList<StudentSummary> allStudents = studentTable.innerAllData();
        for (StudentSummary allData : allStudents) {
            System.out.println(allData.toString());
        }

        System.out.println("_________________________________");

        ArrayList<Student> menStudents = studentTable.selectBySex("муж");
        for (Student male: menStudents) {
            System.out.println(male.toString());
        }

        System.out.println("_________________________________");

        ArrayList<Student> womenStudents = studentTable.selectBySex("жен");
        for (Student female: womenStudents) {
            System.out.println(female.toString());
        }

        System.out.println("_________________________________");

        Group group = groupTable.selectById(1);
        group.setId_curator(4);
        groupTable.update(group);

        System.out.println(groupTable.selectAll());

        System.out.println("_________________________________");

        ArrayList<Student> secondGroup = studentTable.attachedGroup();
        for (Student byGroup: secondGroup) {
            System.out.println(byGroup.toString());
        }
    }
}
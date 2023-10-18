package ra.bussiness;

import ra.entity.Student;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentBussiness {
    public static List<Student> readStudentsFromFile() {
        List<Student> listStudent = null;
        File file = new File("listStudent.txt");
        if (file.exists()) {
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                listStudent = (List<Student>) ois.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return listStudent;
    }

    public static void writeStudentsToFile(List<Student> listStudents) {
        File file = new File("listStudent.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(listStudents);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void inputListStudent(Scanner scanner, List<Student> listStudents) {
        System.out.println("Nhâp vào số sinh viên cần nhập dữ liệu:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            Student student = new Student();
            student.inputData(scanner, listStudents);
            listStudents.add(student);
        }
    }

    public static void calAgeStudent(List<Student> listStudent) {
        for (Student student : listStudent) {
            student.calAge();
        }
    }

    public static void calAvgMark_Rank(List<Student> listStudent) {
        for (Student student : listStudent) {
            student.calAvgMark_Rank();
        }
    }

    public static final String BLUE_BOLD = "\033[1;34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void displayListStudent(List<Student> listStudents) {
        printLine();
        System.out.printf("|%-15s|%-30s|%-12s|%-6s|%-10s|%-12s|%-12s|%-12s|%-12s|%-15s|\n",
                BLUE_BOLD + "Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Tuổi", "Giới tính", "Html",
                "Css", "Javascript", "Điểm TB", "Xếp loại" + ANSI_RESET);
        printLine();
        for (Student student : listStudents) {
            student.displayData();
            printLine();
        }
    }

    public static void printLine() {
        for (int i = 0; i < 147; i++) {
            System.out.print("-");
        }
        System.out.printf("\n");
    }

    public static void sortStudentByAgeASC(List<Student> listStudents) {
        Collections.sort(listStudents, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
    }

    public static void reportStudent(List<Student> listStudents) {
        int cntWeak = 0;
        int cntMedium = 0;
        int cntRather = 0;
        int cntGood = 0;
        int cntExcellent = 0;
        for (Student student : listStudents) {
            switch (student.getRank()) {
                case "Xuất sắc":
                    cntExcellent++;
                    break;
                case "Giỏi":
                    cntGood++;
                    break;
                case "Khá":
                    cntRather++;
                    break;
                case "Trung bình":
                    cntMedium++;
                    break;
                case "Yếu":
                    cntWeak++;
                    break;
            }
        }
        //In ra kết quả
        printLineReport();
        System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|\n", "Xuất sắc", "Giỏi", "Khá", "Trung bình", "Yếu");
        printLineReport();
        System.out.printf("|%-15d|%-15d|%-15d|%-15d|%-15d|\n", cntExcellent, cntGood, cntRather, cntMedium, cntWeak);
        printLineReport();
    }

    public static void printLineReport() {
        for (int i = 0; i < 81; i++) {
            System.out.print("-");
        }
        System.out.printf("\n");
    }

    public static void updateStudent(Scanner scanner, List<Student> listStudents) {

    }

    public static void searchStudentByName(Scanner scanner, List<Student> listStudent) {
        System.out.println("Nhập vào tên sinh viên:");
        String studentName = scanner.nextLine();
        int cntStudent = 0;
        printLine();
        System.out.printf("|%-15s|%-30s|%-12s|%-6s|%-10s|%-12s|%-12s|%-12s|%-12s|%-15s|\n",
                "Mã sinh viên", "Tên sinh viên", "Ngày sinh", "Tuổi", "Giới tính", "Html",
                "Css", "Javascript", "Điểm TB", "Xếp loại");
        printLine();
        for (Student student : listStudent) {
            if (student.getStudentName().toLowerCase().contains(studentName.toLowerCase())) {
                student.displayData();
                cntStudent++;
                printLine();
            }
        }
        if (cntStudent == 0) {
            System.out.println("Không có kết quả");
        }
    }

}

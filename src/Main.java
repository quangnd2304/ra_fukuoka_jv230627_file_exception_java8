import ra.bussiness.StudentBussiness;
import ra.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Student> listStudents = new ArrayList<>();
    public static void main(String[] args) {
        //Đọc dữ liệu từ file và đổ về listStudents để xử lý
        List<Student> listStudentRead = StudentBussiness.readStudentsFromFile();
        listStudents = (listStudentRead!=null)?listStudentRead:new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************MENU******************");
            System.out.println("1. Nhập thông tin các sinh viên");
            System.out.println("2. Tính tuổi các sinh viên");
            System.out.println("3. Tính điểm trung bình và xếp loại sinh viên");
            System.out.println("4. Danh sách sinh viên");
            System.out.println("5. Sắp xếp sinh viên theo tuổi tăng dần");
            System.out.println("6. Thống kê sinh viên theo xếp loại sinh viên");
            System.out.println("7. Cập nhật sinh viên");
            System.out.println("8. Tìm kiếm sinh viên");
            System.out.println("9. Thoát");
            System.out.print("Sự lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    StudentBussiness.inputListStudent(scanner,listStudents);
                    break;
                case 2:
                    StudentBussiness.calAgeStudent(listStudents);
                    break;
                case 3:
                    StudentBussiness.calAvgMark_Rank(listStudents);
                    break;
                case 4:
                    StudentBussiness.displayListStudent(listStudents);
                    break;
                case 5:
                    StudentBussiness.sortStudentByAgeASC(listStudents);
                    break;
                case 6:
                    StudentBussiness.reportStudent(listStudents);
                    break;
                case 7:
                    break;
                case 8:
                    StudentBussiness.searchStudentByName(scanner,listStudents);
                    break;
                case 9:
                    //Ghi dữ liệu ra file
                    StudentBussiness.writeStudentsToFile(listStudents);
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-9");
            }
        }while (true);
    }
}
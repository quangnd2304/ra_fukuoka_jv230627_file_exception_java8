package ra.entity;

import config.Message;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Student implements IEntity<Student>, Serializable {
    private String studentId;
    private String studentName;
    private Date birthDay;
    private int age;
    private boolean sex;
    private float mark_html;
    private float mark_css;
    private float mark_javascript;
    private float avgMark;
    private String rank;

    public Student() {
    }

    public Student(String studentId, String studentName, Date birthDay, int age, boolean sex, float mark_html, float mark_css, float mark_javascript, float avgMark, String rank) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthDay = birthDay;
        this.age = age;
        this.sex = sex;
        this.mark_html = mark_html;
        this.mark_css = mark_css;
        this.mark_javascript = mark_javascript;
        this.avgMark = avgMark;
        this.rank = rank;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public float getMark_html() {
        return mark_html;
    }

    public void setMark_html(float mark_html) {
        this.mark_html = mark_html;
    }

    public float getMark_css() {
        return mark_css;
    }

    public void setMark_css(float mark_css) {
        this.mark_css = mark_css;
    }

    public float getMark_javascript() {
        return mark_javascript;
    }

    public void setMark_javascript(float mark_javascript) {
        this.mark_javascript = mark_javascript;
    }

    public float getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(float avgMark) {
        this.avgMark = avgMark;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }


    @Override
    public void inputData(Scanner scanner, List<Student> list) {
        boolean isExit = true;
        System.out.println("Nhập vào mã sinh viên:");
        do {
            this.studentId = scanner.nextLine();
            if (this.studentId != "" && this.studentId.length() != 0) {
                if (this.studentId.length() == 4) {
                    if (this.studentId.startsWith("S")) {
                        boolean isExist = false;
                        for (ra.entity.Student student : list) {
                            if (student.getStudentId().equals(this.studentId)) {
                                isExist = true;
                                break;
                            }
                        }
                        if (isExist) {
                            System.err.println("Mã sinh viên đã tồn tại, vui lòng nhập lại");
                        } else {
                            break;
                        }
                    } else {
                        System.err.println("Mã sinh viên phải bắt đầu là ký tự S, vui lòng nhập lại");
                    }
                } else {
                    System.err.println("Mã sinh viên phải gồm 4 ký tự, vui lòng nhập lại");
                }
            } else {
                System.err.println("Mã sinh viên không được để trống, vui lòng nhập lại");
            }
        } while (isExit);
        System.out.println("Nhập vào tên sinh viên:");
        do {
            this.studentName = scanner.nextLine();
            if (this.studentName != "" && this.studentName.length() >= 10 && this.studentName.length() <= 50) {
                break;
            } else {
                System.err.println("Tên sinh viên phải có độ dài từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
        System.out.println("Nhập vào ngày sinh của sinh viên:");
        do {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                this.birthDay = sdf.parse(scanner.nextLine());
                if ((this.birthDay.getYear() + 1900) < 2005) {
                    break;
                } else {
                    System.err.println("Năm sinh của sinh viên phải trước năm 2005, vui lòng nhập lại");
                }

            } catch (Exception ex) {
                System.err.println("Ngày sinh phải có định dạng dd-MM-yyyy, vui lòng nhập lại");
            }
        } while (isExit);
        System.out.println("Nhập vào giới tính của sinh viên:");
        do {
            String gender = scanner.nextLine();
            if (gender.equals("true") || gender.equals("false")) {
                this.sex = Boolean.parseBoolean(gender);
                break;
            } else {
                System.err.println(Message.ERR_SEX);
            }
        } while (isExit);
        this.mark_html = validateMark(scanner, "html");
        this.mark_css = validateMark(scanner, "css");
        this.mark_javascript = validateMark(scanner, "javascript");
    }

    public float validateMark(Scanner scanner, String str) {
        System.out.println("Nhập vào điểm " + str + ":");
        do {
            String markStr = scanner.nextLine();
            try {
                float mark = Float.parseFloat(markStr);
                if (mark >= 0 && mark <= 10) {
                    return mark;
                } else {
                    System.err.println("Điểm phải có giá trị trong khoảng 0-10, vui lòng nhập lại");
                }
            } catch (Exception ex) {
                System.err.println("Điểm phải có kiểu dữ liệu là số thực, vui lòng nhập lại");
            }
        } while (true);
    }

    @Override
    public void displayData() {
        //In danh sách sinh viên
        System.out.printf("|%-15s|%-30s|%-12s|%-6d|%-10s|%-12.1f|%-12.1f|%-12.1f|%-12.1f|%-15s|\n",
                this.studentId, this.studentName,new SimpleDateFormat("dd/MM/yyyy").format(this.birthDay), this.age,
                this.sex ? "Nam" : "Nữ", this.mark_html, this.mark_css, this.mark_javascript, this.avgMark, this.rank);

    }

    @Override
    public void calAge() {
        this.age = new Date().getYear() - this.birthDay.getYear();
    }

    @Override
    public void calAvgMark_Rank() {
        this.avgMark = (this.mark_html + this.mark_css + this.mark_javascript) / 3;
        if (this.avgMark < 5) {
            this.rank = "Yếu";
        } else if (this.avgMark < 7) {
            this.rank = "Trung bình";
        } else if (this.avgMark < 8) {
            this.rank = "Khá";
        } else if (this.avgMark < 9) {
            this.rank = "Giỏi";
        } else {
            this.rank = "Xuất sắc";
        }
    }
}

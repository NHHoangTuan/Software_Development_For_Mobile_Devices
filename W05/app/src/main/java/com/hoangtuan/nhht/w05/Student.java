package com.hoangtuan.nhht.w05;

import java.text.DecimalFormat;
import java.util.Random;

public class Student {
    private String id;
    private String fullName;
    private String className;

    private double avg;

    public Student(String id, String fullName, String className, double avg){
        this.id = id;
        this.fullName = fullName;
        this.className = className;
        this.avg = avg;
    }

    public static Student randomStudent() {
        Random random = new Random();
        String id = generateRandomId();
        String fullName = generateRandomFullName();
        String className = generateClassName(id);
        double avg = random.nextDouble() * 10; // Điểm trung bình từ 0 đến 10
        DecimalFormat df = new DecimalFormat("#.##");
        avg = Double.parseDouble(df.format(avg));

        return new Student(id, fullName, className, avg);
    }

    private static String generateRandomId() {
        Random random = new Random();
        String[] prefixes = {"18", "19", "20", "21"};
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String randomDigits = String.valueOf(10000000 + random.nextInt(90000000));
        return prefix + randomDigits.substring(0, 6);
    }

    private static String generateRandomFullName() {
        String[] firstNameList = {
                "Nguyễn", "Trần", "Lê", "Phạm", "Huỳnh", "Hà", "Võ", "Đinh",
                "Bùi", "Vũ", "Phan", "Đặng", "Hồ", "Nông"
        };

        String[] MaleNameList = {
                "An", "Bình", "Cường", "Dũng", "Hưng", "Khoa", "Linh", "Nam",
                "Quang", "Sơn", "Thanh", "Trung", "Việt", "Xuân", "Thành"
        };

        String[] middleNameList = {
                "Văn", "Thị", "Thu", "Quốc", "Xuân", "Anh", "Hoàng"
        };

        String[] FemaleNameList = {
                "Anh", "Cẩm", "Dung", "Hạnh", "Lan", "Mai", "Nga",
                "Phương", "Quyên", "Thảo", "Trang", "Uyên"
        };

        Random random = new Random();


        String firstName = firstNameList[random.nextInt(firstNameList.length)];
        String middleName = middleNameList[random.nextInt(middleNameList.length)];
        String lastName;
        if (random.nextBoolean()) {
            lastName = MaleNameList[random.nextInt(MaleNameList.length)];
        } else {
            lastName = FemaleNameList[random.nextInt(FemaleNameList.length)];
        }
        return firstName+" "+middleName+" "+lastName;

    }

    private static String generateClassName(String id) {
        Random random = new Random();
        String prefix = id.substring(0, 2);
        String[] classSuffixes = {"CTT", "CNS"};
        String[] postfixClass = {"1", "2", "3", "4", "5"};
        String suffix = classSuffixes[random.nextInt(classSuffixes.length)];
        String postfix = postfixClass[random.nextInt(postfixClass.length)];
        return prefix + suffix + postfix;
    }

    String getId(){
        return this.id;
    }

    String getFullName(){
        return this.fullName;
    }

    String getClassName(){
        return this.className;
    }

    double getAvg(){
        return this.avg;
    }

}

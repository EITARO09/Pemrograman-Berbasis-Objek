public class Teacher extends Person {
    private int numCourses = 0;
    private String[] courses = new String[10];

    public Teacher(String name, String address) {
        super(name, address);
    }

    public boolean addCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                return false; // sudah ada
            }
        }
        courses[numCourses++] = course;
        return true;
    }

    public boolean removeCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equalsIgnoreCase(course)) {
                for (int j = i; j < numCourses - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                numCourses--;
                return true;
            }
        }
        return false; // tidak ditemukan
    }

    public void printCourses() {
        System.out.println("Mata Kuliah Dosen:");
        for (int i = 0; i < numCourses; i++) {
            System.out.println("- " + courses[i]);
        }
    }

    @Override
    public String toString() {
        return "Teacher -> " + super.toString();
    }
}
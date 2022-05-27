import sofrecom.it.coursecatalogservice.courses.Course;

public class Programme {

    public static void main(String[] args) {

        Course course = new Course(1,"learning java","Dev",null);
        System.out.println(course.getTitle());

        course.signeCourse("title","data");
        Course.printCourse("");

        Course.Authentication.authenticate("user","user");

    }
}

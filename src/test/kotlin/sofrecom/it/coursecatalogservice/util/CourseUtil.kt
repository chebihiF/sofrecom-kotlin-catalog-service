package sofrecom.it.coursecatalogservice

import sofrecom.it.coursecatalogservice.courses.Course
import sofrecom.it.coursecatalogservice.courses.CourseDTO

fun initCourses() = listOf(
    Course(1,"learning Java", "Dev"),
    Course(2,"learning Spring", "Back-End"),
    Course(3,"learning Angular", "front"),
    Course(4,"learning flutter", "mobile")
)
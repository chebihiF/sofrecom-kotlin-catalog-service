package sofrecom.it.coursecatalogservice.courses

interface ICourseService {
    fun addCourse(course: CourseDTO):CourseDTO
}

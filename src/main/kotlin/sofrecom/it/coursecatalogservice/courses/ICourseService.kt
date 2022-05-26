package sofrecom.it.coursecatalogservice.courses

interface ICourseService {
    fun addCourse(course: CourseDTO):CourseDTO
    fun getCourses():List<CourseDTO>
    fun getCourseById(id: Int):CourseDTO
    fun putCourse(courseDTO: CourseDTO, courseId: Int):CourseDTO
    fun deleteCourse(id: Int) : CourseDTO
}

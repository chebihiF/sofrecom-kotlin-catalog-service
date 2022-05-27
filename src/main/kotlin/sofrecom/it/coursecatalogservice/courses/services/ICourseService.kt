package sofrecom.it.coursecatalogservice.courses.services

import sofrecom.it.coursecatalogservice.courses.CourseDTO
import sofrecom.it.coursecatalogservice.instructor.InstructorDTO

interface ICourseService {
    fun addCourse(courseDTO: CourseDTO): CourseDTO
    fun getCourses():List<CourseDTO>
    fun getCoursesByKeyword(keyword: String?):List<CourseDTO>
    fun getCourseById(id: Int): CourseDTO
    fun putCourse(courseDTO: CourseDTO, courseId: Int): CourseDTO
    fun deleteCourse(id: Int) : CourseDTO
    fun findCourseByInstructor(id: Int) : List<CourseDTO>
}

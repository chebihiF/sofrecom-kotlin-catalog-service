package sofrecom.it.coursecatalogservice.courses

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import sofrecom.it.coursecatalogservice.instructor.Instructor
import sofrecom.it.coursecatalogservice.instructor.InstructorDTO

interface CourseRepository : JpaRepository<Course,Int> {

    fun findCourseByTitleContaining(title: String) : List<Course>

    @Query("From Course c where c.title like %?1% or c.category like %?1%")
    fun findCoursesByKeyword(keyword: String) : List<Course>

    fun findCoursesByInstructor(instructor: Instructor) : List<Course>
}
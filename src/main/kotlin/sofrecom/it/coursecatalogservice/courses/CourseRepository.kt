package sofrecom.it.coursecatalogservice.courses

import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository : JpaRepository<Course,Int> {
}
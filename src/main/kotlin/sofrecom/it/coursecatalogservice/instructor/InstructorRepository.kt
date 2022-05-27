package sofrecom.it.coursecatalogservice.instructor

import org.springframework.data.jpa.repository.JpaRepository

interface InstructorRepository : JpaRepository<Instructor,Int> {
}
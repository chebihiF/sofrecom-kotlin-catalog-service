package sofrecom.it.coursecatalogservice.repository

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import sofrecom.it.coursecatalogservice.courses.Course
import sofrecom.it.coursecatalogservice.courses.CourseRepository
import sofrecom.it.coursecatalogservice.initCourses

@DataJpaTest
@ActiveProfiles("test")
class CourseRepositoryUnitTest {

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp(){
        courseRepository.deleteAll()
        courseRepository.saveAll(initCourses())
    }

    @Test
    fun findCourseByTitleContaining(){
        val courses = courseRepository.findCourseByTitleContaining("Angular")
        Assertions.assertEquals(2,courses.size)
    }

}
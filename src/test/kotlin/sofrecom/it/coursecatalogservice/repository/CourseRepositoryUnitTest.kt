package sofrecom.it.coursecatalogservice.repository

import net.bytebuddy.asm.Advice.Argument
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import sofrecom.it.coursecatalogservice.courses.Course
import sofrecom.it.coursecatalogservice.courses.CourseRepository
import sofrecom.it.coursecatalogservice.initCourses
import java.util.stream.Stream

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

    @Test
    fun findCoursesByKeyword(){
        val keyword = "advanced"
        val courses = courseRepository.findCoursesByKeyword(keyword)
        Assertions.assertEquals(2,courses.size)
    }

    @ParameterizedTest
    @MethodSource("keywordAndSize")
    fun findCoursesByKeywordParametrized(keyword: String, expectedSize: Int){
        val courses = courseRepository.findCoursesByKeyword(keyword)
        Assertions.assertEquals(expectedSize,courses.size)
    }

    companion object{
        @JvmStatic
        fun keywordAndSize(): Stream<Arguments> {
            return Stream.of(Arguments.arguments("advanced",2), Arguments.arguments("Dev",1))
        }
    }

}
package sofrecom.it.coursecatalogservice.intg

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import sofrecom.it.coursecatalogservice.courses.Course
import sofrecom.it.coursecatalogservice.courses.CourseDTO
import sofrecom.it.coursecatalogservice.courses.CourseRepository
import sofrecom.it.coursecatalogservice.initCourses

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseIntegrationTest {



    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var coursesRepository: CourseRepository;

    @BeforeEach
    fun initData()
    {
        coursesRepository.deleteAll()
        coursesRepository.saveAll(initCourses())
    }

    @Test
    fun addCourse(){
        val courseDTO = CourseDTO(null,"learning DevOps","software architect")
        val courseDTOResponse = webTestClient
            .post()
            .uri("/api/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        Assertions.assertNotNull(courseDTOResponse!!.id)
    }

    @Test
    fun addCourseException(){
        val courseDTO = CourseDTO(null,"","software architect")
        val errorResponse = webTestClient
            .post()
            .uri("/api/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("CourseDTO.title must be not blank",errorResponse)
    }


    @Test
    fun getCourses(){
        val courses = webTestClient
            .get()
            .uri("/api/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals(4,courses!!.size)
    }

    @Test
    fun getCourseById(){

        val id = 1
        val course = webTestClient
            .get()
            .uri("/api/v1/courses/{id}",id)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals(1,course?.id)
    }

    @Test
    fun updateCourse(){
        val id = 1
        val courseDTO = CourseDTO(1,"learning JAVAEE","DEV")
        val courseDTOResponse = webTestClient
            .put()
            .uri("/api/v1/courses/{id}",id)
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody
        Assertions.assertEquals("learning JAVAEE",courseDTOResponse?.title)
    }

    @Test
    fun deleteById(){

        val id = 1
        webTestClient
            .delete()
            .uri("/api/v1/courses/{id}",id)
            .exchange()
            .expectStatus().isOk

    }


}
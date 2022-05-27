package sofrecom.it.coursecatalogservice.unit

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.util.UriComponentsBuilder
import sofrecom.it.coursecatalogservice.courses.CourseController
import sofrecom.it.coursecatalogservice.courses.CourseDTO
import sofrecom.it.coursecatalogservice.courses.services.CourseService

@WebMvcTest(controllers =[CourseController::class])
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerUnitTest {

    @MockkBean
    lateinit var courseService: CourseService

    @Autowired
    lateinit var webTestClient: WebTestClient


    @Test
    fun getCoursesByKeyword(){
        val keyword: String = "advanced"
        every{
            courseService.getCoursesByKeyword(any())
        }returns listOf(
            CourseDTO(1,"learning Angular basics", "Dev"),
            CourseDTO(2,"learning Spring", "Back-End"),
            CourseDTO(3,"learning Angular advanced", "front"),
            CourseDTO(4,"learning flutter", "mobile advanced")
        )

        val uri = UriComponentsBuilder.fromUriString("/api/v1/courses")
            .queryParam("keyword",keyword)
            .toUriString()

        val responseBody = webTestClient
            .get()
            .uri(uri)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals(2, responseBody!!.size)
    }


    @Test
    fun getCourseById(){

        val id = 1 ;

        every{
            courseService.getCourseById(any())
        }.throws(RuntimeException("id cannot be found"))

        val responseBody = webTestClient
            .get()
            .uri("/api/v1/courses/{id}",id)
            .exchange()
            .expectStatus().is5xxServerError
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("id cannot be found", responseBody)

    }

}
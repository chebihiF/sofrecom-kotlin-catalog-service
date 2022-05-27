package sofrecom.it.coursecatalogservice.intg

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import sofrecom.it.coursecatalogservice.instructor.InstructorDTO
import sofrecom.it.coursecatalogservice.instructor.InstructorService

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class InstructorIntegrationTest {

    @Autowired
    lateinit var instructorService: InstructorService

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun addInstructor(){

        val instructorDTO = InstructorDTO(null, "Chebihi")
        val _instructorDTO = webTestClient
            .post()
            .uri("/api/v1/instructors")
            .bodyValue(instructorDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(InstructorDTO::class.java)
            .returnResult()
            .responseBody
        Assertions.assertNotNull(_instructorDTO!!.id)
    }
}
package sofrecom.it.coursecatalogservice.unit

import com.ninjasquad.springmockk.MockkBean
import com.ninjasquad.springmockk.MockkBeans
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.MockBeans
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import sofrecom.it.coursecatalogservice.controller.GreetingsController
import sofrecom.it.coursecatalogservice.service.GreetingsService

@WebMvcTest(controllers =[GreetingsController::class])
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class GreetingsControllerUnitTest {

    @MockkBean
    lateinit var greetingServiceMock: GreetingsService

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun retrieveGreetings(){
        val name = "chebihi"

        every {
            greetingServiceMock.retrieveGreetings(any())
        } returns "$name, Hello from Kotlin (test)"


        val responseBody = webTestClient
            .get()
            .uri("/api/v1/greetings/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("$name, Hello from Kotlin (test)",responseBody)

    }


}
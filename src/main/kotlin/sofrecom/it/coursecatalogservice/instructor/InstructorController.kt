package sofrecom.it.coursecatalogservice.instructor

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/instructors")
class InstructorController(private val instructorService: InstructorService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addInstructor(@RequestBody instructorDTO: InstructorDTO) = instructorService.addInstructor(instructorDTO)

    @DeleteMapping("/{id}")
    fun deleteInstructor(@PathVariable id: Int): InstructorDTO = instructorService.deleteInstructor(id)
}
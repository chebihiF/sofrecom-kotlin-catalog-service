package sofrecom.it.coursecatalogservice.courses

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/courses")
class CourseController(val courseService: ICourseService) {

    @PostMapping
    fun addCourse(@RequestBody course: CourseDTO):CourseDTO = courseService.addCourse(course)

}
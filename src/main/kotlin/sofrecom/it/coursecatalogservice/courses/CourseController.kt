package sofrecom.it.coursecatalogservice.courses

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import sofrecom.it.coursecatalogservice.courses.services.ICourseService
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/courses")
@Validated
class CourseController(val courseService: ICourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody @Valid course: CourseDTO):CourseDTO = courseService.addCourse(course)

    @GetMapping()
    fun retrieveCourseByKeyword(@RequestParam("keyword") keyword:String?) : List<CourseDTO> = courseService.getCoursesByKeyword(keyword)

    /*@GetMapping
    fun getCourses() = courseService.getCourses()*/

    @GetMapping("/{id}")
    fun getCoursesById(@PathVariable("id") id: Int):CourseDTO = courseService.getCourseById(id)

    @PutMapping("/{id}")
    fun putCourse(@PathVariable("id") id: Int, @RequestBody courseDTO: CourseDTO) = courseService.putCourse(courseDTO,id)

    @DeleteMapping("/{id}")
    fun deleteCourse(@PathVariable("id") id: Int) = courseService.deleteCourse(id)
}
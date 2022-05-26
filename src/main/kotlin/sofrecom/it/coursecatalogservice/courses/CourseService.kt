package sofrecom.it.coursecatalogservice.courses

import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository:CourseRepository) : ICourseService {

    override fun addCourse(courseDTO: CourseDTO): CourseDTO {
        val course = courseDTO.let {
            println(it)
            Course(null, it.title, it.category)
        }
        courseRepository.save(course)
        return course.let {
            CourseDTO(it.id,it.title,it.category)
        }
    }
}
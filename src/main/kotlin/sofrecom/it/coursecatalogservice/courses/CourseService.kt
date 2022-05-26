package sofrecom.it.coursecatalogservice.courses

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import sofrecom.it.coursecatalogservice.courses.execeptions.CourseNotFoundException

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

    override fun getCourses() : List<CourseDTO> = courseRepository.findAll().map { CourseDTO(it.id,it.title,it.category) }

    override fun getCourseById(id: Int): CourseDTO {
        val courseExists = courseRepository.findById(id)
        return if (courseExists.isPresent)
            courseExists.get().let {
                CourseDTO(it.id, it.title, it.category)
            }
        else
            throw CourseNotFoundException("Course $id not found")
    }

    override fun putCourse(courseDTO: CourseDTO, courseId: Int): CourseDTO {
        val courseExists = courseRepository.findById(courseId);
        return if(courseExists.isPresent) {
            courseExists.get().let {
                it.title = courseDTO.title;
                it.category = courseDTO.category
                courseRepository.save(it)
                CourseDTO(it.id, it.title, it.category)
            }
        }else{
            throw CourseNotFoundException("course $courseId not found")
        }
    }

    override fun deleteCourse(id: Int) : CourseDTO {
        val courseExists = courseRepository.findById(id);
        return if(courseExists.isPresent) {
            val courseDTOcopy = courseExists.get().let {
                CourseDTO(it.id, it.title, it.category)
            }.copy()
            courseRepository.delete(courseExists.get())
            courseDTOcopy
        }else{
            throw CourseNotFoundException("course $id not found")
        }
    }


}
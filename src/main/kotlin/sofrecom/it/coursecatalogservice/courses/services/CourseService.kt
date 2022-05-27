package sofrecom.it.coursecatalogservice.courses.services

import org.springframework.stereotype.Service
import sofrecom.it.coursecatalogservice.courses.Course
import sofrecom.it.coursecatalogservice.courses.CourseDTO
import sofrecom.it.coursecatalogservice.courses.CourseRepository
import sofrecom.it.coursecatalogservice.courses.execeptions.CourseNotFoundException
import sofrecom.it.coursecatalogservice.instructor.Instructor
import sofrecom.it.coursecatalogservice.instructor.InstructorDTO
import sofrecom.it.coursecatalogservice.instructor.InstructorRepository
import sofrecom.it.coursecatalogservice.instructor.exceptions.InstructorNotFoundException

@Service
class CourseService(val courseRepository: CourseRepository, val instructorRepository: InstructorRepository) : ICourseService {

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


    override fun getCoursesByKeyword(keyword: String?): List<CourseDTO> {
        return keyword?.let {
            courseRepository.findCoursesByKeyword(it).map { CourseDTO(it.id,it.title,it.category) }
        }?: courseRepository.findAll().map { CourseDTO(it.id,it.title,it.category) }
    }

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

    override fun findCourseByInstructor(id: Int): List<CourseDTO>  {

        val existInstructor = instructorRepository.findById(id)
        return if(existInstructor.isPresent){
            courseRepository.findCoursesByInstructor(existInstructor.get().let { Instructor(it.id,it.name) }).map { CourseDTO(it.id,it.title,it.category) }
        }else
            throw InstructorNotFoundException("instructor not found")


    }
    //courseRepository.findCoursesByInstructor(instructorDTO.let { Instructor(it.id,it.name) }).map { CourseDTO(it.id,it.title,it.category) }



}
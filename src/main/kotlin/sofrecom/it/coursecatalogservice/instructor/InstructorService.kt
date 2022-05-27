package sofrecom.it.coursecatalogservice.instructor

import org.springframework.stereotype.Service
import sofrecom.it.coursecatalogservice.instructor.exceptions.InstructorNotFoundException

@Service
class InstructorService(private val instructorRepository: InstructorRepository): IInstructorService {


    override fun addInstructor(instructorDTO: InstructorDTO): InstructorDTO {
        val instructor = instructorDTO.let {
            Instructor(null, it.name)
        }
        instructorRepository.save(instructor)
        return instructor.let {
            InstructorDTO(it.id,it.name)
        }
    }

    override fun deleteInstructor(id: Int): InstructorDTO {
        val instructorOptional = instructorRepository.findById(id)
        return if(instructorOptional.isPresent){
            val instructorDTO = instructorOptional.get().let {
                InstructorDTO(it.id, it.name)
            }.copy()
            instructorRepository.delete(instructorOptional.get())
            instructorDTO
            }else
                throw InstructorNotFoundException("instructor not found")
        }

}
package sofrecom.it.coursecatalogservice.instructor

interface IInstructorService {
    fun addInstructor(instructorDTO: InstructorDTO): InstructorDTO
    fun deleteInstructor(id:Int): InstructorDTO
}
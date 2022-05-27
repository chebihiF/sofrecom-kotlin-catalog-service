package sofrecom.it.coursecatalogservice.instructor

import sofrecom.it.coursecatalogservice.courses.Course

data class InstructorDTO(
    val id: Int?,
    val name: String,
    val courses: List<Course>? = null
)
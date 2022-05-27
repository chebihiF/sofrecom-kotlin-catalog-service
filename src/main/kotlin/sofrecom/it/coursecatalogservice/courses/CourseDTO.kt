package sofrecom.it.coursecatalogservice.courses

import javax.validation.constraints.NotBlank

data class CourseDTO(
    val id: Int?,
    @get:NotBlank(message = "CourseDTO.title must be not blank")
    val title:String,
    @get:NotBlank(message = "CourseDTO.category must be not blank")
    val category:String
)

package sofrecom.it.coursecatalogservice.courses

import org.springframework.lang.NonNull
import sofrecom.it.coursecatalogservice.instructor.Instructor
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Course(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var title:String,
    var category:String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    val instructor: Instructor? = null
)
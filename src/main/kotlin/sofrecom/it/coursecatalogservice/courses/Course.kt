package sofrecom.it.coursecatalogservice.courses

import org.springframework.lang.NonNull
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class Course(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var title:String,
    var category:String
)
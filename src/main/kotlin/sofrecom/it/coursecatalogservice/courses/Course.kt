package sofrecom.it.coursecatalogservice.courses

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Course(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val title:String,
    val category:String
)
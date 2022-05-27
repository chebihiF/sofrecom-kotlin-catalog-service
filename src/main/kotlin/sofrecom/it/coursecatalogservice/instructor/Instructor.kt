package sofrecom.it.coursecatalogservice.instructor

import org.hibernate.Hibernate
import sofrecom.it.coursecatalogservice.courses.Course
import javax.persistence.*

@Entity
data class Instructor(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val name: String,
    @OneToMany(mappedBy = "instructor", cascade = [CascadeType.ALL])
    val courses: List<Course>? = null
){
    override fun toString(): String {
        return "Instructor(id=$id, name='$name', courses=${courses?.map { it.title }})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Instructor

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
package sofrecom.it.coursecatalogservice.service

import MyCourse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class GreetingsService : IGreetingsService {

    @Value("\${message}")
    lateinit var message: String

    override fun retrieveGreetings(name:String) = "$name, $message"

    val myCourse: MyCourse = MyCourse(1,"test")


}
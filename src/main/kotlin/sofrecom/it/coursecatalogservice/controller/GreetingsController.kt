package sofrecom.it.coursecatalogservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sofrecom.it.coursecatalogservice.service.IGreetingsService

@RestController
@RequestMapping("/api/v1/greetings")
class GreetingsController(val greetingService: IGreetingsService) {


    @GetMapping("/{name}")
    fun retrieveGreetings(@PathVariable("name") name: String) : String{
        return greetingService.retrieveGreetings(name)
    }
}
package sofrecom.it.coursecatalogservice.courses.execeptions

class CourseNotFoundException(s: String) : RuntimeException(s) {
    override var message: String? = ""
        get()  {
            message = super.message+" #### "
            return field
        }
}

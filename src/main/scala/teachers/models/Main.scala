// akka specific
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
// akka http specific
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives.*
// spray specific (JSON marshalling)
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
import spray.json.DefaultJsonProtocol.*
// cors
import ch.megard.akka.http.cors.scaladsl.CorsDirectives.*

final case class Teacher (id: Long, name: String, email: String)

final case class Course( id: Long, modified_by:String, name: String, description: String)
@main def teacherSerice: Unit = 

  implicit val actorSystem = ActorSystem(Behaviors.empty, "akka-http")
  implicit val teacherMarshaller: spray.json.RootJsonFormat[Teacher] = jsonFormat3(Teacher.apply)
  implicit val courseMarshaller: spray.json.RootJsonFormat[Course] = jsonFormat4(Course.apply)
  val getTeacher = get {
      concat(
        path("hello") {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Hello world from scala akka http server!"))
        },
        path("teacher" / LongNumber) {
          teacherid => {
            println("get teacher by id")
              teacherid match {
                case 1 => complete(Teacher(1, "teacher1","teacher1@gmail.com"))
                case _ => complete(StatusCodes.NotFound)
              }
          }
        }
      )
  }
  
  val createTeacher = post {
    path("teacher") {
      entity(as[Teacher]) {
        teacher => {
          println("save teacher")
          complete(Teacher(teacher.id, teacher.name, teacher.email))
        }
      }
    }
  }

  val updateTeacher = put {
    path("teacher") {
      entity (as[Teacher]) {
        teacher => {
          println("update teacher")
          complete(Teacher(teacher.id, teacher.name, teacher.email))
        }
      }
    }
  }

  val deleteTeacher = delete {
    path ("teacher" / LongNumber) {
      teacherid => {
        println(s"teacher ${teacherid} deleted")
        complete(Teacher(teacherid, "teacher.name", "teacher.email"))
      }
    }
  }
    val updatecourse = post {
    path("course" / LongNumber) {
          courseid => {
            println("course modified by teacher1")
              courseid match {
                case 1 => complete(Course(1, "physics","teacher1","this is a physics course"))
                case _ => complete(StatusCodes.NotFound)
              }
          }
        }
  }

  

  val route = cors() {
    concat(getTeacher, createTeacher, updateTeacher, deleteTeacher,updatecourse)
  }

  val bindFuture = Http().newServerAt("127.0.0.1", 8080).bind(route)


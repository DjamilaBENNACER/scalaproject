// // akka specific
// import akka.actor.typed.ActorSystem
// import akka.actor.typed.scaladsl.Behaviors
// // akka http specific
// import akka.http.scaladsl.Http
// import akka.http.scaladsl.model._
// import akka.http.scaladsl.server.Directives.*
// // spray specific (JSON marshalling)
// import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport.*
// import spray.json.DefaultJsonProtocol.*
// // cors
// import ch.megard.akka.http.cors.scaladsl.CorsDirectives.*

// final case class Student (id: Long, name: String, email: String)

// @main def studentSerice: Unit = 

//   implicit val actorSystem = ActorSystem(Behaviors.empty, "akka-http")
//   implicit val studentMarshaller: spray.json.RootJsonFormat[Student] = jsonFormat3(Student.apply)

//   val getStudent = get {
//       concat(
//         path("hello") {
//           complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Hello world from scala akka http server!"))
//         },
//         path("student" / LongNumber) {
//           studentid => {
//             println("get student by id")
//               studentid match {
//                 case 1 => complete(Student("1", "student1","student1@gmail.com"))
//                 case _ => complete(StatusCodes.NotFound)
//               }
//           }
//         }
//       )
//   }
  
//   val createStudent = post {
//     path("student") {
//       entity(as[Student]) {
//         student => {
//           println("save student")
//           complete(Student(student.id, student.name, student.email))
//         }
//       }
//     }
//   }

//   val updateStudent = put {
//     path("student") {
//       entity (as[Student]) {
//         student => {
//           println("update student")
//           complete(Student(student.id, student.name, student.email))
//         }
//       }
//     }
//   }

//   val deleteStudent = delete {
//     path ("student" / LongNumber) {
//       studentid => {
//         println(s"student ${studentid} deleted")
//         complete(Student(studentid, "student.name", "student.email"))
//       }
//     }
//   }

//   val route = cors() {
//     concat(getStudent, createStudent, updateStudent, deleteStudent)
//   }

//   val bindFuture = Http().newServerAt("127.0.0.1", 8080).bind(route)

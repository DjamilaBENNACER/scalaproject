package models

case class Student (id: Long, name: String, email: String)

object Student {
  def listStudents(): List[Student] = List(
    Student(1, "amariano", "amariano@outlook.com"),
    Student(2, "dodwan", "dodwan@gmail.com"),
    Student(3, "mariano", "mariano@gmail.com"),
    Student(4, "stephen", "stephen@gmail.com"),
    Student(5, "treser", "treser@yahoo.fr")),

}

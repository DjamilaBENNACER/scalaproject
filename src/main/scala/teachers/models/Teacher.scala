package models

case class Teacher (id: Long, name: String, email: String)

object Teacher {
  def listTeachers(): List[Teacher] = List(
    Teacher(1, "stephen", "stephen@gmail.com"),
    Teacher(2, "treser", "treser@yahoo.fr"),
    Teacher(3, "mariano", "mariano@outlook.com"),
    Teacher(4, "dodwan", "dodwan@gmail.com"),
    Teacher(5, "amariano", "amariano@gmail.com")),
}

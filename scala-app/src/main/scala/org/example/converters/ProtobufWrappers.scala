package org.example.converters

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, LocalDateTime}
import java.util.UUID

import org.example.domain.Gender

import scalapb.TypeMapper
import scala.util.Try

object ProtobufWrappers {
  implicit val uuidTypeMapper: TypeMapper[String, UUID] =
    TypeMapper(UUID.fromString)(_.toString)

  private val dateTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS")
  private val dateFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd")

  implicit val localDateMapper: TypeMapper[String, LocalDate] = {
    TypeMapper[String, LocalDate](
      s =>
        Try(LocalDate.parse(s, dateFormatter))
          .getOrElse(LocalDate.now()))(dt => dt.format(dateFormatter))
  }

  implicit val localDateTimeMapper: TypeMapper[String, LocalDateTime] = {
    TypeMapper[String, LocalDateTime](
      s =>
        Try(LocalDateTime.parse(s, dateTimeFormatter))
          .getOrElse(LocalDateTime.now()))(dt => dt.format(dateTimeFormatter))
  }

  implicit val genderMapper: TypeMapper[String, Gender.Gender] = {
    TypeMapper[String, Gender.Gender](Gender.withName)(_.toString)
  }

}

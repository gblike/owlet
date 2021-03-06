package us.oyanglul.owlet

import cats._
import cats.tests.CatsSuite
import cats.laws.discipline.FunctorTests
import monix.eval.Task
import org.scalacheck._
import Arbitrary.arbitrary
import DOM._
import monix.execution.Scheduler.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._

class OwletSpec extends CatsSuite {

  implicit def eqOwlet[A: Eq]: Eq[Owlet[A]] = new Eq[Owlet[A]] {
    def eqv(a: Owlet[A], b: Owlet[A]) = {
      a.nodes == b.nodes
    }
  }

  implicit def arbOwletInt: Arbitrary[Owlet[Int]] =
    Arbitrary((for {
      e <- arbitrary[Int]
      s <- arbitrary[String]
    } yield int(s, e)))

  checkAll("Owlet.FunctorLaws", FunctorTests[Owlet].functor[Int, Int, Int])
}

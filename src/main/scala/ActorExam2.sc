import scala.actors.Actor
import scala.actors.Actor._
val sillyActor = actor {
  loop {
    react {
      case (msg: String, actor: Actor) => actor ! ("Message: " + msg)
      case _ => println("not string: ")
    }
  }
}
sillyActor ! ("Hi", self)
self.receiveWithin(0) { case x => x }

sillyActor ! (11, self)
self.receiveWithin(0) { case x => x }



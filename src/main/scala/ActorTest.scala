import akka.actor._

class EchoActor extends Actor {
  override def receive: Receive = {
    case msg => {
      for(i <- 1 to 5) {
        println("MESSAGE: " + msg)
        Thread.sleep(1000)
      }
    }
  }
}

object SynchronousActorExample extends App {
  val system = ActorSystem("actorexample")
  val echo = system.actorOf(Props[EchoActor], "echo")
  echo ! "test"
}

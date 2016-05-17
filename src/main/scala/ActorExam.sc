import scala.actors.Actor._
import scala.actors._
object NameResolver extends Actor {
  import java.net.{InetAddress, UnknownHostException}

  def act() {
    react {
      case (name: String, actor: Actor) =>
        System.out.println("acting...")
        actor ! getIp(name);
      case msg => println("unhandled message: " + msg);
    }
  }

  def getIp(name: String): Option[InetAddress] = {
    try {
      Some(InetAddress.getByName(name))
    } catch {
      case _: UnknownHostException => None
    }
  }
}

NameResolver.start()
NameResolver ! ("www.naver.com", self)

val result = self.receiveWithin(0) { case x => x }

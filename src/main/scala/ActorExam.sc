import java.net.{InetAddress, UnknownHostException}
import scala.actors.Actor._
import scala.actors._

object NameResolver extends Actor {
  def act() {
      react {
        case (name: String, actor: Actor) =>
          actor ! getIp(name); act()
        case msg => println("unhandled message: " + msg); act()
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

self.receiveWithin(0) { case x => x }

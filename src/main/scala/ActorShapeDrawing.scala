import akka.actor.{ActorRef, Props, ActorSystem, Actor}
import com.typesafe.config.ConfigFactory

case class Point(x: Double = 0.0, y: Double = 0.0)
abstract class Shape {
  def draw(f: String => Unit): Unit = f(s"draw: ${this.toString}")
}

case class Circle(center: Point, radius: Double) extends Shape
case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape
case class Triangle(point1: Point, point2: Point, point3: Point) extends Shape

object Message {
  object Exit
  object Finished
  case class Response(message: String)
}

class ShapeDrawingActor extends Actor {
  import Message._
  def receive = {
    case s: Shape =>
      s.draw(str => println(s"ShapeDrawingActor: $str"))
      sender ! Response(s"ShapesDrawingActor: $s drawn")
    case Exit => sender ! Finished
    case unexpected => sender ! Response(s"unexpected: $unexpected")
  }
}

private object Start

object ActorShapeDrawing {
  def main (args: Array[String]) {
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(Props(new ShapeDrawingActor), "drawingActor")
    val driver = system.actorOf(Props(new ActorShapeDrawing(drawer)))
    driver ! Start
  }
}

class ActorShapeDrawing(drawerActor: ActorRef) extends Actor {
  import Message._

  def receive = {
    case Start =>
      drawerActor ! Circle(Point(0.0, 0.0), 1.0)
      drawerActor ! Rectangle(Point(0.0, 0.0), 1.0, 2.0)
      drawerActor ! Triangle(Point(0.0, 0.0), Point(1.0, 0.0), Point(1.0, 1.0))
      drawerActor ! Exit
    case Finished =>
      println("Driver finished...")
      context.system.shutdown()
    case response: Response => println(s"Driver Response: $response")
    case unexpected => println(s"Driver unexpected: $unexpected")
  }
}

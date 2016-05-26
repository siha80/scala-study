package netty

import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.group.DefaultChannelGroup
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ArsServerHandler extends SimpleChannelInboundHandler[String] {
  val LOGGER: Nothing = LoggerFactory.getLogger(this.getClass.getName)

  val channels = new DefaultChannelGroup();
  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    Channel incoming = ctx.channel();
    for (Channel channel : channels) {
      channel.write("[SERVER] - " + incoming.remoteAddress() + " has joined\n");
    }
    channels.add(ctx.channel());
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    Channel incoming = ctx.channel();
    for (Channel channel : channels) {
      channel.write("[SERVER] - " + incoming.remoteAddress() + " has left\n");
    }
    channels.remove(ctx.channel());
  }

  @Override
  public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    Channel incoming = channelHandlerContext.channel();
    for (Channel channel : channels) {
      if (channel != incoming){
        channel.write("[" + incoming.remoteAddress() + "]" + s + "\n");
      }
    }

  }
}


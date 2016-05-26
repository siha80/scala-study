package netty

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.codec.string.{StringEncoder, StringDecoder}
import io.netty.handler.codec.{Delimiters, DelimiterBasedFrameDecoder}

/**
 * Created by skplanet on 2016-05-26.
 */
class ArsServerInitializer extends ChannelInitializer[SocketChannel] {
  override def initChannel(socketChannel: SocketChannel): Unit = {
    val pipeline = socketChannel.pipeline()
    pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, true, Delimiters.lineDelimiter()));
    pipeline.addLast("decoder", new StringDecoder());
    pipeline.addLast("encoder", new StringEncoder());
//    pipeline.addLast("handler", new ChatServerHandler());
  }
}

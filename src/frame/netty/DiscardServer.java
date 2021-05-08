//package frame.netty;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//
//
//
//public class DiscardServer {
//
//    public static void main(String[] args) throws Exception{
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap b = new ServerBootstrap();
//            b.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .handler(new LoggingHandler(LogLevel.INFO))
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        public void initChannel(SocketChannel ch) {
//                            ChannelPipeline p = ch.pipeline();
//                            p.addLast(new DiscardServerHandler());
//                        }
//                    });
//
//            // Bind and start to accept incoming connections.
//            ChannelFuture f = b.bind(8000).sync();
//
//            // Wait until the server socket is closed.
//            // In this example, this does not happen, but you can do that to gracefully
//            // shut down your server.
//            f.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//        }
//    }
//
//    static class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {
//
//        @Override
//        public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//            // discard
//            System.out.println("channelRead0 msg = " + msg);
//        }
//
//        @Override
//        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//            // Close the connection when an exception is raised.
//            cause.printStackTrace();
//            ctx.close();
//        }
//    }
//}

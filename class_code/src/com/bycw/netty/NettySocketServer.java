package com.bycw.netty;

import io.netty.bootstrap.ServerBootstrap; // netty 启动对象;
import io.netty.channel.ChannelInitializer; // 管道的初始化工厂对象基类;
import io.netty.channel.ChannelOption; // 管道的选项
import io.netty.channel.ChannelPipeline; // 管道的数据通道 
import io.netty.channel.EventLoopGroup; 
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress; // Java IP地址;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class NettySocketServer {
	private Logger logger = LoggerFactory.getLogger(getClass()); // new logger对象来出来做方便打印;
	
	private EventLoopGroup bossGroup = new NioEventLoopGroup(4); // 内部线程的数目;
	private EventLoopGroup workerGroup = new NioEventLoopGroup(); // 不写，就是默认的线程数目;
	
	public void startServer(int serverPort) throws Exception {
		logger.info("netty socket服务已启动，正在监听用户的请求@port:" + serverPort + "......");
		try {
			ServerBootstrap b = new ServerBootstrap(); // new boot对象，来启动我们的netty;
			
			// 启动配置
			b.group(bossGroup, workerGroup) // 配置group, bossGroup负责我们的 监听socket接入新的socket,  workerGroup--->IO,处理我们的socket的IO数据 
			.channel(NioServerSocketChannel.class) // 配置NioServerSocketChannel
			.option(ChannelOption.SO_BACKLOG, 1024) // 允许最多等待连接请求的数目;
			.childHandler(new ChildChannelHandler()); // 每个客户端都会有一个chanle, 客户端的chanle 的初始化工厂对象, 当有新的客户端的chanle进来以后，就会丢给channel初始化工厂对象,来初始化这个客户端channel
			// end
			
			b.bind(new InetSocketAddress(serverPort)).sync(); // 开启服务器的监听;
//			f.channel().closeFuture().sync();
		} catch (Exception e) {
			logger.error("", e);

			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();

			throw e;
		}
	}
	
	// 一定要继承 ChannelInitializer
	// 当有一个新的channel, 被建立连接的时候，就会调用这个对象的，initChannel函数;
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		
		// 重载这个initChannel
		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			logger.info("initChannel");
			ChannelPipeline pipeline = arg0.pipeline(); // 获取channel的数据管道;
			
			pipeline.addLast(new StringDecoder()); // 收到数据以后，我们需要一个解码器---》数据解码出来成一个对象;
			pipeline.addLast(new StringEncoder()); // 发送一个object的时候---》编码器，来编码object--->二进制数据，网络传送出去;
			
			// 客户端300秒没收发包，便会触发UserEventTriggered事件到IdleEventHandler
			pipeline.addLast(new IdleStateHandler(0, 0, 300));
			// 管道的事件: channel建立，有数据可读，channel关闭;
			// 传一个事件处理对象进去，重载特定的接口，当有某种事件发生以后，调用这个对象.重载的接口
			pipeline.addLast(new ChanelEventHandler()); // Channel IO Event事件处理;
		}
	}
}



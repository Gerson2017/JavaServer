package com.bycw.http;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.http.HttpServerCodec;
import org.apache.mina.http.api.DefaultHttpResponse;
import org.apache.mina.http.api.HttpRequest;
import org.apache.mina.http.api.HttpResponse;
import org.apache.mina.http.api.HttpStatus;
import org.apache.mina.http.api.HttpVersion;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

// import com.kingston.jforgame.socket.session.SessionManager;

/**
 * @author kingston
 */
public class HttpServer {

	private Logger logger = LoggerFactory.getLogger(HttpServer.class);

	private IoAcceptor acceptor;

	private int port;

	public void start() throws Exception {
		acceptor = new NioSocketAcceptor();
		acceptor.getFilterChain().addLast("codec", new HttpServerCodec());
		acceptor.setHandler(new HttpServerHandle());

		
		this.port = 6080;
		acceptor.bind(new InetSocketAddress(port));
	}

	public void shutdown() {
		if (acceptor != null) {
			acceptor.unbind();
			acceptor.dispose();
		}
		logger.error("---------> http server stop at port:{}", port);
	}
}

class HttpServerHandle extends IoHandlerAdapter {

	private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		cause.printStackTrace();
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		String ipAddr = ((InetSocketAddress)session.getRemoteAddress()).getAddress().getHostAddress();
		logger.info("ipAddr enter: " + ipAddr);
		
	}


	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		if (message instanceof HttpRequest) {
			// ????????????????????????????????????HttpRequest??????
			HttpRequest request = (HttpRequest) message;
			
			// ?????????????????????????????????????????????,???????????????????????????????????????
			HttpCommandResponse commandResponse = this.handleCommand(request);
			// end
			
			
			// ??????HTML
			String responseHtml = new Gson().toJson(commandResponse);
			byte[] responseBytes = responseHtml.getBytes("UTF-8");
			int contentLength = responseBytes.length;

			// ??????HttpResponse?????????HttpResponse??????????????????status line???header??????
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "text/html; charset=utf-8");
			headers.put("Content-Length", Integer.toString(contentLength));
			HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SUCCESS_OK, headers);

			// ??????BODY
			IoBuffer responseIoBuffer = IoBuffer.allocate(contentLength);
			responseIoBuffer.put(responseBytes);
			responseIoBuffer.flip();
			// ?????????status line???header??????
			session.write(response);
			// ??????body??????
			session.write(responseIoBuffer);
		}
	}

	private HttpCommandResponse handleCommand(HttpRequest request) {
		logger.info(request.getRequestPath());
		
		HttpCommandParams httpParams = toHttpParams(request);
		HttpCommandResponse commandResponse = HttpCommandManager.getInstance().handleCommand(request.getRequestPath(), httpParams);
		if (commandResponse == null) {
			HttpCommandResponse failed = HttpCommandResponse.valueOfFailed();
			failed.setMessage("??????????????????");
			return failed;
		}
		return commandResponse;
	}

	@SuppressWarnings("unchecked")
	private HttpCommandParams toHttpParams(HttpRequest httpReq) {
		String cmd = httpReq.getParameter("cmd");
		if (StringUtils.isEmpty(cmd)) {
			return null;
		}
		String paramJson = httpReq.getParameter("params");
		Map<String, String> params = new HashMap<>();
		if (StringUtils.isNotEmpty(paramJson)) {
			try {
				params = new Gson().fromJson(paramJson, HashMap.class);
			} catch (Exception e) {
			}
		}
		return HttpCommandParams.valueOf(Integer.parseInt(cmd), params);
	}

}
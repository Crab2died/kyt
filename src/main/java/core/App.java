package core;

import java.util.logging.Logger;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.util.log.StdErrLog;
import org.eclipse.jetty.webapp.WebAppContext;

public class App {
	
	private static Logger logger = Logger.getLogger(App.class.getName());
	/**
	 * port
	 */
	private int port = 8090;

	/**
	 * webapp
	 */
	private String webapp = "/webapp";

	/**
	 * contextpath
	 */
	private String contextpath = "/";

	protected String charset = "UTF-8";
	
	private final static String WBE_XML = "/WEB-INF/web.xml";

	public App() {
	}

	public App(int port, String webapp, String contextpath) {
		this.port = port;
		this.webapp = webapp;
		this.contextpath = contextpath;
	}

	public App(int port, String webapp, String contextpath, String charset) {
		super();
		this.port = port;
		this.webapp = webapp;
		this.contextpath = contextpath;
		this.charset = charset;
	}

	/**
	 * 服务器启动。
	 * 
	 */
	public void start() {
		// 设置Jetty日志
		System.setProperty("org.eclipse.jetty.util.log.class", StdErrLog.class.getName());
		HandlerCollection handlers = new HandlerCollection();
		Server server = new Server(this.port);
		RequestLogHandler requestLogHandler = new RequestLogHandler();
		// 设置context
		WebAppContext context = new WebAppContext();
		context.setContextPath(this.contextpath);
		context.setResourceBase(this.webapp);
		// 设置描述符位置
		context.setDescriptor(this.webapp + WBE_XML);
		// PS:嵌入式的Jetty，应用当前工程的ClassPath，如果不设置将使用WebAppClassLoder，WEB-INF/lib目录加载jar。
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setParentLoaderPriority(true);
		handlers.setHandlers(new Handler[] { context, new DefaultHandler(), requestLogHandler });
		server.setHandler(handlers);
		// 启动Server
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			logger.info(String.format("START THE WEB PROJECT ERROR >>> PORT = %s : CONTEXT PATH = %s", this.port, this.contextpath));
		}
	}

	public static void main(String[] args) {
		App server = new App();
		for (String arg : args) {
			if (arg.startsWith("--port="))
				server.port = Integer.parseInt(arg.substring(7));
			if (arg.startsWith("--webapp="))
				server.webapp = arg.substring(9);
			if (arg.startsWith("--contextpath="))
				server.contextpath = arg.substring(14);
		}
		logger.info("##########################################################################################");
		logger.info(String.format("STARTING THE WEB PROJECT >>> \n\t {PORT = %s , CONTEXT PATH = %s} ...",
					server.port, server.contextpath));
		server.start();
	}
}

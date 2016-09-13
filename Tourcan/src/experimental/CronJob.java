package experimental;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CronJob implements ServletContextListener {

	private Thread t = null;
	private ServletContext context;
	private Integer flag = 0;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		t = new Thread() {
			// task
			public void run() {
				try {
					while (true) {
						// System.out.println("Thread running every second");
						CronJob.this.setFlag();
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
				}
			}
		};
		t.start();
		context = sce.getServletContext();
		// you can set a context variable just like this
		context.setAttribute("AccessPoint", this);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		t.interrupt();
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag() {
		this.flag++;
	}

}

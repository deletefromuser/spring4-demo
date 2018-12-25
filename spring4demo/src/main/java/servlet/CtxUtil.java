package servlet;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//@Component
public class CtxUtil implements ApplicationContextAware {

	public static ApplicationContext ctx;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ctx = applicationContext;
	}

	/**
	 * 根据类型获得bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return ctx.getBean(clazz);
	}

	/**
	 * 根据名称名称获得bean
	 */
	public static Object getBean(String name) {
		return ctx.getBean(name);
	}

}

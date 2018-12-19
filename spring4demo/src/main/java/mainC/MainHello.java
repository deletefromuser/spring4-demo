package mainC;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.AnnoTest;
import bean.HelloWorld;
import bean.JavaBasedConf;
import bean.JavaCollection;
import bean.Msg;
import bean.MsgA;
import bean.TextEditor;
import bean.User;
import listener.CustomEventPublisher;

public class MainHello {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("bean/Beans.xml");
//		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
//		obj.getMessage();
//
//		HelloWorld objA = (HelloWorld) context.getBean("helloWorld");
//
//		objA.setMessage("I'm object A");
//		objA.getMessage();
//
//		HelloWorld objB = (HelloWorld) context.getBean("helloWorld");
//		objB.getMessage();
//-----------------------------------------------
//		TextEditor te = (TextEditor) context.getBean("textEditor");
//		te.spellCheck();
//
//		JavaCollection jc = (JavaCollection) context.getBean("javaCollection");
//
//		jc.getAddressList();
//		jc.getAddressSet();
//		jc.getAddressMap();
//		jc.getAddressProp();
//-----------------------------------------------
//		context.start();
//		User user = context.getBean("user", User.class);
//		user.getCat().shout();
//		user.getDog().shout();
//		System.out.println(user.getStr());
		// -----------------------------------------------
		AnnoTest aTest = (AnnoTest) context.getBean("annoTest");
		aTest.display();
		// -----------------------------------------------
//		CustomEventPublisher cvp = (CustomEventPublisher) context.getBean("customEventPublisher");
//		cvp.publish();
//		cvp.publish();
//
//		context.stop();
//		context.registerShutdownHook();
		// -----------------------------------------------

//		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(JavaBasedConf.class);
//
//		ctx.start();
//		Msg a = ctx.getBean(Msg.class);
//		a.show();
//		MsgA b = (MsgA) ctx.getBean("msgA");
//		b.show();
		// -----------------------------------------------
		ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:application.xml");
		System.out.println((String) factory.getBean("address3"));
	}
}

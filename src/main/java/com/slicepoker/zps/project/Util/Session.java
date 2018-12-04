package com.slicepoker.zps.project.Util;

import org.apache.catalina.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashSet;

/**
 * @author Zps
 * @date 2018/11/30 9:35
 **/
@WebListener
public class Session implements HttpSessionListener, HttpSessionAttributeListener {
    public static final Logger logger =LoggerFactory.getLogger(SessionListener.class);


    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        logger.info("attributeAdded");
        HttpSession httpSession = se.getSession();
        logger.info("key"+se.getName());
        logger.info("valuse"+se.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        logger.info("attributeRemoved");
    }


    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        logger.info("attributeReplaced");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("sessionCreated");
        HttpSession httpSession = se.getSession();
        ServletContext application = httpSession.getServletContext();
        HashSet hashSet = (HashSet) application.getAttribute("sessions");
        if (hashSet == null){
            hashSet = new HashSet();
            application.setAttribute("sessions",hashSet);
        }
        hashSet.add(httpSession);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) throws ClassCastException{
        logger.info("sessionDestroyed");
        HttpSession session = se.getSession();
        logger.info("deletedSessionId:"+session.getId());
        System.out.println(session.getCreationTime());
        System.out.println(session.getLastAccessedTime());
        ServletContext application = session.getServletContext();
        HashSet sessions = (HashSet) application.getAttribute("session");
        sessions.remove(session);

    }
}

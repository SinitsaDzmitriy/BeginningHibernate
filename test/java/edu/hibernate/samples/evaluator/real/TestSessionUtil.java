package edu.hibernate.samples.evaluator.real;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSessionUtil {
    @Test
    public void getSession() {
        try(Session session = SessionUtil.getSession()) {
            Assert.assertNotNull(session);
        }
    }
}

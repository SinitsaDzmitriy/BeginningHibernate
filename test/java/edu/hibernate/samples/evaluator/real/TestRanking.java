package edu.hibernate.samples.evaluator.real;

import edu.hibernate.samples.evaluator.service.IRankingService;
import edu.hibernate.samples.evaluator.service.RankingService;
import edu.hibernate.samples.evaluator.service.RankingServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRanking {
    private IRankingService service;

    public TestRanking() {
        service = new RankingServiceImpl();
    }

    @Test
    public void addRanking() {
        service.addRanking("John Snow", "Samwell Tarly",
                "swordplay", 9);
        Assert.assertEquals(service.getAverageRanking("John Snow", "swordplay"), 9);
    }
}

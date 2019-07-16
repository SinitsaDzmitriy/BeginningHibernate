package edu.hibernate.samples.evaluator.real;

import edu.hibernate.samples.evaluator.service.IRankingService;
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

    @Test
    public void updateExistentRanking() {
        final int INITIAL_RANKING = 5;
        final int UPDATED_RANKING = 6;

        service.addRanking("John Snow", "Baristan Selmy",
                "swordplay", INITIAL_RANKING);

        int currentRanking = service
                .getRanking("John Snow", "Baristan Selmy", "swordplay");

        Assert.assertEquals(currentRanking, INITIAL_RANKING);

        service.updateRanking("John Snow", "Baristan Selmy",
                "swordplay", UPDATED_RANKING);

        currentRanking = service
                .getRanking("John Snow", "Baristan Selmy", "swordplay");

        Assert.assertEquals(currentRanking, UPDATED_RANKING);
    }

    @Test
    public void updateNonexistentRanking() {
        final int RANKING_VALUE = 6;

        service.updateRanking("John Snow", "Baristan Selmy",
                "swordplay", RANKING_VALUE);

        Assert.assertEquals(RANKING_VALUE,
                service.getRanking("John Snow", "Baristan Selmy", "swordplay"));
    }
}

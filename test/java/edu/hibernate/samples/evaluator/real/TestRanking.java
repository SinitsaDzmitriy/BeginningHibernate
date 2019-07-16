package edu.hibernate.samples.evaluator.real;

import edu.hibernate.samples.evaluator.service.IRankingService;
import edu.hibernate.samples.evaluator.service.RankingServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityNotFoundException;

public class TestRanking {
    private IRankingService service;

    public TestRanking() {
        service = new RankingServiceImpl();
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void getNonexistentRanking() {
        service.getRanking("John Snow", "Samwell Tarly", "swordplay");
    }

    @Test (dependsOnMethods = "addAndGetRanking", alwaysRun = true)
    public void addAndGetRanking() {
        service.addRanking("John Snow", "Samwell Tarly",
                "swordplay", 9);
        Assert.assertEquals(service.getRanking("John Snow", "Samwell Tarly", "swordplay"), 9);
    }

    @Test(dependsOnMethods = "addAndGetRanking")
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

    @Test(dependsOnMethods = "addAndGetRanking")
    public void updateNonexistentRanking() {
        final int RANKING_VALUE = 6;

        service.updateRanking("John Snow", "Baristan Selmy",
                "swordplay", RANKING_VALUE);

        Assert.assertEquals(RANKING_VALUE,
                service.getRanking("John Snow", "Baristan Selmy", "swordplay"));
    }
}

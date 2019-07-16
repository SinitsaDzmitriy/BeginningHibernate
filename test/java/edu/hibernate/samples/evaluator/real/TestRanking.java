package edu.hibernate.samples.evaluator.real;

import edu.hibernate.samples.evaluator.service.IRankingService;
import edu.hibernate.samples.evaluator.service.RankingServiceImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityNotFoundException;

public class TestRanking {
    private IRankingService service;

    private final String SUBJECT_NAME = "John Snow";

    private final String[] OBSERVERS_NAME =
            {"Samwell Tarly",
            "Baristan Selmy",
            "Stannis Baratheon",
            "Meryn Trant"};

    private final String SKILL_NAME = "swordplay";

    public TestRanking() {
        service = new RankingServiceImpl();
    }

    @Test(expectedExceptions = EntityNotFoundException.class)
    public void getNonexistentRanking() {
        service.getRanking(SUBJECT_NAME, OBSERVERS_NAME[0], SKILL_NAME);
    }

    @Test(dependsOnMethods = "getNonexistentRanking", alwaysRun = true)
    public void addAndGetRanking() {
        final int RANKING_VALUE = 9;
        service.addRanking(SUBJECT_NAME, OBSERVERS_NAME[0], SKILL_NAME, RANKING_VALUE);
        Assert.assertEquals(service.getRanking(SUBJECT_NAME, OBSERVERS_NAME[0], SKILL_NAME), RANKING_VALUE);
    }

    @Test(dependsOnMethods = "addAndGetRanking", alwaysRun = true)
    public void updateNonexistentRanking() {
        final int RANKING_VALUE = 6;

        service.updateRanking(SUBJECT_NAME, OBSERVERS_NAME[1], SKILL_NAME, RANKING_VALUE);

        Assert.assertEquals(RANKING_VALUE,
                service.getRanking(SUBJECT_NAME, OBSERVERS_NAME[1], SKILL_NAME));
    }

    @Test(dependsOnMethods = "updateNonexistentRanking", alwaysRun = true)
    public void updateExistentRanking() {
        final int INITIAL_RANKING = 6;
        final int UPDATED_RANKING = 7;

        service.addRanking(SUBJECT_NAME, OBSERVERS_NAME[2],
                SKILL_NAME, INITIAL_RANKING);

        int currentRanking = service
                .getRanking(SUBJECT_NAME, OBSERVERS_NAME[2], SKILL_NAME);

        Assert.assertEquals(currentRanking, INITIAL_RANKING);

        service.updateRanking(SUBJECT_NAME, OBSERVERS_NAME[2],
                SKILL_NAME, UPDATED_RANKING);

        currentRanking = service
                .getRanking(SUBJECT_NAME, OBSERVERS_NAME[2], SKILL_NAME);

        Assert.assertEquals(currentRanking, UPDATED_RANKING);
    }

    @Test(dependsOnMethods = "addAndGetRanking", expectedExceptions = EntityNotFoundException.class)
    public void deleteExistentRanking() {
        final int RANKING_VALUE = 8;

        // Adds a new Ranking (correct as tested before)
        service.addRanking(SUBJECT_NAME, OBSERVERS_NAME[3], SKILL_NAME, RANKING_VALUE);

        service.deleteRanking(SUBJECT_NAME, OBSERVERS_NAME[3], SKILL_NAME);

        // Throws EntityNotFoundException if the Ranking is deleted successfully.
        service.getRanking(SUBJECT_NAME, OBSERVERS_NAME[3], SKILL_NAME);
    }

    @Test(dependsOnMethods = "deleteExistentRanking")
    public void deleteNonexistentRanking() {
        service.deleteRanking(SUBJECT_NAME, OBSERVERS_NAME[3], SKILL_NAME);
    }
}

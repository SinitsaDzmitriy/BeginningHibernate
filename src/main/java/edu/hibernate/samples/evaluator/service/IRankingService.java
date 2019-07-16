package edu.hibernate.samples.evaluator.service;

import edu.hibernate.samples.evaluator.model.domain.Ranking;

public interface IRankingService {
    void addRanking(String subject, String observer, String skill, int ranking);
    int getRanking(String subject, String observer, String skill);
    int getAverageRanking(String subject, String skill);
    void updateRanking(String subject, String observer, String skill, int updatedRanking);
}

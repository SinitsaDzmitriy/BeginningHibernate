package edu.hibernate.samples.evaluator.service;

public interface IRankingService {
    void addRanking(String subject, String observer, String skill, int ranking);

    int getRanking(String subject, String observer, String skill);

    int getAverageRanking(String subject, String skill);

    void updateRanking(String subject, String observer, String skill, int updatedRanking);

    void deleteRanking(String subject, String observer, String skill);
}

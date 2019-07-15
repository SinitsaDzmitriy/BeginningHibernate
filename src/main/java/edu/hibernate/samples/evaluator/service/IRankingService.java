package edu.hibernate.samples.evaluator.service;

public interface IRankingService {
    void addRanking(String subject, String observer, String skill, int ranking);
    int getAverageRanking(String subject, String skill);
}

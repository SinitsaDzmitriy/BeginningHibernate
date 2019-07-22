package edu.hibernate.samples.largeobjects;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import edu.hibernate.samples.largeobjects.entity.JpaMappedPronunciation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JpaRunner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            byte[] audio = Files.readAllBytes(Paths.get("C:/Test/pron_en_guilty.mp3"));
            String article =
                    new String(Files.readAllBytes(Paths.get("C:/Test/article_en_guilty.txt")),
                            StandardCharsets.UTF_8);
            String transcription = "['ɡɪlti]";

            JpaMappedPronunciation pron =
                    new JpaMappedPronunciation(transcription, audio, article);

            Transaction trans = session.beginTransaction();

            session.persist(pron);

            trans.commit();

            session.clear();

            trans = session.beginTransaction();

            JpaMappedPronunciation receivedPron = session.find(JpaMappedPronunciation.class, pron.getId());

            trans.commit();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

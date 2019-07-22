package edu.hibernate.samples.largeobjects;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import edu.hibernate.samples.largeobjects.entity.separation.Pronunciation;
import edu.hibernate.samples.largeobjects.entity.separation.PronunciationPayload;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SeparateRunner {
    public static void main(String[] args) {
    try (Session session = SessionUtil.getSession()) {
        byte[] audio = Files.readAllBytes(Paths.get("C:/Test/pron_en_guilty.mp3"));
        String article =
                new String(Files.readAllBytes(Paths.get("C:/Test/article_en_guilty.txt")),
                        StandardCharsets.UTF_8);
        String transcription = "['ɡɪlti]";

        Pronunciation pron = new Pronunciation(transcription);
        PronunciationPayload pronPayload = new PronunciationPayload(
                pron,
                BlobProxy.generateProxy(audio),
                ClobProxy.generateProxy(article));

        Transaction trans = session.beginTransaction();
        session.persist(pronPayload);
        trans.commit();

        session.clear();

        trans = session.beginTransaction();
        Pronunciation fetchedPron = session.find(Pronunciation.class, pron.getId());
        PronunciationPayload fetchedPronPayload = session.find(PronunciationPayload.class, pron.getId());

        System.out.println(fetchedPronPayload.getArticle()
            .getSubString(1, (int) fetchedPronPayload.getArticle().length()));

        trans.commit();

    } catch (Exception e) {
        System.out.println(e);
    }
}
}

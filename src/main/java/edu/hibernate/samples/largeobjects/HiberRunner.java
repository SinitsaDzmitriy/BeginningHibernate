package edu.hibernate.samples.largeobjects;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import edu.hibernate.samples.largeobjects.entity.HiberMappedPronunciation;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;

import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HiberRunner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            byte[] audio = Files.readAllBytes(Paths.get("C:/Test/pron_en_guilty.mp3"));
            String article =
                    new String(Files.readAllBytes(Paths.get("C:/Test/article_en_guilty.txt")),
                            StandardCharsets.UTF_8);

            HiberMappedPronunciation pron =
                    new HiberMappedPronunciation(
                            "['ɡɪlti]",
                            BlobProxy.generateProxy(audio),
                            ClobProxy.generateProxy(article));

            Transaction trans = session.beginTransaction();
            session.persist(pron);
            trans.commit();

            session.clear();

            trans = session.beginTransaction();

            HiberMappedPronunciation receivedPron = session.find(HiberMappedPronunciation.class, pron.getId());
            Reader charStream = pron.getArticle().getCharacterStream();
            InputStream binaryStream = pron.getAudio().getBinaryStream();

            System.out.println(pron.getArticle().getSubString(1, (int) pron.getArticle().length()));

            trans.commit();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

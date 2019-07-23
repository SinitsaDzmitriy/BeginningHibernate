package edu.hibernate.samples.aveng.entity;

import javax.persistence.*;
import java.sql.Blob;

// ToDo: Decide what we should do when admin deletes card.
/*
        Thoughts
        One picture to many cards.
*/

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    private Long id;

    @Lob
    private Blob image;

    /*
        Contain list of associations (words and phrases related to the bound
image). It will help user to find image for a new card among already available.
The card-side void addPicture(Picture picture) method should also update
association list, add a new one.

        private List<String> associations;
    */




    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    private Card card;
}
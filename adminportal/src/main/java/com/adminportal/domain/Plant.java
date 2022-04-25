    package com.adminportal.domain;

    import lombok.AccessLevel;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.FieldDefaults;

    import javax.persistence.*;

    @Data
    @NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Entity
    public class Plant {

        // Plant_charectarestics
        // movement, nutrition, respiration, sensitivity, reproduction, excretion,and growth.

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
         Long id;
        @Column(name = "botanical_name")
         String botanicalName;
         String name;
         float height;
         String category;
        @Enumerated(EnumType.STRING)
        BloomTime bloomTime;
        @Enumerated(EnumType.STRING)
        Difficulty difficultyLevel;
         double shippingWeight;
         double listPrice;
         double ourPrice;
         boolean active=true;
         // to map db column if property not same as column name
        @Column(columnDefinition="text")
         String description;
         int inStockNumber;

        @Lob
         byte[] plantImage;

    }

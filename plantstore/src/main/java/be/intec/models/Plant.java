    package be.intec.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.*;
    import lombok.experimental.FieldDefaults;
    import org.springframework.web.multipart.MultipartFile;

    import javax.persistence.*;
    import java.util.List;

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
         String botanicalName;
         String name;
         float height;
         String category;
//         int isbn;
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

        // to not persist into DB (just to expose to view/client)
        @Transient
         MultipartFile plantImage;

        // @JsonIgnore is used at field level to mark a property or list of properties to be ignored.
        // @JsonIgnore - to not expose to view (recommended to use in Dto but not in entity class)
        @OneToMany(mappedBy = "plant")
        @JsonIgnore
          List<PlantToCartItem> plantToCartItemList;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public MultipartFile getPlantImage() {
            return plantImage;
        }

        public void setPlantImage(MultipartFile plantImage) {
            this.plantImage = plantImage;
        }

        @Override
        public String toString() {
            return "Plant{" +
                    "id=" + id +
                    ", botanicalName='" + botanicalName + '\'' +
                    ", name='" + name + '\'' +
                    ", height=" + height +
                    ", category='" + category + '\'' +
                    ", bloomTime=" + bloomTime +
                    ", difficultyLevel=" + difficultyLevel +
                    ", shippingWeight=" + shippingWeight +
                    ", listPrice=" + listPrice +
                    ", ourPrice=" + ourPrice +
                    ", active=" + active +
                    ", description='" + description + '\'' +
                    ", inStockNumber=" + inStockNumber +
                    ", plantImage=" + plantImage +
                    ", plantToCartItemList=" + plantToCartItemList +
                    '}';
        }
    }

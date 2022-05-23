    package be.intec.models;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import lombok.AccessLevel;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.FieldDefaults;

    import javax.persistence.*;
    import java.util.List;

    import static java.util.Base64.getMimeEncoder;

    @Data
    @NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Entity
    public class Plant {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        Long id;
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
        boolean active = true;
        // to map db column if property not same as column name
        @Column(columnDefinition = "text")
        String description;
        int inStockNumber;

        @Lob
        byte[] plantImage;

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

        public String getBase64Image() {
            if (plantImage != null) {
                return getMimeEncoder().encodeToString(plantImage);
            } else {
                return "";
            }

        }
    }

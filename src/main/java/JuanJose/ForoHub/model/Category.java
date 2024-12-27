package JuanJose.ForoHub.model;


import JuanJose.ForoHub.dto.Category.CreateCategoryDTO;
import JuanJose.ForoHub.dto.Category.UpdateCategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "category")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories;

    public Category(CreateCategoryDTO data) {
        this.name = data.name();
    }

    public void updateCategory (UpdateCategoryDTO data){
        if(data.name() != null){
            this.name= data.name();
        }
    }
}
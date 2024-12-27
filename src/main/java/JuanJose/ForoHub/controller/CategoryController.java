package JuanJose.ForoHub.controller;

import JuanJose.ForoHub.Service.Category.CategoryService;
import JuanJose.ForoHub.dto.Category.CreateCategoryDTO;
import JuanJose.ForoHub.dto.Category.DeleteCategoryDTO;
import JuanJose.ForoHub.dto.Category.ResponseCategoryDTO;
import JuanJose.ForoHub.dto.Category.UpdateCategoryDTO;
import JuanJose.ForoHub.dto.SubCategory.ResponseSubCategoryDTO;
import JuanJose.ForoHub.model.Category;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //Create Category
    @PostMapping("/categories")
    public ResponseEntity<ResponseCategoryDTO> createCategory(@RequestBody @Valid CreateCategoryDTO data,
                                                              UriComponentsBuilder uriComponentsBuilder) {
        Category category = categoryService.createCategory(data);
        ResponseCategoryDTO dataCategory = new ResponseCategoryDTO(category.getId(), category.getName());
        URI url = uriComponentsBuilder.path("/api/categories/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(url).body(dataCategory);
    }

    //Update category
    @PutMapping("/category/{id}")
    @Transactional
    public ResponseEntity<ResponseCategoryDTO> updateCategory(@PathVariable long id, @RequestBody @Valid UpdateCategoryDTO updateCategoryDTO) {
        ResponseCategoryDTO responseCategoryDTO = categoryService.updateCategory(id, updateCategoryDTO);
        return ResponseEntity.ok().body(responseCategoryDTO);
    }

    //Delete Category
    @DeleteMapping("/category/{id}")
    public ResponseEntity<DeleteCategoryDTO> deleteCategory(@PathVariable Long id) {
        DeleteCategoryDTO response =  categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(response);
    }

    //Get category by id
    @GetMapping("/category/{id}")
    public ResponseEntity<ResponseCategoryDTO> getCategoryById(@PathVariable Long id) {
        ResponseCategoryDTO responseCategoryDTO = categoryService.getById(id);
        return ResponseEntity.ok().body(responseCategoryDTO);
    }

    //Get all Categories {id,name}
    @GetMapping("/categories-list")
    public ResponseEntity<PagedModel<ResponseCategoryDTO>> listCategories(@PageableDefault(size = 10, sort = "name")
                                                                          Pageable pageable,
                                                                          PagedResourcesAssembler assembler) {
        Page<ResponseCategoryDTO> categoryResponseDTOPage = categoryService.getCategoriesPaginated(pageable);
        PagedModel<ResponseCategoryDTO> pagedModel = assembler.toModel(categoryResponseDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    //Get Category and subcategories associated by name ej : {id,name,subcategoryId}
    @GetMapping("/category/{idCategory}/subcategories")
    public ResponseEntity<PagedModel<ResponseSubCategoryDTO>> getSubcategories(@PathVariable Long idCategory,
                                                                               @PageableDefault(sort = "name")
                                                                               Pageable pageable,
                                                                               PagedResourcesAssembler assembler) {
        Page<ResponseSubCategoryDTO> categoryDTOPage = categoryService.getSubcategories(idCategory, pageable);
        PagedModel<ResponseSubCategoryDTO> pagedModel = assembler.toModel(categoryDTOPage);
        return ResponseEntity.ok(pagedModel);
    }
}

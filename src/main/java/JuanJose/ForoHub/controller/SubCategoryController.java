package JuanJose.ForoHub.controller;

import JuanJose.ForoHub.Service.SubCategory.SubCategoryService;
import JuanJose.ForoHub.dto.Course.ResponseCourseDTO;
import JuanJose.ForoHub.dto.SubCategory.*;
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
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    //Create SubCategory
    @PostMapping("/subcategories")
    @Transactional
    public ResponseEntity<DetailsResponseSubCategoryDTO> createSubCategory(@RequestBody @Valid CreateSubCategoryDTO data,
                                                                           UriComponentsBuilder uriComponentsBuilder) {
        DetailsResponseSubCategoryDTO detailsResponseSubCategoryDTO = subCategoryService.createSubcategory(data);
        URI url = uriComponentsBuilder.path("/sub_category/{id}").buildAndExpand(detailsResponseSubCategoryDTO.id()).toUri();
        return ResponseEntity.created(url).body(detailsResponseSubCategoryDTO);
    }

    //Update SubCategory
    @PutMapping("/subcategories/{id}")
    @Transactional
    public ResponseEntity<ResponseSubCategoryDTO> updateSubcategory (@PathVariable Long id,
                                                                   @Valid @RequestBody UpdateSubcategoryDTO data){
        ResponseSubCategoryDTO responseSubCategoryDTO = subCategoryService.updateSubcategory(id,data);
        return  ResponseEntity.ok().body(responseSubCategoryDTO);
    }

    //Delete SubCategory
    @DeleteMapping("/subcategories/{id}")
    public ResponseEntity<DeleteSubCategoryDTO> deleteSubCategory (@PathVariable Long id){
        DeleteSubCategoryDTO response = subCategoryService.deleteSubcategory(id);
        return ResponseEntity.ok().body(response);
    }

    //Get Subcategory by id
    @GetMapping("/sub_categories/{id}")
    public ResponseEntity<DetailsResponseSubCategoryDTO> getSubCategoryById (@PathVariable Long id){
        DetailsResponseSubCategoryDTO subCategoryDTO = subCategoryService.getById(id);
        return ResponseEntity.ok().body(subCategoryDTO);
    }


    //Get all subcategories
    @GetMapping("/subcategories-list")
    public ResponseEntity<PagedModel<ResponseSubCategoryDTO>> listSubcategories(@PageableDefault(size = 10,
            sort = "categoryId") Pageable pageable, PagedResourcesAssembler assembler) {
        Page<ResponseSubCategoryDTO> subCategoryResponseDTOPage = subCategoryService.getAllSubcategories(pageable);
        PagedModel<ResponseSubCategoryDTO> pagedModel = assembler.toModel(subCategoryResponseDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

    //GET /api/subcategories/{subcategoryId}/courses
    @GetMapping("/subcategories/{idSubCategory}/courses")
    public ResponseEntity<PagedModel<ResponseCourseDTO>> getCourses(
            @PathVariable Long idSubCategory,
            @PageableDefault(size = 10, sort = "subCategory.id")
            Pageable pageable, PagedResourcesAssembler assembler) {
    Page<ResponseCourseDTO> courseResponseDTOPage = subCategoryService.getCourses(idSubCategory,pageable);
    PagedModel<ResponseCourseDTO> pagedModel = assembler.toModel(courseResponseDTOPage);
    return ResponseEntity.ok(pagedModel);
    }


}

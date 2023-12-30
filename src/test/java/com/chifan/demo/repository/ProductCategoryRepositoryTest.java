package com.chifan.demo.repository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ProductCategoryRepositoryTest {
//    @Autowired
//    private ProductCategoryRepository repository;
//    @Test
//    public void findOneTest(){
//        ProductCategory productCategory = repository.findById(1).get();
//        System.out.println(productCategory.toString());
//    }
//    @Test
//    @Transactional
//    public void saveTest(){
//        ProductCategory productCategory = new ProductCategory("女生最爱", 3);
//        ProductCategory result = repository.save(productCategory);
//        Assertions.assertNotNull(result);
//        //Assertions.assertNotEquals(null, result);
//    }
//
//    @Test
//    void findByCategoryTypeInTest(){
//        List<Integer> list = Arrays.asList(2,3,4);
//        List<ProductCategory> result =  repository.findByCategoryTypeIn(list);
//        Assertions.assertNotEquals( 0, result.size());
//
//    }
}
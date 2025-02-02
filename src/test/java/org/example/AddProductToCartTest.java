package org.example;

import BasePackage.ChildBase;
import ModulePackage.AddProductToCartModule;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddProductToCartTest extends ChildBase {

    @DataProvider
    public Object[][] data(){
        return new Object[][]{
                {"book"}
        };
    }

    @Test (dataProvider = "data")
    public void addProductToCartTest(String product){
        AddProductToCartModule addProductToCartModule = new AddProductToCartModule();

        addProductToCartModule.searchTheProduct(product);
        addProductToCartModule.addItemToCart();
        addProductToCartModule.verifyIfProductIsAddedToCart();


    }

}

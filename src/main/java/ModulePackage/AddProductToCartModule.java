package ModulePackage;

import BasePackage.ChildBase;
import PoPackage.HomePagePO;
import org.testng.Assert;

public class AddProductToCartModule extends ChildBase {
    HomePagePO homePagePO = new HomePagePO();
    public void searchTheProduct(String product){
        Assert.assertEquals(homePagePO.getTitleOfThePage(),"Electronics, Cars, Fashion, Collectibles & More | eBay");
        homePagePO.searchProduct(product);
        homePagePO.clickOnSearchButton();
    }

    public void addItemToCart(){
        homePagePO.clickOnFirstProductFromResult();
        Assert.assertEquals(homePagePO.getTitleOfThePage(),"Book for sale | eBay");
        pointToNewPageIfExists();
        homePagePO.clickOnAddToCart();
    }

    public void verifyIfProductIsAddedToCart(){
        Assert.assertEquals(homePagePO.getTheCountOfAttemsInTheCart(),"1","Item is added to cart");
    }
}

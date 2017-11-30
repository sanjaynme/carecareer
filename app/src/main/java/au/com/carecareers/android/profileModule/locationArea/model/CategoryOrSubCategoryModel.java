package au.com.carecareers.android.profileModule.locationArea.model;

public class CategoryOrSubCategoryModel {

    private LocationAreaResponse.Location location;
    private LocationAreaResponse.Area area;
    private boolean isCategory;

    public static CategoryOrSubCategoryModel createCategory(LocationAreaResponse.Location location) {
        CategoryOrSubCategoryModel categoryOrSubCategoryModel = new CategoryOrSubCategoryModel();
        categoryOrSubCategoryModel.location = location;
        categoryOrSubCategoryModel.isCategory = true;
        return categoryOrSubCategoryModel;
    }

    public static CategoryOrSubCategoryModel createSubCategory(LocationAreaResponse.Area area) {
        CategoryOrSubCategoryModel categoryOrSubCategoryModel = new CategoryOrSubCategoryModel();
        categoryOrSubCategoryModel.area = area;
        categoryOrSubCategoryModel.isCategory = false;
        return categoryOrSubCategoryModel;
    }

    public LocationAreaResponse.Area getSubCategory() {
        return area;
    }

    public LocationAreaResponse.Location getCategory() {
        return location;
    }

    public boolean isCategory() {
        return isCategory;
    }
}
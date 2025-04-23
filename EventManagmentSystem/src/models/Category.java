package models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String category;

    // Preset Interests
    public static final Category MUSIC = new Category("Music");
    public static final Category TECHNOLOGY = new Category("Technology");
    public static final Category SPORTS = new Category("Sports");
    public static final Category ART = new Category("Art");
    public static final Category EDUCATION = new Category("Education");
    public static final Category BUSINESS = new Category("Business");

    // List of preset interests
    private static final List<Category> predefinedCategories = new ArrayList<>();

    static {
        predefinedCategories.add(MUSIC);
        predefinedCategories.add(TECHNOLOGY);
        predefinedCategories.add(SPORTS);
        predefinedCategories.add(ART);
        predefinedCategories.add(EDUCATION);
        predefinedCategories.add(BUSINESS);
    }

    // Constructor
    public Category(String category) {
        this.category = category;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    // get all interests
    public static List<Category> getPredefinedCategories() {
        return predefinedCategories;
    }

    // Check if an interest exists
    public static boolean isValidCategory(String categoryName) {
        return predefinedCategories.stream()
                .anyMatch(category -> category.getCategory().equalsIgnoreCase(categoryName));
    }

    // Find an interest by name
    public static Category getCategoryByName(String categoryName) {
        for (Category category : predefinedCategories) {
            if (category.getCategory().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
        
    }

    @Override
    public String toString() {
        return category;
    }
}

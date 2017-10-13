package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import generated.tables.records.AccountRecord;
import generated.tables.records.RecipeRecord;

import java.math.BigDecimal;

public class RecipeResource {

    @JsonProperty
    public UserResource chef;

    @JsonProperty
    public String description;

    @JsonProperty
    public String name;

    @JsonProperty
    public int favoriteCount;

    @JsonProperty
    public int servings;

    @JsonProperty
    public int purchasedCount;

    @JsonProperty
    public BigDecimal price;

    @JsonProperty
    public String photo;

    public RecipeResource(AccountRecord chef, RecipeRecord recipe) {
        this.chef = new UserResource(chef);
        this.description = recipe.getDescription();
        this.name = recipe.getName();
        this.favoriteCount = recipe.getFavoritecount();
        this.servings = recipe.getServings();
        this.purchasedCount = recipe.getPurchasedcount();
        this.price = recipe.getPrice();
        this.photo = recipe.getPhoto();
    }
}

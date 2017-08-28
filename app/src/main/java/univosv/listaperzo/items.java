package univosv.listaperzo;

/**
 * Created by root on 07-18-17.
 */

public class items {

    private String title;
    private String CategoryId;
    private String description;


    public items() {
        super();
    }

    public items(String categoryId, String title, String description) {
        super();
        this.title = title;
        this.description = description;

        this.CategoryId = categoryId;
    }


    public String getTitle() {
        return title;
    }

    public void setTittle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategoryId() {
        return CategoryId;
    }


    public void setCategoryId(String categoryId){this.CategoryId = categoryId;}

}

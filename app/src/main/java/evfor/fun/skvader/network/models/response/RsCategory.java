package evfor.fun.skvader.network.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class RsCategory extends RsBase {

    @SerializedName("answer")
    public List<Category> answer;

    public class Category {
        public String category_id;
        public String standart_img;
        public String title;
        public String description;
        public String name;
        public String urlimg;

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Category && category_id != null && ((Category) obj).category_id != null)
                return category_id.equals(((Category) obj).category_id);
            if(obj instanceof String&& category_id != null)
                return category_id.equals(obj);
            return super.equals(obj);
        }
    }
}

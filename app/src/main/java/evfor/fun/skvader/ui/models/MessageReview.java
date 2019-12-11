package evfor.fun.skvader.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageReview implements Parcelable
{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private String rating;
    public final static Parcelable.Creator<MessageReview> CREATOR = new Creator<MessageReview>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MessageReview createFromParcel(Parcel in) {
            return new MessageReview(in);
        }

        public MessageReview[] newArray(int size) {
            return (new MessageReview[size]);
        }

    }
            ;

    protected MessageReview(Parcel in) {
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.review = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MessageReview() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(review);
        dest.writeValue(rating);
    }

    public int describeContents() {
        return 0;
    }

}

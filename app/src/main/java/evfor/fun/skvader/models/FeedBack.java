package evfor.fun.skvader.models;

public class FeedBack {
    public String rating;
    public String review;
    public String eventId;

    public FeedBack() {
    }

    public FeedBack(String rating, String review, String eventId) {
        this.rating = rating;
        this.review = review;
        this.eventId = eventId;
    }
}

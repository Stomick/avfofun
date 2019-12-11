package evfor.fun.skvader.models;

public class UpdatePhoto {
    public static final String delete = "0";
    private String path = delete;

    public UpdatePhoto() {
    }

    public UpdatePhoto(String path) {
        if (path != null)
            this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean delete() {
        return String.valueOf(path).equalsIgnoreCase(delete);
    }

}

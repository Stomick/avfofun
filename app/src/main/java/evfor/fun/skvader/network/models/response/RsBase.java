package evfor.fun.skvader.network.models.response;

import com.google.gson.annotations.SerializedName;

public class RsBase {

    @SerializedName("error")
    public boolean error;
    @SerializedName("message")
    public String message;

    public boolean success(){return error;}
}

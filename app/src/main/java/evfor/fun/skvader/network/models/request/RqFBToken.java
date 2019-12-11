package evfor.fun.skvader.network.models.request;

public class RqFBToken {
    public Token notytoken;

    public RqFBToken(String token) {
        this.notytoken = new Token(token);
    }

    class Token {
        public String token;
        public String device;

        public Token(String token) {
            this.token = token;
            this.device = "android";
        }
    }
}

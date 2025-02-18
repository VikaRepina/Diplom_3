package site.page.object;

import com.google.gson.Gson;
import io.restassured.response.Response;

public class LoginApi {
    private final Gson gson = new Gson();
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public Response authorizationWithExistingLogin(User user) {
        return  ApiBase.getRequestSpecification()
                .body(user)
                .post("/api/auth/login");
    }

    public Response deleteUser(String Token) {
        return  ApiBase.getRequestSpecification()
                .header("Authorization", Token)
                .delete("/api/auth/user");
    }

    public Response createUser (User user) {
        return  ApiBase.getRequestSpecification()
                .body(user)
                .post("/api/auth/register");
    }
}

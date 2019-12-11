package evfor.fun.skvader.utils.social;

import io.reactivex.Maybe;

public interface FieldProfileTask {
     String EMAIL = "email";
     String BDAY = "birthday";
     String FNAME = "first_name";
     String LNAME = "last_name";
     String GENDER = "gender";
     String PHONE = "phone";
     String HTOWN = "hometown";
     String PICTURE = "picture";
     String LOCATION = "location";

    Maybe<String> execute(String field);

    FieldProfileTask empty = field -> Maybe.empty();
}

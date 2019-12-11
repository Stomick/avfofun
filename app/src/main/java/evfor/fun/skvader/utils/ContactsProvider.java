package evfor.fun.skvader.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import io.reactivex.Observable;

public class ContactsProvider {

    private Context context;

    public ContactsProvider(Context context) {
        this.context = context;
    }

    public Observable<Contact> request() {
        return Observable.create(e -> {
            String[] projection = new String[]{
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.CommonDataKinds.Photo.PHOTO_URI,
                    ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME
            };
            try (Cursor c = createCursor(projection)) {
                assert c != null;
                for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                    Contact contact = new Contact(
                            c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME)),
                            c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)),
                            c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI))
                    );
                    if(filterNumberPhone(contact.number))
                        e.onNext(contact);
                }
            } catch (Exception ex) {
                e.onError(ex);
            }
        });
    }

    private Cursor createCursor(String[] projection) {
        ContentResolver cr = context.getContentResolver();
        return cr.query(ContactsContract.Data.CONTENT_URI, projection, null, null, null);
    }

    private boolean filterNumberPhone(String number) {
        return (String.valueOf(number).replaceAll("[\\D]", "").length() > 8);
    }

    public class Contact {
        public String name;
        public String number;
        public String uri;

        public Contact(String name, String number, String uri) {
            this.name = name;
            this.number = number;
            this.uri = uri;
        }
    }

}

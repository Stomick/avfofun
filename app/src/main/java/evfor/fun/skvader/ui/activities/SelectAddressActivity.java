package evfor.fun.skvader.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Location;
import evfor.fun.skvader.mvp.presenters.SelectAddressPresenter;
import evfor.fun.skvader.mvp.views.SelectAddressView;
import evfor.fun.skvader.ui.adapters.AddressAdapter;
import evfor.fun.skvader.models.Address;
import evfor.fun.skvader.ui.utils.RecyclerUtils;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectAddressActivity extends BaseActivity implements SelectAddressView {

    @InjectPresenter
    SelectAddressPresenter presenter;

    static private final int CODE = 14;
    static private final String RESULT = "res";
    static private final String MAP = "map";

    @BindView(R.id.select_address_field)
    EditText selectAddressField;
    @BindView(R.id.select_address_list)
    RecyclerView selectAddressList;
    @BindView(R.id.select_address_map)
    TextView selectAddressMap;
    private AddressAdapter adapter;

    public static void startSelectAddress(Context context) {
        ((AppCompatActivity) context).startActivityForResult(new Intent(context, SelectAddressActivity.class), CODE);
    }

    public static void startSelectAdressWOMap(Context context) {
        ((AppCompatActivity) context).startActivityForResult(new Intent(context, SelectAddressActivity.class).putExtra(MAP, false), CODE);
    }

    @Nullable
    public static Address result(int requestCode, int resultCode, Intent data) {
        if ((resultCode == CODE || requestCode == CODE)
                && data != null
                && data.getExtras() != null
                && data.getExtras().containsKey(RESULT)) {
            Serializable serializable = data.getSerializableExtra(RESULT);
            if (serializable instanceof Address)
                return (Address) serializable;
        }
        return null;
    }

    @Override
    protected int layout() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        adapter = new AddressAdapter();
        RecyclerUtils.setVerticalAdapter(selectAddressList, adapter);
        adapter.setCallBack1(this::selectAddress);
        selectAddressField.setHint(R.string.input_place);
        selectAddressField.addTextChangedListener(textWatcher);
        parseExtras(getIntent().getExtras());
    }

    private void parseExtras(Bundle bundle) {
        if (bundle != null)
            if (bundle.containsKey(MAP))
                selectAddressMap.setVisibility(View.GONE);
    }

    public void selectAddress(Address address) {
        Intent intent = new Intent();
        intent.putExtra(RESULT, address);
        setResult(CODE, intent);
        finish();
    }

    @OnClick(R.id.select_address_close)
    public void onSelectAddressCloseClicked() {
        finish();
    }

    @OnClick(R.id.select_address_location)
    public void onSelectAddressLocationClicked() {
    }

    @OnClick(R.id.select_address_map)
    public void onViewClicked() {
        SelectLocationActivity.open(this);
    }

    @Override
    public void setAddressList(List<Address> addresses) {
        adapter.setAddressList(addresses);
    }

    void onTextChanged(CharSequence sequence) {
        if (sequence.length() > 3) {
            adapter.clear();
            presenter.getPlaceByWord(sequence.toString());
        }
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            SelectAddressActivity.this.onTextChanged(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Location latLng = SelectLocationActivity.result(data);
        if (latLng == null)
            super.onActivityResult(requestCode, resultCode, data);
        else {
            adapter.clear();
            selectAddressField.setText("");
            presenter.getPlace(latLng);
        }
    }
}

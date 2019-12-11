package evfor.fun.skvader.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Address;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddressAdapter extends BaseAdapter<AddressAdapter.ViewHolder>{
    private List<Address> addressList;

    private CallBack1<Address> callBack1;

    public AddressAdapter() {
        this.addressList = new ArrayList<>();
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
        notifyDataSetChanged();
    }

    public void setCallBack1(CallBack1<Address> callBack1) {
        this.callBack1 = callBack1;
    }

    public void addTop(Address address){
        addressList.add(0, address);
        notifyItemInserted(0);
    }

    public void add(Address address) {
        if (this.addressList == null)
            this.addressList = new ArrayList<>();
        addressList.add(address);
        notifyItemInserted(addressList.indexOf(address));
    }

    public void clear() {
        addressList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.address_item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.address_name)
        TextView name;
        @BindView(R.id.address_title)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                if (callBack1 != null)
                    callBack1.call(addressList.get(getAdapterPosition()));
            });
        }

        @Override
        void bind(int pos) {
            name.setText(addressList.get(pos).name);
            title.setText(addressList.get(pos).title);
        }
    }
}

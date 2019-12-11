package evfor.fun.skvader.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.City;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends BaseAdapter<CityAdapter.ViewHolder> {

    private List<City> cities;
    private CallBack1<String> cityCallBack;

    public CityAdapter() {
        this.cities = new ArrayList<>();
    }

    public void setCities(List<City> cities) {
        this.cities.clear();
        this.cities.addAll(cities);
        notifyDataSetChanged();
    }

    public void setCityCallBack(CallBack1<String> cityCallBack) {
        this.cityCallBack = cityCallBack;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.city_item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            textView.setOnClickListener(view -> {
                if(cityCallBack!=null)
                    cityCallBack.call(cities.get(getAdapterPosition()).name);
            });
        }

        @Override
        void bind(int pos) {
            textView.setText(cities.get(pos).name);
        }
    }
}

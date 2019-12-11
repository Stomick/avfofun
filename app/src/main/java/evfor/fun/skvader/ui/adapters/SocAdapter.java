package evfor.fun.skvader.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.ui.models.UiSoc;
import evfor.fun.skvader.utils.callbacks.CallBack1;
import evfor.fun.skvader.utils.callbacks.CallBack2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nonnull;

import butterknife.BindView;

public class SocAdapter extends BaseAdapter<SocAdapter.ViewHolder> {

    private List<UiSoc> socList;
    private CallBack2<CallBack1<String>, UiSoc> setTextCallBack;
    private boolean edit = false;

    private SocAdapter() {
        socList = new ArrayList<>();
    }

    public static SocAdapter showable() {
        return new SocAdapter();
    }

    public static SocAdapter editable() {
        SocAdapter adapter = new SocAdapter();
        adapter.edit = true;
        return adapter;
    }

    public void load(List<UiSoc> socList) {
        this.socList = socList;
        if(!edit)
            clearList();
        notifyDataSetChanged();
    }

    public List<UiSoc> getSocList() {
        return socList;
    }

    public void clearList(){
        Iterator<UiSoc> iterator = socList.iterator();
        while (iterator.hasNext()){
            UiSoc soc = iterator.next();
            if(soc.url.isEmpty())
                iterator.remove();
        }
    }

    public void setSetTextCallBack(CallBack2<CallBack1<String>, UiSoc> setTextCallBack) {
        this.setTextCallBack = setTextCallBack;
    }

    public void add(UiSoc uiSoc) {
        socList.add(uiSoc);
        notifyItemInserted(socList.indexOf(uiSoc));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.my_soc_item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@Nonnull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return socList.size();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.soc_item_image)
        ImageView imageView;
        @BindView(R.id.soc_item_text)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> onClick());
            title.setOnClickListener(v -> onClick());
        }

        private void onClick() {
            if (edit)
                setTextCallBack.call(text -> title.setText(text), socList.get(getAdapterPosition()));
            else socList.get(getAdapterPosition()).open(imageView.getContext());
        }

        @Override
        void bind(int pos) {
            UiSoc soc = socList.get(pos);
            imageView.setImageResource(soc.image_res);
            if (soc.title == null || soc.title.isEmpty()) {
                title.setText("");
                title.setHint(soc.hint_res);
            } else
                title.setText(soc.title);
        }
    }
}

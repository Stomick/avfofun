package evfor.fun.skvader.ui.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.ui.models.UiCheckedItem;
import evfor.fun.skvader.utils.ImageLoader;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static evfor.fun.skvader.ui.utils.RecyclerUtils.createList;

public class InterestingAdapter extends RecyclerView.Adapter<InterestingAdapter.ViewHolder> {

    private List<UiCheckedItem<Category>> list;
    private boolean checkable;
    private CallBack1<String> callBackCategoryId;

    public InterestingAdapter(boolean checkable) {
        this.list = new ArrayList<>();
        this.checkable = checkable;
    }

    public void setList(List<Category> list) {
        if (this.list != null && this.list.isEmpty())
            this.list.addAll(createList(list));
        notifyDataSetChanged();
    }

    public void setCallBackCategoryId(CallBack1<String> callBackCategoryId) {
        this.callBackCategoryId = callBackCategoryId;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interesting_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.interesting_title)
        TextView title;
        @BindView(R.id.interesting_image)
        ImageView image;
        @BindView(R.id.interesting_check)
        View check;
        @BindView(R.id.interesting_filter)
        View filter;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int pos) {
            DisplayMetrics dm = itemView.getContext().getResources().getDisplayMetrics();
            int w = (int) (dm.widthPixels / 2.2f), h = (int) (w * 0.73f);
            image.setLayoutParams(new ConstraintLayout.LayoutParams(w, h));
            Category item = list.get(pos).item;
            setChecked(list.get(pos).checked);
            if (item.name != null)
                title.setText(item.name);
            if (item.logoUrl != null)
                ImageLoader.loadImage(item.logoUrl, image);
        }

        void setChecked(boolean checked) {
            if (checkable)
                if (checked) {
                    check.setVisibility(View.VISIBLE);
                    filter.setVisibility(View.VISIBLE);
                } else {
                    check.setVisibility(View.GONE);
                    filter.setVisibility(View.GONE);
                }
        }

        @OnClick({R.id.interesting_image, R.id.interesting_title, R.id.interesting_filter})
        void onClick() {
            if (checkable) {
                list.get(getAdapterPosition()).checked = !list.get(getAdapterPosition()).checked;
                setChecked(list.get(getAdapterPosition()).checked);
            } else if (callBackCategoryId != null)
                callBackCategoryId.call(list.get(getAdapterPosition()).item.id);
        }
    }

    public List<UiCheckedItem<Category>> getCheckedList() {
        List<UiCheckedItem<Category>> categories = new ArrayList<>();
        for (UiCheckedItem<Category> c : this.list)
            if (c.checked)
                categories.add(c);
        return categories;
    }
}

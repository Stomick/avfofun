package com.team.noty.event.ui.adapters;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.team.noty.event.R;
import com.team.noty.event.models.Category;
import com.team.noty.event.ui.models.UiCheckedItem;
import com.team.noty.event.ui.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditCategoryAdapter extends BaseAdapter<EditCategoryAdapter.ViewHolder> {

    private List<UiCheckedItem<Category>> list;
    List<String> checkedCategory = new ArrayList<>();
    public EditCategoryAdapter(List<String> strings) {
        list = new ArrayList<>();
        checkedCategory = strings;
    }

    public void setList(List<Category> list) {
        this.list = RecyclerUtils.createList(list);
        notifyDataSetChanged();
    }

    public void check(String id){
        for(UiCheckedItem<Category> c:list)
            if(c.item.id.equalsIgnoreCase(id))
                c.checked = true;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.edit_categories_item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.edit_categories_text)
        TextView editCategoriesText;
        @BindView(R.id.edit_categories_box)
        CheckBox checkBox;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this::onCheck);
            checkBox.setOnClickListener(this::onCheck);
            editCategoriesText.setOnClickListener(this::onCheck);
        }

        private void onCheck(View v) {
            list.get(getAdapterPosition()).checked = !list.get(getAdapterPosition()).checked;
            checkBox.setChecked(list.get(getAdapterPosition()).checked);

        }

        @Override
        void bind(int pos) {
            editCategoriesText.setText(list.get(pos).item.name);
            checkBox.setChecked(list.get(pos).checked);
            for(String s : checkedCategory)
            {
                if(list.get(pos).item.id.equals(s))
                    checkBox.setChecked(true);
            }


        }
    }

    public List<String> getCheckedListId() {
        List<String> categories = new ArrayList<>();
        for (UiCheckedItem<Category> c : this.list)
            if (c.checked)
                categories.add(c.item.id);
        return categories;
    }

}

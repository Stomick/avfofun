package evfor.fun.skvader.ui.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Comment;
import evfor.fun.skvader.ui.activities.EmptyActivity;
import evfor.fun.skvader.utils.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class CommentsAdapter extends BaseAdapter<CommentsAdapter.ViewHolder> {


    private List<Comment> comments;

    public CommentsAdapter() {
        comments = new ArrayList<>();
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.comment_item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.cl_main)
        ConstraintLayout clMain;
        @BindView(R.id.commnet_photo)
        CircleImageView commnetPhoto;
        @BindView(R.id.comment_name)
        TextView commentName;
        @BindView(R.id.comment_rating)
        MaterialRatingBar commentRating;
        @BindView(R.id.comment_date)
        TextView commentDate;
        @BindView(R.id.comment_text)
        TextView commentText;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(int pos) {

            Comment comment = comments.get(pos);
            clMain.setOnClickListener(v ->
                    EmptyActivity.startActivityCabinet(v.getContext(), comment.getUserId()));
            ImageLoader.loadImage(comment.getUserAvatar(), commnetPhoto);
            commentName.setText(comment.getUsername());
            Date date = new Date(Long.parseLong(comment.getTime()));
            SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
            String dateText = df2.format(date);
            commentDate.setText(dateText);
            commentText.setText(comment.getMessage());
            commentRating.setRating(Float.parseFloat(comment.getRating()));
        }
    }
}

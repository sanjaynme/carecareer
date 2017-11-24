package au.com.carecareers.android.profileModule.selectAvatar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import au.com.carecareers.android.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */

public class SelectAvatarAdapter extends RecyclerView.Adapter<SelectAvatarAdapter.ViewHolder> {
    public SelectAvatarAdapter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == R.layout.item_select_avatar_right) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_avatar_right, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_avatar_left, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 19;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return R.layout.item_select_avatar_right;
        } else {
            return R.layout.item_select_avatar_left;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements Binder {
        @BindView(R.id.civ_avatar)
        CircleImageView civAvatar;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(int position) {

        }
    }

    interface Binder {
        void bind(int position);
    }
}

package au.com.carecareers.android.profileModule.selectAvatar.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.selectAvatar.model.AvatarResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nischal Manandhar on 23/11/2017.
 */
@ActivityScope
public class SelectAvatarAdapter extends RecyclerView.Adapter<SelectAvatarAdapter.ViewHolder> {
    private List<AvatarResponse.Avatar> avatarList;
    private ItemClickListener listener;

    @Inject
    public SelectAvatarAdapter() {
        this.avatarList = new ArrayList<>();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public void setAvatarList(List<AvatarResponse.Avatar> avatarList) {
        this.avatarList = avatarList;
        notifyDataSetChanged();
    }

    public void addMoreAvatars(List<AvatarResponse.Avatar> avatarList) {
        int positionStart = this.avatarList.size() - 1;
        this.avatarList.addAll(avatarList);
        notifyItemRangeInserted(positionStart, avatarList.size());
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
        return avatarList.size();
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
            Picasso.with(itemView.getContext())
                    .load(avatarList.get(position).getLinks().getUrl().getHref())
                    .resize(200, 200)
                    .centerCrop()
                    .placeholder(R.color.colorGrey700)
                    .into(civAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(avatarList.get(position));
                }
            });
        }
    }

    interface Binder {
        void bind(int position);
    }

    public interface ItemClickListener {
        void onItemClicked(AvatarResponse.Avatar avatar);
    }
}

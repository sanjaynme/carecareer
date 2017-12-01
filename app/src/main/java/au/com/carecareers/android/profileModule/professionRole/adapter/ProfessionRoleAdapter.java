package au.com.carecareers.android.profileModule.professionRole.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import au.com.carecareers.android.R;
import au.com.carecareers.android.injection.scope.ActivityScope;
import au.com.carecareers.android.profileModule.professionRole.model.ProfessionRoleResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */
@ActivityScope
public class ProfessionRoleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private List<ProfessionRoleResponse.Profession> originalList;
    private List<ProfessionRoleResponse.Profession> filteredList;
    private ItemFilter filter = new ItemFilter();
    private Listener listener;

    @Inject
    public ProfessionRoleAdapter() {
        this.originalList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    public void setProfessionRoleList(List<ProfessionRoleResponse.Profession> listProfession) {
        this.filteredList = listProfession;
        this.originalList = this.filteredList;
        notifyDataSetChanged();
    }

    /**
     * @param roleList list of previously selected area list
     */
    public void persistCheckedList(List<ProfessionRoleResponse.Role> roleList) {
        for (ProfessionRoleResponse.Profession profession : filteredList) {
            for (ProfessionRoleResponse.Role role : profession.getEmbedded().getRoles()) {
                for (ProfessionRoleResponse.Role persistedRole : roleList) {
                    if (role.getId().equals(persistedRole.getId())) {
                        role.setChecked(persistedRole.isChecked());
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public List<ProfessionRoleResponse.Role> getCheckedItems() {
        List<ProfessionRoleResponse.Role> listSelectedRoles = new ArrayList<>();
        for (ProfessionRoleResponse.Profession profession : filteredList) {
            for (ProfessionRoleResponse.Role role : profession.getEmbedded().getRoles()) {
                if (role.isChecked()) {
                    listSelectedRoles.add(role);
                }
            }
        }
        return listSelectedRoles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == R.layout.item_category) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            return new ItemHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer_progress, parent, false);
            return new FooterViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return filteredList.get(position).getId() != -1 ? R.layout.item_category : R.layout.item_footer_progress;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).bind(position);
        } else {
            ((FooterViewHolder) holder).bind(position);
        }
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    interface Binder {
        void bind(int position);
    }

    public interface Listener {
        void onCheckedChanged();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements Binder {
        @BindView(R.id.ll_item_category)
        LinearLayout llItemCategory;
        @BindView(R.id.ll_sub_category)
        LinearLayout llSubCategory;

        @BindView(R.id.tv_category_title)
        TextView tvCategoryTitle;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(int position) {
            tvCategoryTitle.setText(filteredList.get(position).getName());
            List<ProfessionRoleResponse.Role> listRole = filteredList.get(position).getEmbedded().getRoles();
            llSubCategory.removeAllViews();
            if (listRole != null && !listRole.isEmpty()) {
                for (ProfessionRoleResponse.Role role : listRole) {
                    LinearLayout subCategory = (LinearLayout) LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_sub_category, llSubCategory, false);
                    CheckBox cbSubCategory = subCategory.findViewById(R.id.cb_sub_category);
                    cbSubCategory.setText(role.getName());
                    cbSubCategory.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        role.setChecked(buttonView.isChecked());
                        if (listener != null) {
                            listener.onCheckedChanged();
                        }
                    });
                    cbSubCategory.setChecked(role.isChecked());
                    llSubCategory.addView(subCategory);
                }
            }
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder implements Binder {
        @BindView(R.id.pb_li_footer)
        ProgressBar pbFooter;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(int position) {
            pbFooter.setIndeterminate(true);
        }
    }

    class ItemFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults filterResults = new FilterResults();
            List<ProfessionRoleResponse.Profession> tempFilterList = new ArrayList<>(originalList.size());
            if (filterString.isEmpty()) {
                tempFilterList.addAll(originalList);
            } else {
                for (ProfessionRoleResponse.Profession profession : originalList) {
                    List<ProfessionRoleResponse.Role> rolesList = profession.getEmbedded().getRoles();
                    List<ProfessionRoleResponse.Role> tempRoleList = new ArrayList<>();
                    for (ProfessionRoleResponse.Role role : rolesList) {
                        String filterable = role.getName().toLowerCase();
                        if (filterable.startsWith(filterString)) {
                            tempRoleList.add(role);
                        }
                    }
                    if (tempRoleList.size() > 0) {
                        ProfessionRoleResponse.Profession tempProfession = new ProfessionRoleResponse.Profession();
                        tempProfession.setId(profession.getId());
                        tempProfession.setName(profession.getName());
                        tempProfession.setActive(profession.getActive());
                        tempProfession.setLinks(profession.getLinks());
                        tempProfession.setPosition(profession.getPosition());
                        tempProfession.setSlug(profession.getSlug());
                        tempProfession.setEmbedded(new ProfessionRoleResponse.EmbeddedRole());
                        tempProfession.getEmbedded().setRoles(tempRoleList);
                        tempFilterList.add(tempProfession);
                    }
                }
            }

            filterResults.values = tempFilterList;
            filterResults.count = tempFilterList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<ProfessionRoleResponse.Profession>) results.values;
            notifyDataSetChanged();
        }
    }
}

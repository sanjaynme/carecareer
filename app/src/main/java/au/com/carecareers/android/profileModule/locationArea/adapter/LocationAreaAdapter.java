package au.com.carecareers.android.profileModule.locationArea.adapter;

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
import au.com.carecareers.android.profileModule.locationArea.model.LocationAreaResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nischal Manandhar on 28/11/2017.
 */
@ActivityScope
public class LocationAreaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private List<LocationAreaResponse.Location> originalList;
    private List<LocationAreaResponse.Location> filteredList;
    private LocationAreaFilter filter = new LocationAreaFilter();

    @Inject
    public LocationAreaAdapter() {
        this.originalList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    public void setListLocation(List<LocationAreaResponse.Location> listLocation) {
        this.filteredList = listLocation;
        this.originalList = this.filteredList;
        notifyDataSetChanged();
    }

    public void showFooterProgress() {
        LocationAreaResponse.Location location = new LocationAreaResponse.Location();
        location.setId(-1);
        this.filteredList.add(location);
        notifyItemInserted(filteredList.size());
    }

    public void removeFooterProgress() {
        this.filteredList.remove(filteredList.size() - 1);
        notifyItemRemoved(filteredList.size());
    }

    public void addMoreItems(List<LocationAreaResponse.Location> listLocation) {
        int lastPosition = filteredList.size() - 1;
        this.filteredList.addAll(listLocation);
        this.originalList = this.filteredList;
        notifyItemRangeInserted(lastPosition + 1, listLocation.size());
    }

    public List<LocationAreaResponse.Area> getCheckedItems() {
        List<LocationAreaResponse.Area> listSelectedAreas = new ArrayList<>();
        for (LocationAreaResponse.Location location : filteredList) {
            for (LocationAreaResponse.Area area : location.getEmbedded().getAreas()) {
                if (area.isChecked()) {
                    listSelectedAreas.add(area);
                }
            }
        }
        return listSelectedAreas;
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
            List<LocationAreaResponse.Area> listArea = filteredList.get(position).getEmbedded().getAreas();
            llSubCategory.removeAllViews();
            if (listArea != null && !listArea.isEmpty()) {
                for (LocationAreaResponse.Area area : listArea) {
                    LinearLayout subCategory = (LinearLayout) LayoutInflater.from(itemView.getContext()).inflate(R.layout.item_sub_category, llSubCategory, false);
                    CheckBox cbSubCategory = subCategory.findViewById(R.id.cb_sub_category);
                    cbSubCategory.setText(area.getName());
                    cbSubCategory.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        area.setChecked(buttonView.isChecked());
                    });
                    cbSubCategory.setChecked(area.isChecked());
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

    class LocationAreaFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults filterResults = new FilterResults();
            List<LocationAreaResponse.Location> tempOriginalList = originalList;
            List<LocationAreaResponse.Location> tempFilterList = new ArrayList<>(tempOriginalList.size());
            if (filterString.isEmpty()) {
                tempFilterList.addAll(tempOriginalList);
            } else {
                for (LocationAreaResponse.Location location : tempOriginalList) {
                    List<LocationAreaResponse.Area> listAreas = location.getEmbedded().getAreas();
                    List<LocationAreaResponse.Area> tempAreaList = new ArrayList<>();
                    for (LocationAreaResponse.Area area : listAreas) {
                        String filterable = area.getName().toLowerCase();
                        if (filterable.startsWith(filterString)) {
                            tempAreaList.add(area);
                        }
                    }
                    if (tempAreaList.size() > 0) {
                        LocationAreaResponse.Location tempLocation = new LocationAreaResponse.Location();
                        tempLocation.setId(location.getId());
                        tempLocation.setName(location.getName());
                        tempLocation.setCountryId(location.getCountryId());
                        tempLocation.setActive(location.getActive());
                        tempLocation.setLinks(location.getLinks());
                        tempLocation.setPosition(location.getPosition());
                        tempLocation.setEmbedded(new LocationAreaResponse.EmbeddedArea());
                        tempLocation.getEmbedded().setAreas(tempAreaList);
                        tempFilterList.add(tempLocation);
                    }
                }
            }

            filterResults.values = tempFilterList;
            filterResults.count = tempFilterList.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<LocationAreaResponse.Location>) results.values;
            notifyDataSetChanged();
        }
    }

    interface Binder {
        void bind(int position);
    }
}

package au.com.carecareers.android.profileModule.locationArea.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
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
public class LocationAreaAdapter extends RecyclerView.Adapter<LocationAreaAdapter.ViewHolder> implements Filterable {
    private List<LocationAreaResponse.Location> originalList;
    private List<LocationAreaResponse.Location> filteredList;
    private LocationAreaFilter filter = new LocationAreaFilter();

    @Inject
    public LocationAreaAdapter() {
        this.originalList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    public void setListLocation(List<LocationAreaResponse.Location> listLocation) {
        this.originalList = listLocation;
        this.filteredList = listLocation;
        notifyDataSetChanged();
    }

    @Override
    public LocationAreaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(LocationAreaAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements Binder {
        @BindView(R.id.ll_item_category)
        LinearLayout llItemCategory;
        @BindView(R.id.ll_sub_category)
        LinearLayout llSubCategory;

        @BindView(R.id.tv_category_title)
        TextView tvCategoryTitle;

        public ViewHolder(View itemView) {
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
                    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    TextView tv = new TextView(itemView.getContext());
                    tv.setLayoutParams(lparams);
                    tv.setText(area.getName());
                    llSubCategory.addView(tv);
                }
            }
        }
    }

    class LocationAreaFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults filterResults = new FilterResults();
            //Todo fix filterable in location/area
            List<LocationAreaResponse.Location> tempOriginalList = originalList;
            List<LocationAreaResponse.Location> tempFilterList = new ArrayList<>(tempOriginalList.size());
            for (LocationAreaResponse.Location location : tempOriginalList) {
                List<LocationAreaResponse.Area> tempAreaList = null;
                for (LocationAreaResponse.Area area : location.getEmbedded().getAreas()) {
                    tempAreaList = new ArrayList<>();
                    String filterable = area.getName().toLowerCase();
                    if (filterable.contains(filterString)) {
                        tempAreaList.add(area);
                    }
                }
                if (tempAreaList != null) {
                    location.getEmbedded().setAreas(tempAreaList);
                }
                tempFilterList.add(location);
            }
            /*for (int i = 0; i < tempOriginalList.size(); i++) {
                List<LocationAreaResponse.Area> tempAreaList = new ArrayList<>();
                for (int j = 0; j < tempOriginalList.get(i).getEmbedded().getAreas().size(); j++) {
                    String filterableString = tempOriginalList.get(i).getEmbedded().getAreas().get(j).getName().toLowerCase();
                    if (filterableString.contains(filterString)) {
                        tempAreaList.add(tempOriginalList.get(i).getEmbedded().getAreas().get(j));
                    }
                }
                LocationAreaResponse.Location location = new LocationAreaResponse.Location();
                location.setName(tempOriginalList.get(i).getName());
                location.setEmbedded(tempOriginalList.get(i).getEmbedded());
                location.getEmbedded().setAreas(tempAreaList);
                tempFilterList.add(location);
            }*/

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

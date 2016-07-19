package co.herdy.manager.domain.dashboardfeature.model.callback;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;

import co.herdy.manager.domain.dashboardfeature.model.Animal;

public class AnimalSortedListAdapterCallBack extends SortedListAdapterCallback<Animal> {


    public AnimalSortedListAdapterCallBack(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    // Should compare two objects and return how they should be ordered.
    @Override
    public int compare(Animal o1, Animal o2) {
        // Compare local id
        if (o1.getId() < o2.getId()) {
            return -1;
        } else if (o1.getId() > o2.getId()) {
            return 1;
        }
        return 0;
    }

    // Called by the SortedList when it wants to check whether two items have the same data or not.
    @Override
    public boolean areContentsTheSame(Animal oldItem, Animal newItem) {
        // Compare titles
        return oldItem.getName().equals(newItem.getName());
    }

    // Called by the SortedList to decide whether two object represent the same Item or not.
    @Override
    public boolean areItemsTheSame(Animal item1, Animal item2) {
        // Compare remote id
        return item1.getId() == item2.getId();
    }
}

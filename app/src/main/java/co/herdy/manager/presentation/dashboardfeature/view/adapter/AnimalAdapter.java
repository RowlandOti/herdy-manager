package co.herdy.manager.presentation.dashboardfeature.view.adapter;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.BuildConfig;
import co.herdy.manager.R;
import co.herdy.manager.domain.dashboardfeature.model.Animal;
import co.herdy.manager.domain.dashboardfeature.model.callback.AnimalSortedListAdapterCallBack;

public class AnimalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The class Log identifier
    private static final String LOG_TAG = AnimalAdapter.class.getSimpleName();
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private SortedList<Animal> mAnimalList;

    public AnimalAdapter(List<Animal> animalList) {
        addAll(animalList);
    }

    // Called when RecyclerView needs a new AnimalViewHolder of the given type to represent an item.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_animal, parent, false);
            return new AnimalViewHolder(v);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_animal_header, parent, false);
            return new AnimalHeaderViewHolder(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AnimalViewHolder) {
            final Animal movie = mAnimalList.get(position);
            ((AnimalViewHolder) holder).bindTo(movie, position);
        } else if (holder instanceof AnimalHeaderViewHolder) {

        }
    }

    // What's the size of the movie List
    @Override
    public int getItemCount() {
        if (mAnimalList != null) {
            if (BuildConfig.IS_DEBUG_MODE) {
                Log.d(LOG_TAG, "List Count: " + mAnimalList.size());
            }
            return mAnimalList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    // Handy method for passing the list to the adapter
    public void addAll(List<Animal> animalList) {
        if (animalList != null) {
            if (mAnimalList == null) {
                mAnimalList = new SortedList<>(Animal.class, new AnimalSortedListAdapterCallBack(this));
            }
            mAnimalList.beginBatchedUpdates();
            for (Animal animal : animalList) {
                mAnimalList.add(animal);
            }
            mAnimalList.endBatchedUpdates();
        }
    }

    // Takes care of the overhead of recycling and gives better performance and scrolling
    public class AnimalViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.txt_name)
        TextView mNameTextView;

        @Bind(R.id.txt_date)
        TextView mDateTextView;

        @Bind(R.id.txt_hatchno)
        TextView mHatchNoTextView;

        public AnimalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        // Bind the data to the holder views
        private void bindTo(final Animal animal, final int position) {
            mNameTextView.setText(animal.getName());
            mDateTextView.setText(animal.getDate());
            mHatchNoTextView.setText(animal.getHatchno());
        }
    }

    // Takes care of the overhead of recycling and gives better performance and scrolling
    public class AnimalHeaderViewHolder extends RecyclerView.ViewHolder {
        public AnimalHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

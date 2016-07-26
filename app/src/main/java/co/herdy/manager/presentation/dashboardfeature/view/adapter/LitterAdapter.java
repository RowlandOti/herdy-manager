package co.herdy.manager.presentation.dashboardfeature.view.adapter;

import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import co.herdy.manager.BuildConfig;
import co.herdy.manager.R;
import co.herdy.manager.domain.dashboardfeature.model.Animal;
import co.herdy.manager.domain.dashboardfeature.model.callback.AnimalSortedListAdapterCallBack;

public class LitterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The class Log identifier
    private static final String LOG_TAG = LitterAdapter.class.getSimpleName();
    // Determine type of View
    private static final int TYPE_HEADER = 1;
    private static final int TYPE_ITEM = 0;

    private SortedList<Animal> mLitterList;

    public LitterAdapter(List<Animal> animalList) {
        addAll(animalList);
    }

    // Called when RecyclerView needs a new LitterViewHolder of the given type to represent an item.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_litter, parent, false);
            return new LitterViewHolder(v);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_litter_header, parent, false);
            return new LitterHeaderViewHolder(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LitterViewHolder) {
            final Animal movie = mLitterList.get(position);
            ((LitterViewHolder) holder).bindTo(movie);
        } else if (holder instanceof LitterHeaderViewHolder) {

        }
    }

    // What's the size of the movie List
    @Override
    public int getItemCount() {
        if (mLitterList != null) {
            if (BuildConfig.IS_DEBUG_MODE) {
                Log.d(LOG_TAG, "List Count: " + mLitterList.size());
            }
            return mLitterList.size();
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
            if (mLitterList == null) {
                mLitterList = new SortedList<>(Animal.class, new AnimalSortedListAdapterCallBack(this));
            }
            mLitterList.beginBatchedUpdates();
            for (Animal animal : animalList) {
                mLitterList.add(animal);
            }
            mLitterList.endBatchedUpdates();
        }
    }

    // Takes care of the overhead of recycling and gives better performance and scrolling
    public class LitterViewHolder extends RecyclerView.ViewHolder {

        Calendar mCalendar;

        @Bind(R.id.txt_name)
        TextView mNameTextView;

        @Bind(R.id.txt_hatchno)
        TextView mHatchNoTextView;

        @Bind(R.id.txt_weaned)
        TextView mWeanedTextView;

        @Bind(R.id.txt_date)
        TextView mDateTextView;

        public LitterViewHolder(View itemView) {
            super(itemView);
            mCalendar = Calendar.getInstance();
            ButterKnife.bind(this, itemView);
        }

        // Bind the data to the holder views
        private void bindTo(final Animal animal) {
            mCalendar.setTime(animal.getDate());
            String format = new SimpleDateFormat("EEE, d MMMM yyyy", Locale.ENGLISH).format(animal.getDate());

            mNameTextView.setText(animal.getName());
            mHatchNoTextView.setText(animal.getHatchno());
            mWeanedTextView.setText(String.valueOf(animal.getWeaned()));
            mDateTextView.setText(format);
        }
    }

    // Takes care of the overhead of recycling and gives better performance and scrolling
    public class LitterHeaderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_header_one)
        TextView mHeaderTextViewOne;

        @Bind(R.id.tv_header_two)
        TextView mHeaderTextViewTwo;

        @Bind(R.id.tv_header_three)
        TextView mHeaderTextViewThree;

        @Bind(R.id.tv_header_four)
        TextView mHeaderTextViewFour;

        public LitterHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

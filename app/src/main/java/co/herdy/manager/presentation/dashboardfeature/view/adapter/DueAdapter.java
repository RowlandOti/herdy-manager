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

public class DueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The class Log identifier
    private static final String LOG_TAG = DueAdapter.class.getSimpleName();
    // Determine type of View
    private static final int TYPE_HEADER =1;
    private static final int TYPE_ITEM = 0;
    // Determine Header type
    public static final String TYPE_HEADER_TYPE = "header_type";
    public static final int TYPE_HEADER_ANIMAL = 10;
    public static final int TYPE_HEADER_LITTER = 11;

    private int mHeaderType;

    private SortedList<Animal> mAnimalList;

    public DueAdapter(List<Animal> animalList, int headerType) {
        this.mHeaderType = headerType;
        addAll(animalList);
    }

    // Called when RecyclerView needs a new DueViewHolder of the given type to represent an item.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_animal, parent, false);
            return new DueViewHolder(v);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_animal_header, parent, false);
            return new DueHeaderViewHolder(v);
        }
        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DueViewHolder) {
            final Animal movie = mAnimalList.get(position);
            ((DueViewHolder) holder).bindTo(movie);
        } else if (holder instanceof DueHeaderViewHolder) {
            ((DueHeaderViewHolder) holder).toggleHeader();
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
    public class DueViewHolder extends RecyclerView.ViewHolder {

        Calendar mCalendar;

        @Bind(R.id.txt_name)
        TextView mNameTextView;

        @Bind(R.id.txt_date)
        TextView mDateTextView;

        @Bind(R.id.txt_hatchno)
        TextView mHatchNoTextView;

        public DueViewHolder(View itemView) {
            super(itemView);
            mCalendar = Calendar.getInstance();
            ButterKnife.bind(this, itemView);
        }

        // Bind the data to the holder views
        private void bindTo(final Animal animal) {
            mCalendar.setTime(animal.getDate());
            String format = new SimpleDateFormat("EEE, d MMMM yyyy", Locale.ENGLISH).format(animal.getDate());

            mNameTextView.setText(animal.getName());
            mDateTextView.setText(format);
            mHatchNoTextView.setText(animal.getHatchno());
        }
    }

    // Takes care of the overhead of recycling and gives better performance and scrolling
    public class DueHeaderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_header_one)
        TextView mHeaderTextViewOne;

        @Bind(R.id.tv_header_two)
        TextView mHeaderTextViewTwo;

        @Bind(R.id.tv_header_three)
        TextView mHeaderTextViewThree;

        public DueHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void toggleHeader() {
            if (mHeaderType == TYPE_HEADER_LITTER) {
                mHeaderTextViewTwo.setText("LITTER");
            }
            if (mHeaderType == TYPE_HEADER_ANIMAL) {
                mHeaderTextViewTwo.setText("ANIMAL");
            }

        }
    }
}

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

public class AnimalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // The class Log identifier
    private static final String LOG_TAG = AnimalAdapter.class.getSimpleName();
    // Determine type of View
    private static final int TYPE_ITEM = 0;

    private SortedList<Animal> mAnimalList;

    public AnimalAdapter(List<Animal> animalList) {
        addAll(animalList);
    }

    // Called when RecyclerView needs a new AnimalViewHolder of the given type to represent an item.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_animal, parent, false);
        return new AnimalViewHolder(v);
    }

    // Called by RecyclerView to display the data at the specified position.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AnimalViewHolder) {
            final Animal movie = mAnimalList.get(position);
            ((AnimalViewHolder) holder).bindTo(movie);
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

        Calendar mCalendar;

        @Bind(R.id.txt_name_value)
        TextView mNameTextView;

        @Bind(R.id.txt_date_value)
        TextView mDateTextView;

        @Bind(R.id.txt_hatchno_value)
        TextView mHatchNoTextView;

        @Bind(R.id.txt_sex_value)
        TextView mSexTextView;

        @Bind(R.id.txt_breed_value)
        TextView mBreedTextView;

        @Bind(R.id.txt_status_value)
        TextView mStatusTextView;

        public AnimalViewHolder(View itemView) {
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
            mSexTextView.setText(animal.getSex());
            mBreedTextView.setText(animal.getBreed());
            mStatusTextView.setText(animal.getStatus());
        }
    }

    // Takes care of the overhead of recycling and gives better performance and scrolling
    public class AnimalHeaderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_header_one)
        TextView mHeaderTextViewOne;

        @Bind(R.id.tv_header_two)
        TextView mHeaderTextViewTwo;

        @Bind(R.id.tv_header_three)
        TextView mHeaderTextViewThree;

        @Bind(R.id.tv_header_four)
        TextView mHeaderTextViewFour;

        @Bind(R.id.tv_header_five)
        TextView mHeaderTextViewFive;

        @Bind(R.id.tv_header_six)
        TextView mHeaderTextViewSix;

        public AnimalHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

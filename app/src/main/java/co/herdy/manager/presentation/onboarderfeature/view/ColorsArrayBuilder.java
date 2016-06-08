package co.herdy.manager.presentation.onboarderfeature.view;


import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import co.herdy.manager.presentation.onboarderfeature.view.fragment.OnBoarder;

public class ColorsArrayBuilder {

    public static Integer[] getPageBackgroundColors(Context context, List<OnBoarder> onBoarderPages) {
        List<Integer> colorsList = new ArrayList<>();
        for (OnBoarder onBoarder : onBoarderPages) {
            colorsList.add(ContextCompat.getColor(context, onBoarder.getBackgroundColor()));
        }
        return colorsList.toArray(new Integer[onBoarderPages.size()]);
    }

}

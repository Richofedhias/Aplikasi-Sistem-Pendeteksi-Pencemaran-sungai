package com.example.sistempendeteksi;


import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class Uitest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);


    @Test
    public void test() throws InterruptedException{
        onView(withId(R.id.card1)).perform(click());
        Thread.sleep(3000);
        Espresso.pressBack();
        onView(withId(R.id.card2)).perform(click());
        Thread.sleep(3000);
        Espresso.pressBack();
        onView(withId(R.id.card3)).perform(click());
        Thread.sleep(3000);
        Espresso.pressBack();
        onView(withId(R.id.btn_history)).perform(click());
        Thread.sleep(3000);
        Espresso.pressBack();

    }
}

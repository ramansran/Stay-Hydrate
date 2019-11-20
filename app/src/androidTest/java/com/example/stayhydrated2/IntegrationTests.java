package com.example.stayhydrated2;


import android.app.Application;
import android.app.Instrumentation;
import android.app.admin.DelegatedAdminReceiver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.provider.OpenableColumns;
import android.widget.ListView;
import android.widget.ProgressBar;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.os.Looper.getMainLooper;
import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.setFailureHandler;
import static androidx.test.espresso.action.ViewActions.actionWithAssertions;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class IntegrationTests {
    // Define the activity to be tested
    @Rule
    public ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);




    @BeforeClass
    public static   void setUp() throws InterruptedException {
      ApplicationProvider.getApplicationContext().deleteDatabase("StayHydreateDatabase3");
    }
    @After
    public  void tearDown() throws InterruptedException {
        activity.getActivity().deleteDatabase("StayHydreateDatabase3");
    }

    String expected_timeStamp;

    @Test
    public void matchQuantity1() {

        onView(withId(R.id.droplet)).perform(click());

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.quantity))  // Select the view historyDataText in the element
                .check(matches(withText("50ml")));  // Assertion
    }







    @Test
    public void matchQuantity2() {
        onView(withId(R.id.glass)).perform(click());

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.quantity))  // Select the view historyDataText in the element
                .check(matches(withText("200ml")));  // Assertion
    }




    @Test
    public void matchTimeStamp1() {
        onView(withId(R.id.bottle)).perform(click());

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                expected_timeStamp = new SimpleDateFormat("HH:mm - dd/MM", Locale.US).format(new Date());
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.quantity))  // Select the view historyDataText in the element
                .check(matches(withText("350ml"))) ; // Assertion

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.Dates))  // Select the view historyDataText in the element
                .check(matches(withText(expected_timeStamp))) ; // Assertion

    }




    @Test
    public void matchTimeStamp2()  {
        onView(withId(R.id.droplet)).perform(click());

        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                expected_timeStamp = new SimpleDateFormat("HH:mm - dd/MM", Locale.US).format(new Date());
                someHandler.postDelayed(this, 1000);
            }
        }, 10);

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.quantity))  // Select the view historyDataText in the element
                .check(matches(withText("50ml"))) ; // Assertion

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .onChildView(withId(R.id.Dates))  // Select the view historyDataText in the element
                .check(matches(withText(expected_timeStamp))) ; // Assertion

    }




    @Test
    public void progessBarTest1() {
        onView(withId(R.id.glass)).perform(click());

        ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
        assertThat(progressBar.getProgress(), equalTo(200));
    }




    @Test
    public void progessBarTest2() {
        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.bottle)).perform(click());

        onView(withId(R.id.droplet)).perform(click());

        onView(withId(R.id.droplet)).perform(click());

        ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
        assertThat(progressBar.getProgress(), equalTo(2500));
    }



    @Test
    public void doubleClickTest1() {

        onView(withId(R.id.bottle)).perform(click());

        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0)  // Select the element at index 0
                .perform(click()).perform(click());

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ListView listk = activity.getActivity().findViewById(R.id.list);
        assertThat(listk.getCount(),equalTo(0));

        ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
        assertThat(progressBar.getProgress(), equalTo(0));
    }





    @Test
    public void doubleClickTest2() {

        onView(withId(R.id.droplet)).perform(click());
        onView(withId(R.id.glass)).perform(click());
        onData(anything()) // Select all elements in a list
                .inAdapterView(withId(R.id.list)) // Only in the historyListViewAdapter
                .atPosition(0).perform(click()).perform(click());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ListView listk = activity.getActivity().findViewById(R.id.list);
        assertThat(listk.getCount(),equalTo(1));

        ProgressBar progressBar = activity.getActivity().findViewById(R.id.progressBar);
        assertThat(progressBar.getProgress(), equalTo(200));
    }





    @Test
    public void ExampleAssertListIsEmpty(){

        ListView listView = activity.getActivity().findViewById(R.id.list);
        assertThat(listView.getCount(), equalTo(0));
    }




    @Test
    public void ExampleTestImage(){
        onView(withId(R.id.droplet)).perform(click());

        onData(anything())
                .inAdapterView(withId(R.id.list))
                .atPosition(0)
                .onChildView(withId(R.id.icon))
                .check(matches(withTagValue(Matchers.<Object>equalTo(R.drawable.icons8_water_droplet))));
    }






}

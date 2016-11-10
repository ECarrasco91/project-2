package com.ezequielc.moviesforsale;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.movie_list), withText("Dazed and Confused"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.add_to_cart_button), withText("Add to Cart"), isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.search), withContentDescription("Search"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction searchAutoComplete = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete.perform(replaceText("action"), closeSoftKeyboard());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.movie_list), withText("Dark Knight"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add_to_cart_button), withText("Add to Cart"), isDisplayed()));
        appCompatButton2.perform(click());

        pressBack();

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.search), withContentDescription("Search"), isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction searchAutoComplete2 = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete2.perform(replaceText("comedy"), closeSoftKeyboard());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.movie_list), withText("Clerks"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.add_to_cart_button), withText("Add to Cart"), isDisplayed()));
        appCompatButton3.perform(click());

        pressBack();

        ViewInteraction actionMenuItemView3 = onView(
                allOf(withId(R.id.search), withContentDescription("Search"), isDisplayed()));
        actionMenuItemView3.perform(click());

        ViewInteraction searchAutoComplete3 = onView(
                allOf(withId(R.id.search_src_text),
                        withParent(allOf(withId(R.id.search_plate),
                                withParent(withId(R.id.search_edit_frame)))),
                        isDisplayed()));
        searchAutoComplete3.perform(replaceText("house on"), closeSoftKeyboard());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.movie_list), withText("House on Haunted Hill"), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.add_to_cart_button), withText("Add to Cart"), isDisplayed()));
        appCompatButton4.perform(click());

        pressBack();

        ViewInteraction actionMenuItemView4 = onView(
                allOf(withId(R.id.shopping_cart), withContentDescription("Shopping Cart"), isDisplayed()));
        actionMenuItemView4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.display_total), withText("Total: $74.96"),
                        childAtPosition(
                                allOf(withId(R.id.activity_shopping_cart),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("Total: $74.96")));

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.remove_button), withText("Remove"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.checkout_button), withText("Checkout"),
                        withParent(withId(R.id.activity_shopping_cart))));
        appCompatButton6.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView5 = onView(
                allOf(withId(R.id.shopping_cart), withContentDescription("Shopping Cart"), isDisplayed()));
        actionMenuItemView5.perform(click());

        pressBack();

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

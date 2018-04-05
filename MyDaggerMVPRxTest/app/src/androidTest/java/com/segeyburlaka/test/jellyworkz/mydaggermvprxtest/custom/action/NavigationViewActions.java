package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.custom.action;

import android.content.res.Resources.NotFoundException;
import android.support.design.widget.NavigationView;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static org.hamcrest.Matchers.allOf;

/**
 * View actions for interacting with {@link NavigationView}
 */
public final class NavigationViewActions {

    private NavigationViewActions() {
        // no Instance
    }

    /**
     * @param menuItemId the resource id of the menu item
     * @return a {@link ViewAction} that navigates on a menu item
     */
    public static ViewAction navigateTo(final int menuItemId) {

        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                NavigationView navigationView = (NavigationView) view;
                Menu menu = navigationView.getMenu();
                if (null == menu.findItem(menuItemId)) {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(new RuntimeException(getErrorMessage(menu, view)))
                            .build();
                }
                menu.performIdentifierAction(menuItemId, 0);
                uiController.loopMainThreadUntilIdle();
            }

            private String getErrorMessage(Menu menu, View view) {
                String NEW_LINE = System.getProperty("line.separator");
                StringBuilder errorMessage = new StringBuilder("Menu item was not found, "
                        + "available menu items:")
                        .append(NEW_LINE);
                for (int position = 0; position < menu.size(); position++) {
                    errorMessage.append("[MenuItem] position=")
                            .append(position);
                    MenuItem menuItem = menu.getItem(position);
                    if (menuItem != null) {
                        CharSequence itemTitle = menuItem.getTitle();
                        if (itemTitle != null) {
                            errorMessage.append(", title=")
                                    .append(itemTitle);
                        }
                        if (view.getResources() != null) {
                            int itemId = menuItem.getItemId();
                            try {
                                errorMessage.append(", id=");
                                String menuItemResourceName = view.getResources()
                                        .getResourceName(itemId);
                                errorMessage.append(menuItemResourceName);
                            } catch (NotFoundException nfe) {
                                errorMessage.append("not found");
                            }
                        }
                        errorMessage.append(NEW_LINE);
                    }
                }
                return errorMessage.toString();
            }

            @Override
            public String getDescription() {
                return "click on menu item with id";
            }

            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(NavigationView.class),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        isDisplayingAtLeast(90)
                );
            }
        };

    }
}

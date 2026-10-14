# Implementation Plan - Auth & Profile Synchronization Fixes

## Problem Statement
1. **Google & Apple Auth**: Ensure Google and Apple login/signup options are removed or absent from the app login/register flows.
2. **Profile Name Synchronization**: When signing up with a new name, the name and email entered during registration are not synchronized into `DataManager` or displayed in `ProfileActivity`.
3. **Mobile Number Country Code Selection**: The mobile number input in registration shows a static `+1` `TextView` which cannot be switched. Users need a selectable country code dropdown (Spinner) defaulting to `+1`.

---

## Proposed Changes

### 1. Data & Profile Synchronization (`DataManager.java`, `RegisterActivity.java`, `ProfileActivity.java`, `activity_profile.xml`)
- **[MODIFY] [DataManager.java](file:///C:/Users/pabas/AndroidStudioProjects/FoodieGo/app/src/main/java/edu/cinec/foodiego/data/DataManager.java)**: Ensure `setUserProfile(name, email, phone)` properly sets and persists user details across sessions.
- **[MODIFY] [RegisterActivity.java](file:///C:/Users/pabas/AndroidStudioProjects/FoodieGo/app/src/main/java/edu/cinec/foodiego/RegisterActivity.java)**: Call `DataManager.getInstance().setUserProfile(name, email, phone)` upon successful registration so the user's name and email are synced.
- **[MODIFY] [activity_profile.xml](file:///C:/Users/pabas/AndroidStudioProjects/FoodieGo/app/src/main/res/layout/activity_profile.xml)**: Add IDs `tvProfileName` and `tvProfileEmail` to the profile name and email TextViews.
- **[MODIFY] [ProfileActivity.java](file:///C:/Users/pabas/AndroidStudioProjects/FoodieGo/app/src/main/java/edu/cinec/foodiego/ProfileActivity.java)**: Load `DataManager.getInstance().getUserName()` and `DataManager.getInstance().getUserEmail()` into `tvProfileName` and `tvProfileEmail` in `onResume()` / `onCreate()`.

### 2. Country Code Picker (`activity_register.xml`, `RegisterActivity.java`)
- **[MODIFY] [activity_register.xml](file:///C:/Users/pabas/AndroidStudioProjects/FoodieGo/app/src/main/res/layout/activity_register.xml)**: Replace the static `TextView` (`+1`) with a `Spinner` (`spinnerCountryCode`) allowing users to select country codes (`+1`, `+44`, `+94`, `+91`, `+61`, etc.), defaulting to `+1`.
- **[MODIFY] [RegisterActivity.java](file:///C:/Users/pabas/AndroidStudioProjects/FoodieGo/app/src/main/java/edu/cinec/foodiego/RegisterActivity.java)**: Set up the adapter for `spinnerCountryCode` and combine the selected country code with the phone number string when saving/registering.

---

## Verification Plan

### Automated Tests
- Build project using gradle (`app:assembleDebug`) to ensure no compilation errors.

### Manual Verification
1. Run app on emulator/device.
2. Navigate to Register screen, select country code (e.g. switch from `+1` to `+44`), enter name, email, phone, password, and register.
3. Open Profile screen and verify that the registered name and email are correctly synchronized and displayed.
4. Verify that Google and Apple auth buttons/options are absent from login/register flows.

package expo.modules.notifications.notifications;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import expo.modules.notifications.notifications.enums.NotificationPriority;
import expo.modules.notifications.notifications.model.NotificationContent;

public class JSONNotificationContentBuilder extends NotificationContent.Builder {
  private static final String TITLE_KEY = "title";
  private static final String TEXT_KEY = "message";
  private static final String SUBTITLE_KEY = "subtitle";
  private static final String SOUND_KEY = "sound";
  private static final String BODY_KEY = "body";
  private static final String VIBRATE_KEY = "vibrate";
  private static final String PRIORITY_KEY = "priority";
  private static final String BADGE_KEY = "badge";
  private static final String COLOR_KEY = "color";

  private SoundResolver mSoundResolver;

  public JSONNotificationContentBuilder(Context context) {
    mSoundResolver = new SoundResolver(context);
  }

  public NotificationContent.Builder setPayload(JSONObject payload) {
    this.setTitle(getTitle(payload))
        .setSubtitle(getSubtitle(payload))
        .setText(getText(payload))
        .setBody(getBody(payload))
        .setPriority(getPriority(payload))
        .setBadgeCount(getBadgeCount(payload))
        .setColor(getColor(payload));
    if (shouldPlayDefaultSound(payload)) {
      useDefaultSound();
    } else {
      setSound(getSound(payload));
    }

    if (shouldUseDefaultVibrationPattern(payload)) {
      useDefaultVibrationPattern();
    } else {
      setVibrationPattern(getVibrationPattern(payload));
    }
    return this;
  }

  protected String getTitle(JSONObject payload) {
    try {
      return payload.getString(TITLE_KEY);
    } catch (JSONException e) {
      return null;
    }
  }

  protected String getText(JSONObject payload) {
    try {
      return payload.getString(TEXT_KEY);
    } catch (JSONException e) {
      return null;
    }
  }

  protected String getSubtitle(JSONObject payload) {
    try {
      return payload.getString(SUBTITLE_KEY);
    } catch (JSONException e) {
      return null;
    }
  }

  protected Number getBadgeCount(JSONObject payload) {
    try {
      return payload.has(BADGE_KEY) ? payload.getInt(BADGE_KEY) : null;
    } catch (JSONException e) {
      return null;
    }
  }

  protected boolean shouldPlayDefaultSound(JSONObject payload) {
    try {
      // value is an explicit boolean
      return payload.getBoolean(SOUND_KEY);
    } catch (JSONException e) {
      // do nothing
    }

    // do not play a default sound only if the value is a valid Uri
    return getSound(payload) == null;
  }

  protected Uri getSound(JSONObject payload) {
    try {
      payload.getBoolean(SOUND_KEY);
      // value is an explicit boolean
      return null;
    } catch (JSONException e) {
      // it's not a boolean, let's handle it as a string
    }

    try {
      String soundValue = payload.getString(SOUND_KEY);
      return mSoundResolver.resolve(soundValue);
    } catch (JSONException e) {
      // if it's neither a boolean nor a string we can't handle it
    }
    return null;
  }

  @Nullable
  protected JSONObject getBody(JSONObject payload) {
    try {
      return new JSONObject(payload.optString(BODY_KEY));
    } catch (JSONException | NullPointerException e) {
      return null;
    }
  }

  protected boolean shouldUseDefaultVibrationPattern(JSONObject payload) {
    return !payload.optBoolean(VIBRATE_KEY, true);
  }

  protected long[] getVibrationPattern(JSONObject payload) {
    try {
      JSONArray vibrateJsonArray = payload.optJSONArray(VIBRATE_KEY);
      if (vibrateJsonArray != null) {
        long[] pattern = new long[vibrateJsonArray.length()];
        for (int i = 0; i < vibrateJsonArray.length(); i++) {
          pattern[i] = vibrateJsonArray.getLong(i);
        }
        return pattern;
      }
    } catch (JSONException e) {
      Log.w("expo-notifications", "Failed to set custom vibration pattern from the notification: " + e.getMessage());
    }

    return null;
  }

  protected NotificationPriority getPriority(JSONObject payload) {
    String priorityString = payload.optString(PRIORITY_KEY);
    return NotificationPriority.fromEnumValue(priorityString);
  }

  protected Number getColor(JSONObject payload) {
    try {
      return payload.has(COLOR_KEY) ? Color.parseColor(payload.getString(COLOR_KEY)) : null;
    } catch (IllegalArgumentException e) {
      Log.e("expo-notifications", "Could not have parsed color passed in notification.");
    } catch (JSONException e) {
      Log.e("expo-notifications", "Could not have parsed a non-string color value passed in notification.");
    }
    return null;
  }
}